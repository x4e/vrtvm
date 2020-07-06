package me.xdark.vrtvm.mirror;

import me.xdark.vrtvm.JavaValue;
import me.xdark.vrtvm.MemberDeclaration;
import me.xdark.vrtvm.VM;
import org.objectweb.asm.tree.ClassNode;

import java.lang.reflect.Modifier;

public final class InstanceClass implements JavaClass {
    private final VM vm;
    private final JavaValue classLoader;
    private final ClassNode classNode;
    private JavaValue protectionDomain;
    private JavaClass superclass;
    private JavaClass[] interfaces;
    private JavaValue handle;

    public InstanceClass(VM vm, JavaValue classLoader, ClassNode classNode) {
        this.vm = vm;
        this.classLoader = classLoader;
        this.classNode = classNode;
    }

    @Override
    public String getName() {
        return classNode.name.replace('/', '.');
    }

    @Override
    public String getInternalName() {
        return 'L' + classNode.name + ';';
    }

    @Override
    public String getSourceFile() {
        return classNode.sourceFile;
    }

    @Override
    public String getSourceDebug() {
        return classNode.sourceDebug;
    }

    @Override
    public boolean isInstance(JavaValue value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAssignableFrom(JavaClass cls) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isInterface() {
        return Modifier.isInterface(classNode.access);
    }

    @Override
    public boolean isArray() {
        return false;
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public JavaClass getSuperclass() {
        return superclass;
    }

    @Override
    public JavaClass[] getInterfaces() {
        return interfaces;
    }

    @Override
    public JavaClass getComponentType() {
        return null;
    }

    @Override
    public int getModifiers() {
        return classNode.access;
    }

    @Override
    public JavaValue[] getSigners() {
        return new JavaValue[0];
    }

    @Override
    public void setSigners(JavaValue[] signers) { }

    @Override
    public JavaValue[] getEnclosingMethod() {
        return new JavaValue[0];
    }

    @Override
    public JavaClass getDeclaringClass() {
        return null;
    }

    @Override
    public void setProtectionDomain(JavaValue protectionDomain) {
        this.protectionDomain = protectionDomain;
    }

    @Override
    public JavaValue getProtectionDomain() {
        return protectionDomain;
    }

    @Override
    public String getGenericSignature() {
        return null;
    }

    @Override
    public byte[] getRawAnnotations() {
        return new byte[0];
    }

    @Override
    public byte[] getRawTypeAnnotations() {
        return new byte[0];
    }

    @Override
    public JavaValue getConstantPool() {
        return null;
    }

    @Override
    public JavaValue[] getDeclaredFields(boolean publicOnly) {
        return new JavaValue[0];
    }

    @Override
    public JavaValue[] getDeclaredMethods(boolean publicOnly) {
        return new JavaValue[0];
    }

    @Override
    public JavaValue[] getDeclaredConstructors(boolean publicOnly) {
        return new JavaValue[0];
    }

    @Override
    public JavaClass[] getDeclaredClasses() {
        return new JavaClass[0];
    }

    @Override
    public JavaMethod getMethod(MemberDeclaration declaration) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public JavaValue getHandle() {
        return handle;
    }

    @Override
    public void setHandle(JavaValue handle) {
        this.handle = handle;
    }

    @Override
    public JavaClass newArrayClass(int dimension) {
        String name = getInternalName();
        StringBuilder builder = new StringBuilder(dimension + name.length());
        for (int i = 0; i < dimension; i++) {
            builder.append('[');
        }
        String arrayName = builder.append(name).toString();
        return new ArrayClass(arrayName, protectionDomain, this, vm.objectClass());
    }

    @Override
    public void resolve() { }
}
