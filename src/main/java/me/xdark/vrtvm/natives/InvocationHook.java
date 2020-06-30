package me.xdark.vrtvm.natives;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.VMContext;

public interface InvocationHook {
    JavaValue execute(VMContext ctx);
}
