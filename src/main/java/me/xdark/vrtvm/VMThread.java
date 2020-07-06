package me.xdark.vrtvm;

import java.util.LinkedList;

public final class VMThread extends Thread {
    private final VM vm;
    private final JavaObject handle;
    private final LinkedList<StackTraceElement> stack = new LinkedList<>();

    public VMThread(VM vm, JavaObject handle) {
        this.vm = vm;
        this.handle = handle;
    }

    public StackTraceElement pop() {
        return stack.pop();
    }

    public void pushEntry(StackTraceElement element) {
        stack.push(element);
    }

    public LinkedList<StackTraceElement> captureStack() {
        return new LinkedList<>(stack);
    }

    @Override
    public void run() {
        VM vm = this.vm;
        vm.registerThread(this);
        try {
            vm.executeResolve(handle, new MemberDeclaration("run", "()V"), true);
        } finally {
            vm.unregisterThread(this);
            if (!stack.isEmpty()) {
                vm.panic("thread stack corruption");
            }
        }
    }
}
