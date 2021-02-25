package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.VarInsnNode;

public final class LongStoreHandler implements InstructionInterpreter<VarInsnNode> {
    @Override
    public void process(VMStackFrame ctx, VarInsnNode insn) {
        ctx.locals.store(insn.var, ctx.stack.pop());
    }
}
