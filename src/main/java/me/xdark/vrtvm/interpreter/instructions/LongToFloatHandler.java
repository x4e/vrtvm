package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class LongToFloatHandler implements InstructionInterpreter<AbstractInsnNode> {
    @Override
    public void process(VMStackFrame ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        stack.push(ctx.vm.newFloat(stack.popTop().floatValue()));
    }
}
