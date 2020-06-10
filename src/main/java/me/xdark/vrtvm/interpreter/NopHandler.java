package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMContext;
import org.objectweb.asm.tree.AbstractInsnNode;

public final class NopHandler implements InstructionInterpreter<AbstractInsnNode> {
    @Override
    public void process(VMContext ctx, AbstractInsnNode insn) {
        // no-op
    }
}
