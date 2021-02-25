package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import org.objectweb.asm.tree.VarInsnNode;

public final class IntLoadHandler implements InstructionInterpreter<VarInsnNode> {
    @Override
    public void process(VMStackFrame ctx, VarInsnNode insn) {
        ctx.stack.push(ctx.locals.load(insn.var));
    }
}
