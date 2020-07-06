package me.xdark.vrtvm.logging;

public interface VMLogger {
    void debug(String message, Object... args);

    void debug(String message);

    void info(String message, Object... args);

    void info(String message);

    void warn(String message, Object... args);

    void warn(String message);

    void error(String message, Object... args);

    void error(String message);

    void panic(String message, Object... args);

    void panic(String message);
}
