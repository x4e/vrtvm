package me.xdark.vrtvm;

import me.xdark.vrtvm.mirror.JavaClass;

public abstract class JavaValue implements ReferenceCounted {
    protected final VM vm;
    private int refCount;

    protected JavaValue(VM vm) {
        this.vm = vm;
    }

    public abstract boolean matches(ValueType type);

    public abstract long longValue();

    public abstract double doubleValue();

    public abstract int intValue();

    public abstract float floatValue();

    public abstract short shortValue();

    public abstract char charValue();

    public abstract byte byteValue();

    public abstract boolean booleanValue();

    public abstract void push(VMStack stack);

    public abstract JavaClass getJClass();

    // INTERNAL USAGE ONLY
    abstract void setJClass(JavaClass jClass);
    //


    @Override
    public JavaValue retain(int count) {
        refCount +=count;
        return this;
    }

    @Override
    public JavaValue release(int count) {
        refCount -= count;
        return this;
    }

    @Override
    public int refCount() {
        return refCount;
    }
}
