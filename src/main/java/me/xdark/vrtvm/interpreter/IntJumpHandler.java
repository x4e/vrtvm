package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMContext;
import org.objectweb.asm.tree.JumpInsnNode;

public final class IntJumpHandler implements InstructionInterpreter<JumpInsnNode> {
    private final IntJumpPredicate predicate;

    public IntJumpHandler(IntJumpPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public void process(VMContext ctx, JumpInsnNode insn) {
        if (predicate.test(ctx.stack.pop().intValue())) {
            ctx.cursor(insn.label);
        }
    }

    @FunctionalInterface
    public interface IntJumpPredicate {
        boolean test(int v);
    }
}
