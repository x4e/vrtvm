package me.xdark.vrtvm;

import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import me.xdark.vrtvm.logging.VMLogger;
import me.xdark.vrtvm.mirror.InstanceClass;
import me.xdark.vrtvm.mirror.JavaClass;
import me.xdark.vrtvm.mirror.JavaMethod;
import me.xdark.vrtvm.mirror.PrimitiveClass;
import me.xdark.vrtvm.natives.ClassHooks;
import me.xdark.vrtvm.natives.ClassLoaderHooks;
import me.xdark.vrtvm.natives.InvocationHook;
import me.xdark.vrtvm.natives.ObjectHooks;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class VM {
    private final VMLogger logger;
    private final SystemDictionary dictionary;
    private final JavaClass longType;
    private final JavaClass doubleType;
    private final JavaClass intType;
    private final JavaClass floatType;
    private final JavaClass shortType;
    private final JavaClass charType;
    private final JavaClass byteType;
    private final JavaClass booleanType;
    private final JavaClass voidType;
    private final JavaValue _null;
    private final JavaValue _top;
    private final Instructions instructions;
    private final Map<MemberDeclaration, InvocationHook> hooks = new HashMap<>(64);
    private final Set<VMThread> vmThreads = Collections.newSetFromMap(new WeakHashMap<>());
    private final Object threadLock = new Object();
    private boolean panicked;

    public VM(VMLogger logger) {
        this.logger = logger;
        // Setup dictionary
        dictionary = new SystemDictionary();
        // Setup primitives
        longType = newPrimitiveClass("long", "J");
        doubleType = newPrimitiveClass("double", "D");
        intType = newPrimitiveClass("int", "I");
        floatType = newPrimitiveClass("float", "F");
        shortType = newPrimitiveClass("short", "S");
        charType = newPrimitiveClass("char", "C");
        byteType = newPrimitiveClass("byte", "B");
        booleanType = newPrimitiveClass("boolean", "Z");
        voidType = newPrimitiveClass("void", "V");
        // Setup internals
        _null = new NullJavaValue(this);
        _top = new TopJavaValue(this);
        instructions = new Instructions();
        ClassHooks.setup(this);
        ClassLoaderHooks.setup(this);
        ObjectHooks.setup(this);
    }

    public void registerThread(VMThread thread) {
        synchronized (threadLock) {
            if (!vmThreads.add(thread)) {
                panic("duplicate thread");
            }
        }
    }

    public void unregisterThread(VMThread owner) {
        synchronized (threadLock) {
            if (!vmThreads.remove(owner)) {
                panic("attempted to remove non-existing thread");
            }
        }
    }

    public void installHook(MemberDeclaration declaration, InvocationHook hook) {
        hooks.put(declaration, hook);
    }

    public void removeHook(MemberDeclaration declaration) {
        hooks.remove(declaration);
    }

    public <V extends JavaValue> V executeResolve(JavaValue handle, MemberDeclaration declaration, boolean runHooks, JavaValue... args) {
        JavaClass jclass = handle.getJClass();

        JavaMethod method;
        do {
            method = jclass.getMethod(declaration);
        } while (method == null && (jclass = jclass.getSuperclass()) != null);
        if (method == null) {
            throw new UnsupportedOperationException();
        }
        return executeContext(method.newContext(this, handle, args), runHooks);
    }

    public <V extends JavaValue> V executeContext(VMContext ctx, boolean runHooks) {
        VMThread thread = (VMThread) Thread.currentThread();
        JavaMethod method = ctx.method;
        StackTraceElement element = new StackTraceElement(method.getDeclaringClass().getName(), method.getName(), method.getDeclaringClass().getSourceFile(), -1);
        thread.pushEntry(element);
        if (runHooks) {
            InvocationHook hook = hooks.get(method.getDeclaration());
            if (hook != null) {
                try {
                    return (V) hook.execute(ctx);
                } finally {
                    thread.pop();
                }
            }
        }
        InsnList list = method.getInstructions();
        Instructions opset = instructions;
        try {
            while (true) {
                AbstractInsnNode insn = list.get(ctx.cursor++);
                int opcode = insn.getOpcode();
                if (opcode == -1) {
                    continue;
                }
                InstructionInterpreter interpreter = opset.forOpcode(opcode);
                if (interpreter == null) {
                    throw new UnsupportedOperationException("Unsupported opcode: " + opcode);
                }
                try {
                    interpreter.process(ctx, insn);
                } catch (ContextExitSignal ignored) {
                    VMStack stack = ctx.stack;
                    V v = stack.pop();
                    if (!stack.isEmpty()) {
                        throw new IllegalStateException("Stack is not empty after execution");
                    }
                    return v;
                }
            }
        } finally {
            thread.pop();
        }
    }

    public void panic(String reason) {
        if (panicked) {
            throw new InternalError();
        }
        panicked = true;
        VMLogger logger = this.logger;
        logger.panic("A fatal error has been detected, cannot continue: %s", reason);
        Set<VMThread> vmThreads = this.vmThreads;
        for (VMThread thread : vmThreads) {
            LinkedList<StackTraceElement> stack = thread.captureStack();
            thread.interrupt();
            try {
                thread.join(1000L);
            } catch (InterruptedException ex) {
                // No luck
                thread.stop();
            }
            while (!stack.isEmpty()) {
                logger.panic("\t %s", stack.poll());
            }
        }
        vmThreads.clear();
    }

    public JavaValue nullValue() {
        return _null;
    }

    public JavaValue voidValue() {
        return _null;
    }

    public JavaValue topValue() {
        return _top;
    }

    public JavaValue newLong(long v) {
        return new LongJavaValue(this, v);
    }

    public JavaValue newDouble(double v) {
        return new DoubleJavaValue(this, v);
    }

    public JavaValue newInt(int v) {
        return new IntJavaValue(this, v);
    }

    public JavaValue newFloat(float v) {
        return new FloatJavaValue(this, v);
    }

    public JavaValue newShort(short v) {
        return new ShortJavaValue(this, v);
    }

    public JavaValue newChar(char v) {
        return new CharJavaValue(this, v);
    }

    public JavaValue newByte(byte v) {
        return new ByteJavaValue(this, v);
    }

    public JavaValue newBoolean(boolean v) {
        return new ByteJavaValue(this, (byte) (v ? 1 : 0));
    }

    public JavaValue newString(String str) {
        throw new UnsupportedOperationException();
    }

    public JavaClass primitiveClass(String name) {
        switch (name) {
            case "long":
                return longType;
            case "double":
                return doubleType;
            case "int":
                return intType;
            case "float":
                return floatType;
            case "short":
                return shortType;
            case "char":
                return charType;
            case "byte":
                return byteType;
            case "boolean":
                return booleanType;
            case "void":
                return voidType;
            default:
                throw new IllegalArgumentException(name);
        }
    }

    public JavaClass findLoadedClass(JavaValue classLoader, String name) {
        return dictionary.getLinkedClass(new SystemDictionary.DictionaryEntry(classLoader, name.replace('.', '/')));
    }

    public JavaClass defineClass(JavaValue classLoader, JavaValue protectionDomain, String name, byte[] code, int off, int len) {
        if (off < 0) {
            throw new IllegalStateException("Offset is negative!");
        } else if (len > code.length) {
            throw new IllegalStateException("Code length is too long!");
        }
        if (off != 0 && len != code.length) {
            code = Arrays.copyOfRange(code, off, len);
        }
        ClassReader classReader = new ClassReader(code);
        if (name != null) {
            String actualName = classReader.getClassName();
            if (!actualName.equals(name)) {
                throw new IllegalStateException("Wrong class name! Expected: " + actualName);
            }
            name = actualName;
        }
        ClassNode node = new ClassNode();
        classReader.accept(node, 0);
        JavaClass jClass = new InstanceClass(this, classLoader, node);
        assignClassHandle(jClass);
        jClass.setProtectionDomain(protectionDomain);
        // Link into system dictionary
        dictionary.linkClass(new SystemDictionary.DictionaryEntry(classLoader, name), jClass);
        return jClass;
    }

    public void resolveClass(JavaClass jClass) {
        jClass.resolve();
    }

    public JavaClass objectClass() {
        throw new UnsupportedOperationException();
    }

    public SystemDictionary getDictionary() {
        return dictionary;
    }

    public JavaArrayValue getInterfaces(JavaClass jClass) {
        JavaClass[] interfaces = jClass.getInterfaces();
        int j = interfaces.length;
        JavaArrayValue arr = new JavaArrayValue(this, j, _null);
        while (j-- > 0) {
            arr.set(j, interfaces[j].getHandle());
        }
        return arr;
    }

    public JavaValue wrapLdc(Object ldc) {
        if (ldc instanceof Long) {
            return newLong((Long) ldc);
        } else if (ldc instanceof Double) {
            return newDouble((Double) ldc);
        } else if (ldc instanceof Integer) {
            return newInt((Integer) ldc);
        } else if (ldc instanceof Float) {
            return newFloat((Float) ldc);
        } else if (ldc instanceof Short) {
            return newShort((Short) ldc);
        } else if (ldc instanceof Character) {
            return newChar((Character) ldc);
        } else if (ldc instanceof Byte) {
            return newByte((Byte) ldc);
        } else if (ldc instanceof Boolean) {
            return newBoolean((Boolean) ldc);
        } else if (ldc instanceof String) {
            return newString((String) ldc);
        } else if (ldc == null) {
            return _null;
        }
        throw new UnsupportedOperationException();
    }

    private JavaClass newPrimitiveClass(String name, String internalName) {
        JavaClass jClass = new PrimitiveClass(this, name, internalName, null /* TODO */);
        assignClassHandle(jClass);
        return jClass;
    }

    private void assignClassHandle(JavaClass jClass) {
        JavaObject object = new JavaObject(this);
        object.setJClass(jClass); // maybe we can avoid doing that?...
        jClass.setHandle(object);
    }
}
