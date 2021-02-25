package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.LdcInsnNode;

public final class LdcHandler implements InstructionInterpreter<LdcInsnNode> {
    @Override
    public void process(VMStackFrame ctx, LdcInsnNode insn) {
        ctx.stack.push(ctx.vm.wrapLdc(insn.cst));
    }
}
