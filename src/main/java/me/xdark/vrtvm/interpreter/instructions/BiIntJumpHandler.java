package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.JumpInsnNode;

public final class BiIntJumpHandler implements InstructionInterpreter<JumpInsnNode> {
    private final BiIntJumpPredicate predicate;

    public BiIntJumpHandler(BiIntJumpPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public void process(VMStackFrame ctx, JumpInsnNode insn) {
        VMStack stack = ctx.stack;
        int v2 = stack.pop().intValue(), v1 =stack.pop().intValue();
        if (predicate.test(v1,v2)) {
            ctx.jumpTo(insn.label);
        }
    }

    @FunctionalInterface
    public interface BiIntJumpPredicate {
        boolean test(int v1,int v2);
    }
}
