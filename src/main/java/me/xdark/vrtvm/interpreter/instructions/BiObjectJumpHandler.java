package me.xdark.vrtvm.interpreter.instructions;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMStackFrame;
import me.xdark.vrtvm.VMStack;
import me.xdark.vrtvm.interpreter.InstructionInterpreter;
import org.objectweb.asm.tree.JumpInsnNode;

import java.util.function.BiPredicate;

public final class BiObjectJumpHandler implements InstructionInterpreter<JumpInsnNode> {
    private final BiPredicate<JavaValue, JavaValue> predicate;

    public BiObjectJumpHandler(BiPredicate<JavaValue, JavaValue> predicate) {
        this.predicate = predicate;
    }

    @Override
    public void process(VMStackFrame ctx, JumpInsnNode insn) {
        VMStack stack = ctx.stack;
        JavaValue v2 = stack.pop(), v1 = stack.pop();
        if (predicate.test(v1, v2)) {
            ctx.jumpTo(insn.label);
        }
    }
}
