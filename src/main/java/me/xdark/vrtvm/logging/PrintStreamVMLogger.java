package me.xdark.vrtvm.logging;

import java.io.PrintStream;

public final class PrintStreamVMLogger implements VMLogger {
    private final PrintStream printStream;

    public PrintStreamVMLogger(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void debug(String message, Object... args) {
        printStream.println(format("debug", message, args));
    }

    @Override
    public void debug(String message) {
        printStream.println(format("debug", message));
    }

    @Override
    public void info(String message, Object... args) {
        printStream.println(format("info", message, args));
    }

    @Override
    public void info(String message) {
        printStream.println(format("info", message));
    }

    @Override
    public void warn(String message, Object... args) {
        printStream.println(format("warn", message, args));
    }

    @Override
    public void warn(String message) {
        printStream.println(format("warn", message));
    }

    @Override
    public void error(String message, Object... args) {
        printStream.println(format("error", message, args));
    }

    @Override
    public void error(String message) {
        printStream.println(format("error", message));
    }

    @Override
    public void panic(String message, Object... args) {
        printStream.println(format("panic", message, args));
    }

    @Override
    public void panic(String message) {
        printStream.println(format("panic", message));
    }

    private String format(String level, String message, Object... args) {
        return String.format("[%s] %s", level, String.format(message, args));
    }
}
