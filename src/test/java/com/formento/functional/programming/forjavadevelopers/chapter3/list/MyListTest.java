package com.formento.functional.programming.forjavadevelopers.chapter3.list;

import com.formento.functional.programming.forjavadevelopers.chapter3.exception.EmptyListHasNoHead;
import com.formento.functional.programming.forjavadevelopers.chapter3.exception.EmptyListHasNoTail;
import org.junit.Test;

import static com.formento.functional.programming.forjavadevelopers.chapter3.list.NonEmptyList.list;
import static org.junit.Assert.assertEquals;

public class MyListTest {

    private final MyList<String> EMPTYLS = EmptyList.emptyList();
    private final MyList<Long> EMPTYLL = EmptyList.emptyList();

    @Test(expected = EmptyListHasNoHead.class)
    public void callingHeadOnAnEmptyListRaises() {
        EMPTYLS.head();
    }

    @Test(expected = EmptyListHasNoTail.class)
    public void callingTailOnAnEmptyListRaises() {
        EMPTYLS.tail();
    }

    @Test
    public void callingTailOnAListWithMultipleElementsReturnsANonEmptyList() {
        MyList<String> tail = list("one", list("two", EMPTYLS)).tail();
        assertEquals(list("two", EMPTYLS), tail);
    }

    @Test
    public void callingHeadOnANonEmptyListReturnsTheHead() {
        final String head = list("one", EMPTYLS).head();
        assertEquals("one", head);
    }

    @Test
    public void allEmptyListsAreEqual() {
        assertEquals(EMPTYLL, EMPTYLS);
    }

    @Test
    public void listAreRecursiveStructures() {
        MyList<String> list1 = list("one", list("two", list("three", EMPTYLS)));
        assertEquals("(one, (two, (three, ())))", list1.toString());
    }

    @Test
    public void higherOrderFunctionCombinatorExample() {
        MyList<Integer> listI = list(1, list(2, list(3, list(4, list(5, list(6, EmptyList.emptyList()))))));
        Integer sum = listI
                .filter(new Function1<Integer, Boolean>() {
                    public Boolean apply(Integer i) {
                        return i % 2 == 0;
                    }
                })
                .map(new Function1<Integer, Integer>() {
                    public Integer apply(Integer i) {
                        return i * 2;
                    }
                })
                .foldLeft(0, new Function2<Integer, Integer, Integer>() {
                    public Integer apply(Integer seed, Integer item) {
                        return seed + item;
                    }
                });

        assertEquals(new Integer(24), sum);
    }

}
