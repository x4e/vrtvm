package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import org.objectweb.asm.tree.AbstractInsnNode;

public interface InstructionInterpreter<T extends AbstractInsnNode> {
    void process(VMStackFrame ctx, T insn);
}
