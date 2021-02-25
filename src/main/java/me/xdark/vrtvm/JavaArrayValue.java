package me.xdark.vrtvm;

import me.xdark.vrtvm.mirror.JavaClass;

import java.util.Arrays;

public final class JavaArrayValue extends JavaValue {
    private final JavaValue[] backing;
    private JavaClass jClass;

    public JavaArrayValue(int size, JavaValue dflt) {
        Arrays.fill(this.backing = new JavaValue[size], dflt);
    }

    public JavaArrayValue(JavaValue[] backing) {
        this.backing = backing;
    }

    public JavaValue get(int index) {
        return backing[index];
    }

    public void set(int index, JavaValue value) {
        backing[index] = value;
    }

    public int length() {
        return backing.length;
    }

    @Override
    public boolean matches(ValueType type) {
        return type == ValueType.OBJECT || type == ValueType.ARRAY;
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
