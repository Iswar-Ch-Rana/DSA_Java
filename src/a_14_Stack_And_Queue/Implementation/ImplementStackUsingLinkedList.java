package a_14_Stack_And_Queue.Implementation;

import java.util.LinkedList;

class LinkedListStack {
    private final LinkedList<Integer> list;

    public LinkedListStack() {
        list = new LinkedList<>();
    }

    // Push element to top of Stack
    public void push(int x) {
        list.addFirst(x); // add at head -> O(1)
    }

    // Pop element from Stack
    public int pop() {
        if (isEmpty()) return -1;
        return list.removeFirst(); // remove head -> O(1)
    }

    // Peek top element
    public int top() {
        if (isEmpty()) return -1;
        return list.getFirst();
    }

    // Check if Stack is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }
}


// ---------- MAIN CLASS ----------
public class ImplementStackUsingLinkedList {
    public static void main(String[] args) {

        LinkedListStack st = new LinkedListStack();

        st.push(10);
        st.push(20);
        st.push(30);

        System.out.println(st.top()); // 30
        System.out.println(st.pop()); // 30

        System.out.println(st.top()); // 20

        st.push(40);

        System.out.println(st.pop()); // 40
        System.out.println(st.pop()); // 20
        System.out.println(st.pop()); // 10

        System.out.println(st.isEmpty()); // true
    }
}
