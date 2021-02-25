package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.VarInsnNode;

public final class FloatLoadHandler implements InstructionInterpreter<VarInsnNode> {
    @Override
    public void process(VMStackFrame ctx, VarInsnNode insn) {
        ctx.stack.push(ctx.locals.load(insn.var));
    }
}
