package com.vitosak.utils;

public class Pair<T,V>{
    public T first;
    public V second;
    private Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }
    public static <T,V> Pair<T,V> of(T first, V second) {
        return new Pair<T,V>(first, second);
    }
}
