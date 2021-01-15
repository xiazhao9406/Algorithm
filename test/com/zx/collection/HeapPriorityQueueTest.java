package com.zx.collection;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class HeapPriorityQueueTest {

    private PriorityQueue<Integer> pq;

    @Test
    void addItemsToPriorityQueue_thendelMax_correctValueReturned() {
        pq = new HeapPriorityQueue<>();
        assertEquals(0, pq.size());
        assertTrue(pq.isEmpty());

        for (int i = 0; i < 5; i++) {
            pq.insert(i);
        }
        assertEquals(5, pq.size());
        assertFalse(pq.isEmpty());
        assertEquals(4, pq.max());
        assertEquals(4, pq.delMax());
        assertEquals(3, pq.max());
        assertEquals(3, pq.delMax());

        for (int i = 0; i < 5; i++) {
            pq.insert(i);
        }
        assertEquals(8, pq.size());
        assertFalse(pq.isEmpty());
        assertEquals(4, pq.max());
        assertEquals(4, pq.delMax());
        assertEquals(3, pq.max());
        assertEquals(3, pq.delMax());
    }

    @Test
    void addItemsToMinPriorityQueue_thendelMax_correctValueReturned() {
        pq = new HeapPriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        assertEquals(0, pq.size());
        assertTrue(pq.isEmpty());

        for (int i = 0; i < 100; i++) {
            pq.insert(i);
        }
        assertEquals(100, pq.size());
        assertFalse(pq.isEmpty());
        assertEquals(0, pq.max());
        assertEquals(0, pq.delMax());
        assertEquals(1, pq.max());
        assertEquals(1, pq.delMax());

        for (int i = 0; i < 100; i++) {
            pq.insert(i);
        }
        assertEquals(198, pq.size());
        assertFalse(pq.isEmpty());
        assertEquals(0, pq.max());
        assertEquals(0, pq.delMax());
        assertEquals(1, pq.max());
        assertEquals(1, pq.delMax());

        while (!pq.isEmpty()) pq.delMax();
    }

    @Test
    void testPriorityQueueWithInitialArray_thenCalldelMax_correctOrderReturned() {
        final Integer[] integers = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
        final PriorityQueue<Integer> pq = new HeapPriorityQueue<>(integers);
        assertEquals(10, pq.size());
        assertFalse(pq.isEmpty());
        for (int i = 10; i > 0; i--) {
            assertEquals(i, pq.max());
            assertEquals(i, pq.delMax());
        }
    }

    @Test
    void testPriorityQueueWithInitialArrayWithDecedentOrder_thenCalldelMax_correctOrderReturned() {
        final Integer[] integers = {1, 3, 5, 7, 9, 2, 4, 6, 8, 10};
        final PriorityQueue<Integer> pq = new HeapPriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        }, integers);
        assertEquals(10, pq.size());
        assertFalse(pq.isEmpty());
        for (int i = 1; i <= 10; i++) {
            assertEquals(i, pq.max());
            assertEquals(i, pq.delMax());
        }
    }

}