package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import org.objectweb.asm.tree.LookupSwitchInsnNode;

public final class LookupSwitchHandler implements InstructionInterpreter<LookupSwitchInsnNode> {
    @Override
    public void process(VMStackFrame ctx, LookupSwitchInsnNode insn) {
        int key = ctx.stack.pop().intValue();
        int index = insn.keys.indexOf(key);
        if (index == -1) {
            ctx.jumpTo(insn.dflt);
        } else {
            ctx.jumpTo(insn.labels.get(index));
        }
    }
}
