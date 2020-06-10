package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.ContextExitSignal;
import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMContext;
import me.xdark.vrtvm.ValueType;
import org.objectweb.asm.tree.InsnNode;

public final class DoubleReturnHandler implements InstructionInterpreter<InsnNode> {
    @Override
    public void process(VMContext ctx, InsnNode insn) {
        JavaValue pop = ctx.stack.pop();
        if (!pop.matches(ValueType.TOP)) {
            throw new IllegalStateException("Expected to pop top, but got: " + pop);
        }
        throw ContextExitSignal.INSTANCE;
    }
}
