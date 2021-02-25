package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import org.objectweb.asm.tree.JumpInsnNode;

public final class IntJumpHandler implements InstructionInterpreter<JumpInsnNode> {
    private final IntJumpPredicate predicate;

    public IntJumpHandler(IntJumpPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public void process(VMStackFrame ctx, JumpInsnNode insn) {
        if (predicate.test(ctx.stack.pop().intValue())) {
            ctx._return();
        }
    }

    @FunctionalInterface
    public interface IntJumpPredicate {
        boolean test(int v);
    }
}
