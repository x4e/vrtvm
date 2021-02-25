package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class LongCompareHandler implements InstructionInterpreter<AbstractInsnNode> {
    @Override
    public void process(VMStackFrame ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        long value1 = stack.popTop().longValue();
        long value2 = stack.popTop().longValue();
        stack.push(ctx.vm.newInt((value1 > value2) ? 1 : -1));
    }
}
