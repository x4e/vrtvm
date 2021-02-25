package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import org.objectweb.asm.tree.JumpInsnNode;

public final class JumpHandler implements InstructionInterpreter<JumpInsnNode> {
    @Override
    public void process(VMStackFrame ctx, JumpInsnNode insn) {
        ctx.cursor(insn.label);
    }
}
