package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.IntInsnNode;

public final class ShortPushHandler implements InstructionInterpreter<IntInsnNode> {
    @Override
    public void process(VMStackFrame ctx, IntInsnNode insn) {
        ctx.stack.push(ctx.vm.newShort((short) insn.operand));
    }
}
