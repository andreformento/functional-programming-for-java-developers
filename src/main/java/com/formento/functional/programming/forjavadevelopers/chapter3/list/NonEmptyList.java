package com.formento.functional.programming.forjavadevelopers.chapter3.list;

public final class NonEmptyList<T> implements MyList<T> {
    private final T _head;
    private final MyList<T> _tail;

    public static <T> MyList<T> list(T head, MyList<T> tail) {
        return new NonEmptyList<T>(head, tail);
    }

    private NonEmptyList(T head, MyList<T> tail) {
        this._head = head;
        this._tail = tail;
    }

    public T head() {
        return _head;
    }

    public MyList<T> tail() {
        return _tail;
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public MyList<T> filter(Function1<T, Boolean> f) {
        return f.apply(head()) ? list(head(), tail().filter(f)) : tail().filter(f);
    }

    @Override
    public <T2> MyList<T2> map(Function1<T, T2> f) {
        return list(f.apply(head()), tail().map(f));
    }

    @Override
    public <T2> T2 foldLeft(T2 seed, Function2<T2, T, T2> f) {
        return tail().foldLeft(f.apply(seed, head()), f);
    }

    @Override
    public <T2> T2 foldRight(T2 seed, Function2<T, T2, T2> f) {
        return f.apply(head(), tail().foldRight(seed, f));
    }

    @Override
    public void foreach(Function1Void<T> f) {
        f.apply(head());
        tail().foreach(f);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NonEmptyList<?> that = (NonEmptyList<?>) o;

        return _head.equals(that._head) && _tail.equals(that._tail);

    }

    @Override
    public int hashCode() {
        int result = _head.hashCode();
        result = 31 * result + _tail.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "(" + head() + ", " + tail() + ")";
    }

}
