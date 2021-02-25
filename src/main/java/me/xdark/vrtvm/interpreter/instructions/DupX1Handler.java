package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class DupX1Handler implements InstructionInterpreter<AbstractInsnNode> {
    @Override
    public void process(VMStackFrame ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        JavaValue value1 = stack.pop();
        JavaValue value2 = stack.pop();
        stack.push(value1);
        stack.push(value2);
        stack.push(value1);
    }
}
