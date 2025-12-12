package a_14_Stack_And_Queue.Implementation;

import java.util.Stack;

class StackQueue {
    private Stack<Integer> st1, st2;

    // Constructor
    public StackQueue() {
        st1 = new Stack<>();
        st2 = new Stack<>();
    }

    // Method to push element in Queue (O(n))
    public void push(int x) {
        // Move all from st1 → st2
        while (!st1.isEmpty()) {
            st2.push(st1.pop());
        }

        // Push new element to st1
        st1.push(x);

        // Move all from st2 → st1 (restore order)
        while (!st2.isEmpty()) {
            st1.push(st2.pop());
        }
    }

    // Method to pop element from Queue (O(1))
    public int pop() {
        if (st1.isEmpty()) {
            return -1;
        }
        return st1.pop();
    }

    // Method to get front element (O(1))
    public int peek() {
        if (st1.isEmpty()) {
            return -1;
        }
        return st1.peek();
    }

    // Check if queue is empty
    public boolean isEmpty() {
        return st1.isEmpty();
    }
}

public class ImplementQueueUsingStacks {
    public static void main(String[] args) {

        StackQueue q = new StackQueue();

        q.push(10);
        q.push(20);
        q.push(30);

        System.out.println(q.peek()); // 10
        System.out.println(q.pop());  // 10
        System.out.println(q.pop());  // 20

        q.push(40);
        System.out.println(q.peek()); // 30
        System.out.println(q.pop());  // 30

        System.out.println(q.isEmpty()); // false
    }
}
