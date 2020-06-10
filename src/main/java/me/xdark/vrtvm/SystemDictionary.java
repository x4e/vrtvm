package me.xdark.vrtvm;

import me.xdark.vrtvm.mirror.JavaClass;

import java.util.Hashtable;
import java.util.Objects;

public final class SystemDictionary {
    private final Hashtable<DictionaryEntry, JavaClass> table =new Hashtable<>(1024);

    public JavaClass getLinkedClass(DictionaryEntry entry) {
        return table.get(entry);
    }

    public void linkClass(DictionaryEntry entry, JavaClass jclas) {
        table.put(entry, jclas);
    }

    public static class DictionaryEntry {
        final JavaValue loader;
        final String className;

        public DictionaryEntry(JavaValue loader, String className) {
            this.loader = loader;
            this.className = className;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DictionaryEntry that = (DictionaryEntry) o;
            return Objects.equals(loader, that.loader) &&
                    Objects.equals(className, that.className);
        }

        @Override
        public int hashCode() {
            return Objects.hash(loader, className);
        }
    }
}
