package com.globallogic;

import java.util.concurrent.atomic.AtomicInteger;

public class ComperableAtomicInteger extends AtomicInteger implements Comparable<ComperableAtomicInteger> {
    @Override
    public int compareTo(ComperableAtomicInteger o) {
        return get() - o.get();
    }
}
