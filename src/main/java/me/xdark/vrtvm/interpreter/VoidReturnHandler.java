package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.ContextExitSignal;
import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.InsnNode;

public final class VoidReturnHandler implements InstructionInterpreter<InsnNode> {
    @Override
    public void process(VMStackFrame ctx, InsnNode insn) {
        ctx.stack.push(ctx.vm.nullValue());
        ctx._return();
    }
}
