package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VM;
import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class ValueConvertationHandler implements InstructionInterpreter<AbstractInsnNode> {
    private final ConvertationFunction function;
    private final boolean top;

    public ValueConvertationHandler(ConvertationFunction function, boolean top) {
        this.function = function;
        this.top = top;
    }

    @Override
    public void process(VMStackFrame ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        stack.push(function.apply(ctx.vm, top ? stack.popTop() : stack.pop()));
    }

    @FunctionalInterface
    public interface ConvertationFunction {
        JavaValue apply(VM vm, JavaValue value);
    }
}
