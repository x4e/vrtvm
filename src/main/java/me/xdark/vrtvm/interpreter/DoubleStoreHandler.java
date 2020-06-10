package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMContext;
import org.objectweb.asm.tree.VarInsnNode;

public final class DoubleStoreHandler implements InstructionInterpreter<VarInsnNode> {
    @Override
    public void process(VMContext ctx, VarInsnNode insn) {
        ctx.locals.store(insn.var, ctx.stack.pop());
    }
}
