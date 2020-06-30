package me.xdark.vrtvm.mirror;

import me.xdark.vrtvm.JavaValue;

public interface JavaClass {
    /**
     * ByteBuffer buf = ByteBuffer.wrap(rawAnnotations);
     * int numAnnotations = buf.getShort() & 0xFFFF;
     */
    byte[] RAW_ANNOTATIONS = new byte[]{0, 0};

    String getName();

    String getInternalName();

    boolean isInstance(JavaValue value);

    boolean isAssignableFrom(JavaClass cls);

    boolean isInterface();

    boolean isArray();

    boolean isPrimitive();

    JavaClass getSuperclass();

    JavaClass[] getInterfaces();

    JavaClass getComponentType();

    int getModifiers();

    JavaValue[] getSigners();

    void setSigners(JavaValue[] signers);

    JavaValue[] getEnclosingMethod();

    JavaClass getDeclaringClass();

    JavaValue getProtectionDomain();

    String getGenericSignature();

    byte[] getRawAnnotations();

    byte[] getRawTypeAnnotations();

    JavaValue getConstantPool();

    JavaValue[] getDeclaredFields(boolean publicOnly);

    JavaValue[] getDeclaredMethods(boolean publicOnly);

    JavaValue[] getDeclaredConstructors(boolean publicOnly);

    JavaClass[] getDeclaredClasses();

    JavaValue getHandle();

    void setHandle(JavaValue handle);

    JavaClass newArrayClass(int dimension);

    void resolve();
}
