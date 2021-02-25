package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class FloatMathHandler implements InstructionInterpreter<AbstractInsnNode> {
    private final FloatMathFunction mathFunction;

    public FloatMathHandler(FloatMathFunction mathFunction) {
        this.mathFunction = mathFunction;
    }

    @Override
    public void process(VMStackFrame ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        JavaValue value2 = stack.pop();
        JavaValue value1 = stack.pop();
        stack.push(ctx.vm.newFloat(mathFunction.apply(value1.floatValue(), value2.floatValue())));
    }

    @FunctionalInterface
    public interface FloatMathFunction {
        float apply(float v1, float v2);
    }
}
