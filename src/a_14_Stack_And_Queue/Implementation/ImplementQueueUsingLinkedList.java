package a_14_Stack_And_Queue.Implementation;

import java.util.LinkedList;

class LinkedListQueue {
    private LinkedList<Integer> list;

    public LinkedListQueue() {
        list = new LinkedList<>();
    }

    // Enqueue (add at back)
    public void push(int x) {
        list.addLast(x); // O(1)
    }

    // Dequeue (remove from front)
    public int pop() {
        if (isEmpty()) return -1;
        return list.removeFirst(); // O(1)
    }

    // Get front element
    public int peek() {
        if (isEmpty()) return -1;
        return list.getFirst(); // O(1)
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }
}

public class ImplementQueueUsingLinkedList {
    public static void main(String[] args) {

        LinkedListQueue q = new LinkedListQueue();

        q.push(10);
        q.push(20);
        q.push(30);

        System.out.println(q.peek()); // 10
        System.out.println(q.pop());  // 10
        System.out.println(q.pop());  // 20

        q.push(40);
        q.push(50);

        System.out.println(q.peek()); // 30
        System.out.println(q.pop());  // 30
        System.out.println(q.pop());  // 40
        System.out.println(q.pop());  // 50

        System.out.println(q.isEmpty()); // true
    }
}
