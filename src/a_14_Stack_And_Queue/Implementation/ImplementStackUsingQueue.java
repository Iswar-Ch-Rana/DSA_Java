package a_14_Stack_And_Queue.Implementation;

import java.util.LinkedList;
import java.util.Queue;

class QueueStack {
    // Queue
    Queue<Integer> q = new LinkedList<>();

    // Method to push element in the stack
    public void push(int x) {
        // Get size
        int s = q.size();

        // Add element at back
        q.add(x);

        // Rotate previous elements behind the new element
        for (int i = 0; i < s; i++) {
            q.add(q.poll());
        }
    }

    // Method to pop element from stack
    public int pop() {
        if (q.isEmpty()) {
            return -1; // stack underflow
        }
        return q.poll();
    }

    // Method to return the top of stack
    public int top() {
        if (q.isEmpty()) {
            return -1;
        }
        return q.peek();
    }

    // Method to check if the stack is empty
    public boolean isEmpty() {
        return q.isEmpty();
    }
}

public class ImplementStackUsingQueue {
    public static void main(String[] args) {
        QueueStack st = new QueueStack();

        st.push(10);
        st.push(20);
        st.push(30);

        System.out.println(st.top()); // 30
        System.out.println(st.pop()); // 30
        System.out.println(st.top()); // 20
        System.out.println(st.isEmpty()); // false
    }
}
