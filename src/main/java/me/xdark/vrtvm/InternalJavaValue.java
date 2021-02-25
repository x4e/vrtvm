package me.xdark.vrtvm;

import me.xdark.vrtvm.mirror.JavaClass;

public abstract class InternalJavaValue extends JavaValue {
    @Override
    public long longValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public double doubleValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int intValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public float floatValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public short shortValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public char charValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public byte byteValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean booleanValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void push(VMStack stack) {
        stack.doPush(this);
    }

    @Override
    public JavaClass getJClass() {
        throw new UnsupportedOperationException();
    }

    @Override
    void setJClass(JavaClass jClass) {
        throw new UnsupportedOperationException();
    }
}
