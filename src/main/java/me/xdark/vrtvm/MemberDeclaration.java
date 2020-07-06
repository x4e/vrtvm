package me.xdark.vrtvm;

import java.util.Objects;

public final class MemberDeclaration {
    private final String owner, name, desc;

    public MemberDeclaration(String owner, String name, String desc) {
        this.owner = owner;
        this.name = name;
        this.desc = desc;
    }

    public MemberDeclaration(String name, String desc) {
        this(null, name, desc);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MemberDeclaration that = (MemberDeclaration) o;
        return Objects.equals(owner, that.owner) &&
                Objects.equals(name, that.name) &&
                Objects.equals(desc, that.desc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, name, desc);
    }
}
