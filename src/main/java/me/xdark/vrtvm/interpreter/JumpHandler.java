package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMContext;
import org.objectweb.asm.tree.JumpInsnNode;

public final class JumpHandler implements InstructionInterpreter<JumpInsnNode> {
    @Override
    public void process(VMContext ctx, JumpInsnNode insn) {
        ctx.cursor(insn.label);
    }
}
