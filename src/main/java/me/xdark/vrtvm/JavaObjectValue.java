package me.xdark.vrtvm;

import me.xdark.vrtvm.mirror.JavaClass;

public final class JavaObjectValue extends JavaValue {
    private JavaClass jClass;

    @Override
    public boolean matches(ValueType type) {
        return type == ValueType.OBJECT;
    }

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
        return jClass;
    }

    @Override
    void setJClass(JavaClass jClass) {
        this.jClass = jClass;
    }
}
