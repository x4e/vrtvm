package me.xdark.vrtvm;

public final class NullJavaValue extends InternalJavaValue {
    @Override
    public boolean matches(ValueType type) {
        return type == ValueType.NULL;
    }

    @Override
    public String toString() {
        return "null{}";
    }
}
