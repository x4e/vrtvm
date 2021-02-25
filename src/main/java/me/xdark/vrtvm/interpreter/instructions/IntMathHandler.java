package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class IntMathHandler implements InstructionInterpreter<AbstractInsnNode> {
    private final IntMathFunction mathFunction;

    public IntMathHandler(IntMathFunction mathFunction) {
        this.mathFunction = mathFunction;
    }

    @Override
    public void process(VMStackFrame ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        JavaValue value2 = stack.pop();
        JavaValue value1 = stack.pop();
        stack.push(ctx.vm.newInt(mathFunction.apply(value1.intValue(), value2.intValue())));
    }

    @FunctionalInterface
    public interface IntMathFunction {
        int apply(int v1, int v2);
    }
}
