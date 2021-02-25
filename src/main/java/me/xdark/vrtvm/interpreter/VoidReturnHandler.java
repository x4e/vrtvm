package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.ContextExitSignal;
import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.InsnNode;

public final class VoidReturnHandler implements InstructionInterpreter<InsnNode> {
    @Override
    public void process(VMStackFrame ctx, InsnNode insn) {
        VMStack stack=ctx.stack;
        if (!stack.isEmpty()) {
            throw new IllegalStateException("Expected empty stack");
        }
        stack.push(ctx.vm.nullValue());
        throw ContextExitSignal.INSTANCE;
    }
}
