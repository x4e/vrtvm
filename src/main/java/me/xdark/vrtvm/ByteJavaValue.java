package me.xdark.vrtvm;

import me.xdark.vrtvm.mirror.JavaClass;

public final class ByteJavaValue extends JavaValue {
    private final byte value;

    protected ByteJavaValue(byte value) {
        this.value = value;
    }

    @Override
    public boolean matches(ValueType type) {
        return type == ValueType.NUMBER;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public short shortValue() {
        return value;
    }

    @Override
    public char charValue() {
        return (char) value;
    }

    @Override
    public byte byteValue() {
        return value;
    }

    @Override
    public boolean booleanValue() {
        int value = this.value;
        if (value == 1) return true;
        else if (value == 0) return false;
        else throw new IllegalStateException();
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
        return "byte{" +
                "value=" + value +
                '}';
    }
}
