package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import org.objectweb.asm.tree.VarInsnNode;

public final class ObjectStoreHandler implements InstructionInterpreter<VarInsnNode> {
    @Override
    public void process(VMStackFrame ctx, VarInsnNode insn) {
        ctx.locals.store(insn.var, ctx.stack.pop());
    }
}
