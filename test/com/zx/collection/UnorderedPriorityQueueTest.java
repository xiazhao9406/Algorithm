package com.zx.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnorderedPriorityQueueTest {
    PriorityQueue<Integer> unorderedPQ;

    @BeforeEach
    void setUp() {
        unorderedPQ = new UnorderedPriorityQueue<>();
    }

    @Test
    void insertSomeItems_thenCallIsEmptyAndSize_returnFalseAndCorrectSize() {
        unorderedPQ.insert(2);
        unorderedPQ.insert(4);
        unorderedPQ.insert(7);
        unorderedPQ.insert(8);
        unorderedPQ.insert(1);
        unorderedPQ.insert(6);
        unorderedPQ.insert(3);
        unorderedPQ.insert(5);
        assertFalse(unorderedPQ.isEmpty());
        assertEquals(8, unorderedPQ.size());
    }

    @Test
    void isEmpty_thenCallDelMAxAndMax_throwException() {
        assertThrows(IllegalStateException.class, () -> unorderedPQ.delMax());
        assertThrows(IllegalStateException.class, () -> unorderedPQ.max());
    }

    @Test
    void insertSomeItems_thenCallMax_equalMAx() {
        unorderedPQ.insert(2);
        unorderedPQ.insert(4);
        unorderedPQ.insert(7);
        unorderedPQ.insert(8);
        unorderedPQ.insert(1);
        unorderedPQ.insert(6);
        unorderedPQ.insert(3);
        unorderedPQ.insert(5);
        assertEquals(8, unorderedPQ.max());
        assertEquals(8, unorderedPQ.delMax());
        assertEquals(7, unorderedPQ.max());
        unorderedPQ.insert(9);
        assertEquals(9, unorderedPQ.delMax());
        assertEquals(7, unorderedPQ.max());
    }
}