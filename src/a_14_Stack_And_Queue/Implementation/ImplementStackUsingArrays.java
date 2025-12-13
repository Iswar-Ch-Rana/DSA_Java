package a_14_Stack_And_Queue.Implementation;

class ArrayStack {
    private int[] arr;       // stack array
    private int capacity;    // max capacity
    private int top;         // index of top element

    // Constructor
    public ArrayStack(int size) {
        this.capacity = size;
        this.arr = new int[size];
        this.top = -1;
    }

    public ArrayStack() {
        this(1000);
    }

    // Insert element at top
    public void push(int x) {
        if (top + 1 >= capacity) {
            System.out.println("Stack Overflow");
            return;
        }
        arr[++top] = x;
    }

    // Remove and return top element
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1;
        }
        int value = arr[top];
        arr[top] = 0; // optional cleanup
        top--;
        return value;
    }

    // Return top element without removing
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return -1;
        }
        return arr[top];
    }

    // Check if stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Return current size
    public int size() {
        return top + 1;
    }
}

public class ImplementStackUsingArrays {
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);

        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Top: " + stack.peek()); // 30
        System.out.println("Popped: " + stack.pop()); // 30
        System.out.println("Top after pop: " + stack.peek()); // 20

        System.out.println("Is stack empty? " + stack.isEmpty());
        System.out.println("Stack size: " + stack.size());
    }
}
