package com.zx.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedPriorityQueueTest {
    PriorityQueue<Integer> orderedPQ;

    @BeforeEach
    void setUp() {
        orderedPQ = new OrderedPriorityQueue<>();
    }

    @Test
    void insertSomeItems_thenCallIsEmptyAndSize_returnFalseAndCorrectSize() {
        orderedPQ.insert(2);
        orderedPQ.insert(4);
        orderedPQ.insert(7);
        orderedPQ.insert(8);
        orderedPQ.insert(1);
        orderedPQ.insert(6);
        orderedPQ.insert(3);
        orderedPQ.insert(5);
        assertFalse(orderedPQ.isEmpty());
        assertEquals(8, orderedPQ.size());
    }

    @Test
    void isEmpty_thenCallDelMAxAndMax_throwException() {
        assertThrows(IllegalStateException.class, () -> orderedPQ.delMax());
        assertThrows(IllegalStateException.class, () -> orderedPQ.max());
    }

    @Test
    void insertSomeItems_thenCallMax_equalMAx() {
        orderedPQ.insert(2);
        orderedPQ.insert(4);
        orderedPQ.insert(7);
        orderedPQ.insert(8);
        orderedPQ.insert(1);
        orderedPQ.insert(6);
        orderedPQ.insert(3);
        orderedPQ.insert(5);
        assertEquals(8, orderedPQ.max());
        assertEquals(8, orderedPQ.delMax());
        assertEquals(7, orderedPQ.max());
        orderedPQ.insert(9);
        assertEquals(9, orderedPQ.delMax());
        assertEquals(7, orderedPQ.max());
    }

}