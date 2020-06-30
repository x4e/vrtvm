package me.xdark.vrtvm.natives;

import me.xdark.vrtvm.*;
import me.xdark.vrtvm.mirror.JavaClass;

public final class ClassLoaderHooks {
    private ClassLoaderHooks() { }

    public static void setup(VM vm) {
        vm.installHook(newDeclaration("defineClass0", "(Ljava/lang/String;[BIILjava/security/ProtectionDomain;)Ljava/lang/Class;"), ctx -> {
            VMLocals locals = ctx.locals;
            String className = JavaConverters.vmToJavaString(locals.load(1));
            byte[] bytes = JavaConverters.vmToJavaBytes(locals.load(2));
            int off = locals.load(3).intValue();
            int len = locals.load(4).intValue();
            JavaValue pd = locals.load(5);
            return vm.defineClass(locals.load(0), pd, className, bytes, off, len).getHandle();
        });
        vm.installHook(newDeclaration("defineClass0", "(Ljava/lang/String;[BIILjava/security/ProtectionDomain;Ljava/lang/String;)Ljava/lang/Class;"), ctx -> {
            VMLocals locals = ctx.locals;
            String className = JavaConverters.vmToJavaString(locals.load(1));
            byte[] bytes = JavaConverters.vmToJavaBytes(locals.load(2));
            int off = locals.load(3).intValue();
            int len = locals.load(4).intValue();
            JavaValue pd = locals.load(5);
            return vm.defineClass(locals.load(0), pd, className, bytes, off, len).getHandle();
        });
        // TODO
        //     private native Class<?> defineClass2(String name, java.nio.ByteBuffer b,
        //                                         int off, int len, ProtectionDomain pd,
        //                                         String source);
        vm.installHook(newDeclaration("resolveClass0", "(Ljava/lang/Class;)V"), ctx -> {
            vm.resolveClass(ctx.locals.load(1).getJClass());
            return vm.voidValue();
        });
        vm.installHook(newDeclaration("findBootstrapClass", "(Ljava/lang/String;)Ljava/lang/Class;"), ctx -> {
            JavaClass jClass = ctx.vm.findLoadedClass(vm.nullValue(), JavaConverters.vmToJavaString(ctx.locals.load(1)));
            return jClass != null ? jClass.getHandle() : vm.nullValue();
        });
        vm.installHook(newDeclaration("findLoadedClass0", "(Ljava/lang/String;)Ljava/lang/Class;"), ctx -> {
            JavaClass jClass = ctx.vm.findLoadedClass(ctx.locals.load(0), JavaConverters.vmToJavaString(ctx.locals.load(1)));
            return jClass != null ? jClass.getHandle() : vm.nullValue();
        });
        // TODo findBuiltinLib
    }

    private static MemberDeclaration newDeclaration(String name, String desc) {
        return new MemberDeclaration("java/lang/ClassLoader", name, desc);
    }
}
