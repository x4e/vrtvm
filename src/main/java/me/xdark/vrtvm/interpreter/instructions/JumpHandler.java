package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.JumpInsnNode;

public final class JumpHandler implements InstructionInterpreter<JumpInsnNode> {
    @Override
    public void process(VMStackFrame ctx, JumpInsnNode insn) {
        ctx._return();
    }
}
