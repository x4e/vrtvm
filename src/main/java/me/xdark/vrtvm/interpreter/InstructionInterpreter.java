package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMContext;
import org.objectweb.asm.tree.AbstractInsnNode;

public interface InstructionInterpreter<T extends AbstractInsnNode> {
    void process(VMContext ctx, T insn);
}
