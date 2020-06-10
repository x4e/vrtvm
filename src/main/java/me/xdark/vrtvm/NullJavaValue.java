package me.xdark.vrtvm;

public final class NullJavaValue extends InternalJavaValue {
    public NullJavaValue(VM vm) {
        super(vm);
    }

    @Override
    public boolean matches(ValueType type) {
        return type == ValueType.NULL;
    }

    @Override
    public String toString() {
        return "null{}";
    }
}
