package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import org.objectweb.asm.tree.IntInsnNode;

public final class BytePushHandler implements InstructionInterpreter<IntInsnNode> {
    @Override
    public void process(VMStackFrame ctx, IntInsnNode insn) {
        ctx.stack.push(ctx.vm.newByte((byte) insn.operand));
    }
}
