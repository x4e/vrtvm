package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class DupHandler implements InstructionInterpreter<AbstractInsnNode> {
    @Override
    public void process(VMStackFrame ctx, AbstractInsnNode insn) {
        VMStack stack = ctx.stack;
        JavaValue v = stack.pop();
        stack.push(v);
        stack.push(v);
    }
}
