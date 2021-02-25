package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class IntConstantHandler implements InstructionInterpreter<AbstractInsnNode> {
    @Override
    public void process(VMStackFrame ctx, AbstractInsnNode insn) {
        ctx.stack.push(ctx.vm.newInt(insn.getOpcode() - 3));
    }
}
