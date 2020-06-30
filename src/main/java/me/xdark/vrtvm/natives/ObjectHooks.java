package me.xdark.vrtvm.natives;

import me.xdark.vrtvm.MemberDeclaration;
import me.xdark.vrtvm.VM;

public final class ObjectHooks {
    private ObjectHooks() { }

    public static void setup(VM vm) {
        vm.installHook(newDeclaration("registerNatives", "()V"), ctx -> {
            //Call to registerNatives causes JVM to register getClass/hashCode native methods. We don't need that.
            return ctx.vm.voidValue();
        });
        vm.installHook(newDeclaration("getClass", "()Ljava/lang/Class;"), ctx -> {
            return ctx.locals.load(0).getJClass().getHandle();
        });
        vm.installHook(newDeclaration("hashCode", "()I"), ctx -> {
            return ctx.vm.newInt(ctx.locals.load(0).hashCode());
        });
        // TODO clone, notify, notifyAll, wait
    }

    private static MemberDeclaration newDeclaration(String name, String desc) {
        return new MemberDeclaration("java/lang/Object", name, desc);
    }
}
