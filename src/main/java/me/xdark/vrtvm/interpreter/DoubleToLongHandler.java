package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMContext;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class DoubleToLongHandler implements InstructionInterpreter<AbstractInsnNode> {
    @Override
    public void process(VMContext ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        stack.pushTop(ctx.vm.newLong(stack.popTop().longValue()));
    }
}
