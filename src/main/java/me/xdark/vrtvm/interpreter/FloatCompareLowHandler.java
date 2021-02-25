package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class FloatCompareLowHandler implements InstructionInterpreter<AbstractInsnNode> {
    @Override
    public void process(VMStackFrame ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        float value1 = stack.pop().floatValue();
        float value2 = stack.pop().floatValue();
        stack.push(ctx.vm.newInt((Float.isNaN(value1) || Float.isNaN(value2)) ? -1 : Float.compare(value1, value2)));
    }
}
