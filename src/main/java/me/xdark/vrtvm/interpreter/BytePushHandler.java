package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMContext;
import org.objectweb.asm.tree.IntInsnNode;

public final class BytePushHandler implements InstructionInterpreter<IntInsnNode> {
    @Override
    public void process(VMContext ctx, IntInsnNode insn) {
        ctx.stack.push(ctx.vm.newByte((byte) insn.operand));
    }
}
