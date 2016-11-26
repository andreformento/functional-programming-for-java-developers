package com.formento.functional.programming.forjavadevelopers.chapter3.list;

public interface MyList<T> {

    T head();

    MyList<T> tail();

    boolean isEmpty();

    MyList<T> filter(Function1<T, Boolean> f);

    <T2> MyList<T2> map(Function1<T, T2> f);

    <T2> T2 foldLeft(T2 seed, Function2<T2, T, T2> f);

    <T2> T2 foldRight(T2 seed, Function2<T, T2, T2> f);

    void foreach(Function1Void<T> f);

}
