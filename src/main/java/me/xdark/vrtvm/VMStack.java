package me.xdark.vrtvm;

import java.util.Arrays;

public final class VMStack {
    private final JavaValue[] stack;
    private final JavaValue dflt;
    private final JavaValue top;
    private int cursor;

    public VMStack(int size, JavaValue dflt, JavaValue top) {
        this.top = top;
        Arrays.fill(stack = new JavaValue[size], this.dflt = dflt);
    }

    public void push(JavaValue value) {
        value.push(this);
    }

    public void pushTop(JavaValue value) {
        push(value);
        push(top);
    }

    public void doPush(JavaValue value) {
        stack[cursor++] = value;
    }

    public <V extends JavaValue> V pop() {
        int cursor = --this.cursor;
        JavaValue v = stack[cursor];
        stack[cursor] = dflt;
        return (V) v;
    }

    public <V extends JavaValue> V popTop() {
        JavaValue value = pop();
        if (!value.matches(ValueType.TOP)) {
            throw new IllegalStateException("Expected to pop top value, but got: " + value);
        }
        return pop();
    }

    public boolean isEmpty() {
        return cursor == 0;
    }
}
