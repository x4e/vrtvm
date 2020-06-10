package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMContext;
import org.objectweb.asm.tree.VarInsnNode;

public final class ObjectLoadHandler implements InstructionInterpreter<VarInsnNode> {
    @Override
    public void process(VMContext ctx, VarInsnNode insn) {
        ctx.stack.push(ctx.locals.load(insn.var));
    }
}
