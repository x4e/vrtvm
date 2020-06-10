package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.ContextExitSignal;
import me.xdark.vrtvm.VMContext;
import org.objectweb.asm.tree.InsnNode;

public final class IntReturnHandler implements InstructionInterpreter<InsnNode> {
    @Override
    public void process(VMContext ctx, InsnNode insn) {
        throw ContextExitSignal.INSTANCE;
    }
}
