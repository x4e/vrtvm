package me.xdark.vrtvm;

public interface ReferenceCounted {
    ReferenceCounted retain(int count);

    ReferenceCounted release(int count);

    int refCount();
}
