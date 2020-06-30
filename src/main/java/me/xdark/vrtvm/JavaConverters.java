package me.xdark.vrtvm;

public final class JavaConverters {
    private JavaConverters() { }

    public static long[] vmToJavaLongs(JavaArrayValue arrayValue) {
        int j = arrayValue.length();
        long[] longs = new long[j];
        while (j-- > 0) {
            longs[j] = arrayValue.get(j).longValue();
        }
        return longs;
    }

    public static double[] vmToJavaDoubles(JavaArrayValue arrayValue) {
        int j = arrayValue.length();
        double[] doubles = new double[j];
        while (j-- > 0) {
            doubles[j] = arrayValue.get(j).doubleValue();
        }
        return doubles;
    }

    public static int[] vmToJavaInts(JavaArrayValue arrayValue) {
        int j = arrayValue.length();
        int[] ints = new int[j];
        while (j-- > 0) {
            ints[j] = arrayValue.get(j).intValue();
        }
        return ints;
    }

    public static float[] vmToJavaFloats(JavaArrayValue arrayValue) {
        int j = arrayValue.length();
        float[] floats = new float[j];
        while (j-- > 0) {
            floats[j] = arrayValue.get(j).floatValue();
        }
        return floats;
    }

    public static short[] vmToJavaShorts(JavaArrayValue arrayValue) {
        int j = arrayValue.length();
        short[] shorts = new short[j];
        while (j-- > 0) {
            shorts[j] = arrayValue.get(j).shortValue();
        }
        return shorts;
    }

    public static char[] vmToJavaChars(JavaArrayValue arrayValue) {
        int j = arrayValue.length();
        char[] chars = new char[j];
        while (j-- > 0) {
            chars[j] = arrayValue.get(j).charValue();
        }
        return chars;
    }

    public static byte[] vmToJavaBytes(JavaArrayValue arrayValue) {
        int j = arrayValue.length();
        byte[] bytes = new byte[j];
        while (j-- > 0) {
            bytes[j] = arrayValue.get(j).byteValue();
        }
        return bytes;
    }

    public static boolean[] vmToJavaBooleans(JavaArrayValue arrayValue) {
        int j = arrayValue.length();
        boolean[] booleans = new boolean[j];
        while (j-- > 0) {
            booleans[j] = arrayValue.get(j).booleanValue();
        }
        return booleans;
    }

    public static String vmToJavaString(JavaValue javaValue) {
        throw new UnsupportedOperationException();
    }
}
