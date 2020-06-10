package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMContext;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class DoubleMathHandler implements InstructionInterpreter<AbstractInsnNode> {
    private final DoubleMathFunction mathFunction;

    public DoubleMathHandler(DoubleMathFunction mathFunction) {
        this.mathFunction = mathFunction;
    }

    @Override
    public void process(VMContext ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        JavaValue value2 = stack.pop();
        JavaValue value1 = stack.pop();
        stack.pushTop(ctx.vm.newDouble(mathFunction.apply(value1.doubleValue(), value2.doubleValue())));
    }

    @FunctionalInterface
    public interface DoubleMathFunction {
        double apply(double v1, double v2);
    }
}
