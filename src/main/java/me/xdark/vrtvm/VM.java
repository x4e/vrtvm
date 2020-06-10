package me.xdark.vrtvm;

import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import me.xdark.vrtvm.mirror.JavaClass;
import me.xdark.vrtvm.mirror.PrimitiveClass;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.InsnList;

public final class VM {
    private final SystemDictionary dictionary;
    private final JavaClass longType;
    private final JavaClass doubleType;
    private final JavaClass intType;
    private final JavaClass floatType;
    private final JavaClass shortType;
    private final JavaClass charType;
    private final JavaClass byteType;
    private final JavaClass booleanType;
    private final JavaValue _null;
    private final JavaValue _top;
    private final Instructions instructions;

    public VM() {
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
        // Setup internals
        _null = new NullJavaValue(this);
        _top = new TopJavaValue(this);
        instructions = new Instructions();
    }

    public <V extends JavaValue> V executeContext(VMContext ctx) {
        InsnList list = ctx.method.getInstructions();
        Instructions opset = instructions;
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
    }

    public JavaValue nullValue() {
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
        PrimitiveClass jclass = new PrimitiveClass(name, internalName, null /* TODO */);
        JavaObject object = new JavaObject(this);
        object.setJClass(jclass); // maybe we can avoid doing that?...
        jclass.setHandle(object);
        return jclass;
    }


}
