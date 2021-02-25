package me.xdark.vrtvm;

import me.xdark.vrtvm.mirror.JavaClass;

public final class DoubleJavaValue extends JavaValue {
    private final double value;

    protected DoubleJavaValue(double value) {
        this.value = value;
    }

    @Override
    public boolean matches(ValueType type) {
        return type == ValueType.NUMBER || type == ValueType.WIDE;
    }

    @Override
    public long longValue() {
        return (long) value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public int intValue() {
        return (int) value;
    }

    @Override
    public float floatValue() {
        return (float) value;
    }

    @Override
    public short shortValue() {
        return (short) value;
    }

    @Override
    public char charValue() {
        return (char) value;
    }

    @Override
    public byte byteValue() {
        return (byte) value;
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

    @Override
    public String toString() {
        return "double{" +
                "value=" + value +
                '}';
    }
}
