package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMLocals;
import org.objectweb.asm.tree.IincInsnNode;

public final class IntIncrementHandler implements InstructionInterpreter<IincInsnNode> {
    @Override
    public void process(VMStackFrame ctx, IincInsnNode insn) {
        VMLocals locals = ctx.locals;
        int index = insn.var;
        locals.store(index, ctx.vm.newInt(locals.load(index).intValue() +insn.incr));
    }
}
