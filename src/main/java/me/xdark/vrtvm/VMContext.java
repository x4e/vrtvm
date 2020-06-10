package me.xdark.vrtvm;

import me.xdark.vrtvm.mirror.JavaMethod;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class VMContext {
    public final VM vm;
    public final VMStack stack;
    public final VMLocals locals;
    public final JavaValue handle;
    public final JavaValue[] args;
    public final JavaMethod method;
    public int cursor;

    public VMContext(VM vm, VMStack stack, VMLocals locals, JavaValue handle, JavaValue[] args, JavaMethod method) {
        this.vm = vm;
        this.stack = stack;
        this.locals = locals;
        this.handle = handle;
        this.args = args;
        this.method = method;
    }

    public void cursor(AbstractInsnNode insn) { }
}
