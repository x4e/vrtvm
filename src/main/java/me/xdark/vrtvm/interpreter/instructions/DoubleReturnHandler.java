package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.ContextExitSignal;
import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.ValueType;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.InsnNode;

public final class DoubleReturnHandler implements InstructionInterpreter<InsnNode> {
    @Override
    public void process(VMStackFrame ctx, InsnNode insn) {
        JavaValue pop = ctx.stack.pop();
        if (!pop.matches(ValueType.TOP)) {
            throw new IllegalStateException("Expected to pop top, but got: " + pop);
        }
        ctx._return();
    }
}
