package me.xdark.vrtvm.natives;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMStackFrame;

public interface InvocationHook {
    JavaValue execute(VMStackFrame ctx);
}
