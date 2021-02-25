package me.xdark.vrtvm.mirror;

import me.xdark.vrtvm.*;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.lang.reflect.Modifier;

public final class JavaMethod {
    private final String name;
    private final String desc;
    private final int modifiers;
    private final JavaClass declaringClass;
    private final JavaClass returnType;
    private final JavaClass[] parameterTypes;
    private final InsnList instructions;
    private final int maxLocals;
    private final int maxStack;
    private final MemberDeclaration declaration;

    public JavaMethod(String name, String desc, int modifiers, JavaClass declaringClass, JavaClass returnType, JavaClass[] parameterTypes, InsnList instructions, int maxLocals, int maxStack) {
        this.name = name;
        this.desc = desc;
        this.declaration = new MemberDeclaration(declaringClass.getInternalName(), name, desc);
        this.modifiers = modifiers;
        this.declaringClass = declaringClass;
        this.returnType = returnType;
        this.parameterTypes = parameterTypes;
        this.instructions = instructions;
        this.maxLocals = maxLocals;
        this.maxStack = maxStack;
    }

    public JavaMethod(JavaValue classLoader, VM vm, MethodNode node) {
        this(node.name, node.desc, node.access, /* TODO */null,/* TODO */ null, /* TODO */null, node.instructions, node.maxLocals, node.maxStack);
    }

    public VMStackFrame newContext(VM vm, JavaValue handle, JavaValue... args) {
        VMLocals locals = new VMLocals(maxLocals, vm.nullValue());
        int index = 0;
        if (!Modifier.isStatic(modifiers)) {
            locals.store(index++, handle);
        }
        for (JavaValue arg : args) {
            locals.store(index++, arg);
            if (arg.matches(ValueType.WIDE)) {
                locals.store(index++, vm.topValue());
            }
        }
        return new VMStackFrame(vm, new VMStack(maxStack, vm.nullValue(), vm.topValue()), locals, handle, this);
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public JavaClass getDeclaringClass() {
        return declaringClass;
    }

    public JavaClass getReturnType() {
        return returnType;
    }

    public JavaClass[] getParameterTypes() {
        return parameterTypes;
    }

    public MemberDeclaration getDeclaration() {
        return declaration;
    }

    public InsnList getInstructions() {
        return instructions;
    }
}
