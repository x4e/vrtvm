package me.xdark.vrtvm.interpreter;

import me.xdark.vrtvm.VMStackFrame;
import org.objectweb.asm.tree.TableSwitchInsnNode;

public final class TableSwitchHandler implements InstructionInterpreter<TableSwitchInsnNode> {
    @Override
    public void process(VMStackFrame ctx, TableSwitchInsnNode insn) {
        int key = ctx.stack.pop().intValue();
        int min = insn.min;
        ctx.cursor((key < min || key > insn.max) ? insn.dflt : insn.labels.get(key - min));
    }
}
