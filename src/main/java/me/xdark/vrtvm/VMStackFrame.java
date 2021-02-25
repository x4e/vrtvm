package me.xdark.vrtvm;

import me.xdark.vrtvm.mirror.JavaMethod;
import org.objectweb.asm.tree.AbstractInsnNode;

/**
 * A stack frame is an item on the call stack containing the context of that call
 * This includes data such as the method being executed, registers and local variables
 */
 public final class VMStackFrame {
	public final VM vm;
	/**
	 * The data stack of the method we are currently executing
	 */
    public final VMStack stack;
	/**
	 * Local variables of the method this stackframe is executing
	 */
	public final VMLocals locals;
	/**
	 * Instance of the class the method we are executing belongs to - can be null
	 */
    public final JavaValue handle;
	/**
	 * The method we are currently executing
	 */
	public final JavaMethod method;
	/**
	 * Program counter, holds the *next* instruction to be executed
	 */
    public AbstractInsnNode pc;

    public VMStackFrame(VM vm, VMStack stack, VMLocals locals, JavaValue handle, JavaMethod method) {
        this.vm = vm;
        this.stack = stack;
        this.locals = locals;
        this.handle = handle;
        this.method = method;
    }

    public void jumpTo(AbstractInsnNode target) {
    	pc = target;
    }

    public void _return() {
	    pc = null;
    }
}
