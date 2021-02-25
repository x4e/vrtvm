package me.xdark.vrtvm.tests;

import me.xdark.vrtvm.JavaObject;
import me.xdark.vrtvm.MemberDeclaration;
import me.xdark.vrtvm.VM;
import me.xdark.vrtvm.VMThread;
import me.xdark.vrtvm.mirror.JavaClass;
import me.xdark.vrtvm.tests.classfiles.HelloWorldGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassWriter;

class ClassFileTests {
    @Test
    @DisplayName("Hello World")
    void testHelloWorld() throws Throwable {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        HelloWorldGenerator.generate(cw);
        run(cw.toByteArray());
    }

    private void run(byte[] classFile) throws InterruptedException {
        VM vm = new VM();
        JavaClass clazz =
            vm.defineClass(new JavaObject(), new JavaObject(), null, classFile, 0, classFile.length);
        JavaObject instance = new JavaObject();
        instance.setJClass(clazz);

        VMThread thread = new VMThread(vm, instance, new MemberDeclaration("run", "()V"));
        thread.start();
        thread.wait();
    }
}
