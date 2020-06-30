package me.xdark.vrtvm.natives;

import me.xdark.vrtvm.MemberDeclaration;
import me.xdark.vrtvm.VM;
import me.xdark.vrtvm.mirror.JavaClass;

public final class ClassHooks {
    private ClassHooks() { }

    public static void setup(VM vm) {
        vm.installHook(newDeclaration("isInstance", "(Ljava/lang/Object;)Z"), ctx -> {
            return ctx.vm.newBoolean(ctx.handle.getJClass().isInstance(ctx.locals.load(1)));
        });
        vm.installHook(newDeclaration("isAssignableFrom", "(Ljava/lang/Class;)Z"), ctx -> {
            return ctx.vm.newBoolean(ctx.handle.getJClass().isAssignableFrom(ctx.locals.load(1).getJClass()));
        });
        vm.installHook(newDeclaration("isInterface", "()Z"), ctx -> {
            return ctx.vm.newBoolean(ctx.handle.getJClass().isInterface());
        });
        vm.installHook(newDeclaration("isArray", "()Z"), ctx -> {
            return ctx.vm.newBoolean(ctx.handle.getJClass().isArray());
        });
        vm.installHook(newDeclaration("isPrimitive", "()Z"), ctx -> {
            return ctx.vm.newBoolean(ctx.handle.getJClass().isPrimitive());
        });
        vm.installHook(newDeclaration("getName0", "()Ljava/lang/String;"), ctx -> {
            return ctx.vm.newString(ctx.handle.getJClass().getName());
        });
        vm.installHook(newDeclaration("getSuperclass", "()Ljava/lang/Class;"), ctx -> {
            JavaClass superclass = ctx.handle.getJClass().getSuperclass();
            return superclass == null ? ctx.vm.nullValue() : superclass.getHandle();
        });
        vm.installHook(newDeclaration("getInterfaces0", "()[Ljava/lang/Class;"), ctx -> {
            return ctx.vm.newString(ctx.handle.getJClass().getName());
        });
    }

    private static MemberDeclaration newDeclaration(String name, String desc) {
        return new MemberDeclaration("java/lang/Class", name, desc);
    }
}
