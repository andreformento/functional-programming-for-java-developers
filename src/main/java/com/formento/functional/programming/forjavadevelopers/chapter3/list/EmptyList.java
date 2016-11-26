package com.formento.functional.programming.forjavadevelopers.chapter3.list;

import com.formento.functional.programming.forjavadevelopers.chapter3.exception.EmptyListHasNoHead;
import com.formento.functional.programming.forjavadevelopers.chapter3.exception.EmptyListHasNoTail;

public final class EmptyList implements MyList<Object> {
    private static final EmptyList instance = new EmptyList();

    @SuppressWarnings(value = "unchecked")
    public static <T> MyList<T> emptyList() {
        return (MyList<T>) instance; // dangerous?!
    }

    private EmptyList() {
    }

    public Object head() {
        throw new EmptyListHasNoHead();
    }

    public MyList<Object> tail() {
        throw new EmptyListHasNoTail();
    }

    public boolean isEmpty() {
        return true;
    }

    @Override
    public MyList<Object> filter(Function1<Object, Boolean> f) {
        return this;
    }

    @Override
    public <T2> MyList<T2> map(Function1<Object, T2> f) {
        return emptyList();
    }

    @Override
    public <T2> T2 foldLeft(T2 seed, Function2<T2, Object, T2> f) {
        return seed;
    }

    @Override
    public <T2> T2 foldRight(T2 seed, Function2<Object, T2, T2> f) {
        return seed;
    }

    @Override
    public void foreach(Function1Void<Object> f) {

    }

    @Override
    public String toString() {
        return "()";
    }

}
