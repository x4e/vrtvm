package me.xdark.vrtvm.tests.classfiles;

import me.xdark.vrtvm.*;
import me.xdark.vrtvm.mirror.JavaClass;
import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.Opcodes.*;

public class HelloWorldGenerator {
    public static void generate(ClassWriter cw) {
        cw.visit(
            V1_8,
            ACC_PUBLIC,
            "me/xdark/vrtvm/tests/HelloWorld",
            null,
            "java/lang/Object",
            null
        );

        {
            MethodVisitor mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "run", "()V", null, null);
            mv.visitCode();
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("Hello, World!");
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            mv.visitEnd();
        }

        cw.visitEnd();
    }
}
