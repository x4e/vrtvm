package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import org.objectweb.asm.tree.TableSwitchInsnNode;

public final class TableSwitchHandler implements InstructionInterpreter<TableSwitchInsnNode> {
    @Override
    public void process(VMStackFrame ctx, TableSwitchInsnNode insn) {
        int key = ctx.stack.pop().intValue();
        int min = insn.min;
        if (key < min || key > insn.max) {
            ctx.jumpTo(insn.dflt);
        } else {
            ctx.jumpTo(insn.labels.get(key - min));
        }
    }
}
