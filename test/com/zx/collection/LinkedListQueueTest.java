package com.zx.collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListQueueTest {
    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new LinkedListQueue<>();
    }

    @Test
    void enqueueAndDequeueItemsFromQueue_thenCallIsEmpty_correctValueReturned() {
        assertTrue(queue.isEmpty());
        queue.enqueue(34);
        assertFalse(queue.isEmpty());
        queue.enqueue(28);
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }

    @Test
    void enqueueSomeItemsFromQueue_thenCallDequeue_correctOrderReturned() {
        for (int i = 1; i <= 10; i++) {
            queue.enqueue(i);
        }
        for (int i = 1; i <= 5; i++) {
            assertEquals(i, queue.dequeue());
        }

        for (int i = 11; i <= 20; i++) {
            queue.enqueue(i);
        }
        for (int i = 6; i <= 20; i++) {
            assertEquals(i, queue.dequeue());
        }
    }

    @Test
    void queueIsEmpty_thenCallDequeue_throwException() {
        assertThrows(IllegalStateException.class, () -> queue.dequeue());
        queue.enqueue(1);
        queue.dequeue();
        assertThrows(IllegalStateException.class, () -> queue.dequeue());
    }

}