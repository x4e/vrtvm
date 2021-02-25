package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class IntToDoubleHandler implements InstructionInterpreter<AbstractInsnNode> {
    @Override
    public void process(VMStackFrame ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        stack.pushTop(ctx.vm.newDouble(stack.pop().doubleValue()));
    }
}
