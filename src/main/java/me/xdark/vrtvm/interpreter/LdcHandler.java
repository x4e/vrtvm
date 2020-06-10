package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMContext;
import org.objectweb.asm.tree.LdcInsnNode;

public final class LdcHandler implements InstructionInterpreter<LdcInsnNode> {
    @Override
    public void process(VMContext ctx, LdcInsnNode insn) {
        ctx.stack.push(ctx.vm.wrapLdc(insn.cst));
    }
}
