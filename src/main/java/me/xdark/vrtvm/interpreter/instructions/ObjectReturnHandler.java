package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.ContextExitSignal;
import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.InsnNode;

public final class ObjectReturnHandler implements InstructionInterpreter<InsnNode> {
    @Override
    public void process(VMStackFrame ctx, InsnNode insn) {
        ctx._return();
    }
}
