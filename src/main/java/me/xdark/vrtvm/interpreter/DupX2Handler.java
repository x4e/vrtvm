package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMContext;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class DupX2Handler implements InstructionInterpreter<AbstractInsnNode> {
    @Override
    public void process(VMContext ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        JavaValue value1 = stack.pop();
        JavaValue value2 = stack.pop();
        JavaValue value3 = stack.pop();
        stack.push(value1);
        stack.push(value3);
        stack.push(value2);
        stack.push(value1);
    }
}
