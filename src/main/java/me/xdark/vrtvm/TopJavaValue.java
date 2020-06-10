package me.xdark.vrtvm;

public final class TopJavaValue extends InternalJavaValue {
    public TopJavaValue(VM vm) {
        super(vm);
    }

    @Override
    public boolean matches(ValueType type) {
        return type==ValueType.TOP;
    }

    @Override
    public String toString() {
        return "top{}";
    }
}
