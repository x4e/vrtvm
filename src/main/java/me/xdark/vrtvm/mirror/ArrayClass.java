package me.xdark.vrtvm.mirror;

import me.xdark.vrtvm.JavaValue;

public final class ArrayClass implements JavaClass {
    private final String name;
    private final JavaValue protectionDomain;
    private final JavaClass componentType;
    private final JavaClass objectClass;
    private JavaValue handle;

    public ArrayClass(String name, JavaValue protectionDomain, JavaClass componentType, JavaClass objectClass) {
        this.name = name;
        this.protectionDomain = protectionDomain;
        this.componentType = componentType;
        this.objectClass = objectClass;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getInternalName() {
        return name;
    }

    @Override
    public boolean isInstance(JavaValue value) {
        return value.getJClass() == this;
    }

    @Override
    public boolean isAssignableFrom(JavaClass cls) {
        return false;
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public boolean isArray() {
        return true;
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }

    @Override
    public JavaClass getSuperclass() {
        return objectClass;
    }

    @Override
    public JavaClass[] getInterfaces() {
        return new JavaClass[0];
    }

    @Override
    public JavaClass getComponentType() {
        return componentType;
    }

    @Override
    public int getModifiers() {
        return 1041;
    }

    @Override
    public JavaValue[] getSigners() {
        return new JavaValue[0];
    }

    @Override
    public void setSigners(JavaValue[] signers) {
    }

    @Override
    public JavaValue[] getEnclosingMethod() {
        return new JavaValue[0];
    }

    @Override
    public JavaClass getDeclaringClass() {
        return null;
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
        return RAW_ANNOTATIONS;
    }

    @Override
    public byte[] getRawTypeAnnotations() {
        return RAW_ANNOTATIONS;
    }

    @Override
    public JavaValue getConstantPool() {
        throw new UnsupportedOperationException();
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
    public JavaValue getHandle() {
        return handle;
    }

    @Override
    public void setHandle(JavaValue handle) {
        this.handle = handle;
    }
}
