package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMContext;
import me.xdark.vrtvm.VMStack;
import org.objectweb.asm.tree.JumpInsnNode;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public final class BiObjectJumpHandler implements InstructionInterpreter<JumpInsnNode> {
    private final BiPredicate<JavaValue, JavaValue> predicate;

    public BiObjectJumpHandler(BiPredicate<JavaValue, JavaValue> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void process(VMContext ctx, JumpInsnNode insn) {
        VMStack stack = ctx.stack;
        JavaValue v2 = stack.pop(), v1 = stack.pop();
        if (predicate.test(v1, v2)) {
            ctx.cursor(insn.label);
        }
    }
}
