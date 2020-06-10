package me.xdark.vrtvm;

import java.util.Arrays;

public final class VMLocals {
    private final JavaValue[] locals;

    public VMLocals(int size, JavaValue dflt) {
        Arrays.fill(this.locals = new JavaValue[size], dflt);
    }

    public void store(int index, JavaValue value){
        locals[index] = value;
    }

    public <V extends JavaValue> V load(int index){
        return (V) locals[index];
    }
}
