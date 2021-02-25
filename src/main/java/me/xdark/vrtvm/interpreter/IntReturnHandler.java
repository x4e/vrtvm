package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.ContextExitSignal;
import me.xdark.vrtvm.VMStackFrame;
import org.objectweb.asm.tree.InsnNode;

public final class IntReturnHandler implements InstructionInterpreter<InsnNode> {
    @Override
    public void process(VMStackFrame ctx, InsnNode insn) {
        ctx._return();
    }
}
