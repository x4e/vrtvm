package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMContext;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.JumpInsnNode;

public final class BiIntJumpHandler implements InstructionInterpreter<JumpInsnNode> {
    private final BiIntJumpPredicate predicate;

    public BiIntJumpHandler(BiIntJumpPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public void process(VMContext ctx, JumpInsnNode insn) {
        VMStack stack = ctx.stack;
        int v2 = stack.pop().intValue(), v1 =stack.pop().intValue();
        if (predicate.test(v1,v2)) {
            ctx.cursor(insn.label);
        }
    }

    @FunctionalInterface
    public interface BiIntJumpPredicate {
        boolean test(int v1,int v2);
    }
}
