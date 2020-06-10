package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMContext;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class DoubleCompareLowHandler implements InstructionInterpreter<AbstractInsnNode> {
    @Override
    public void process(VMContext ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        double value1 = stack.popTop().doubleValue();
        double value2 = stack.popTop().doubleValue();
        stack.push(ctx.vm.newInt((Double.isNaN(value1) || Double.isNaN(value2)) ? -1 : Double.compare(value1, value2)));
    }
}
