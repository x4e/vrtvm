package me.xdark.vrtvm;

public final class ContextExitSignal extends Error {
    public static final ContextExitSignal INSTANCE = new ContextExitSignal();

    private ContextExitSignal() { }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public Throwable initCause(Throwable cause) {
        return this;
    }
}
