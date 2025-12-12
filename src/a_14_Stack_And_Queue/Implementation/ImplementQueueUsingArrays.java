package a_14_Stack_And_Queue.Implementation;

class ArrayQueue {
    int[] arr;
    int capacity;
    int front;
    int rear;
    int size;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        arr = new int[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public ArrayQueue() {
        this(1000);
    }

    // Add element
    public void push(int x) {
        if (size == capacity) {
            System.out.println("Queue is full!");
            return;
        }

        rear = (rear + 1) % capacity; // circular increment
        arr[rear] = x;
        size++;
    }

    // Remove element
    public int pop() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }

        int val = arr[front];
        front = (front + 1) % capacity; // circular increment
        size--;
        return val;
    }

    // Get front element
    public int peek() {
        if (isEmpty()) return -1;
        return arr[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

public class ImplementQueueUsingArrays {
    public static void main(String[] args) {
        ArrayQueue q = new ArrayQueue(5);

        q.push(10);
        q.push(20);
        q.push(30);

        System.out.println(q.pop());  // 10
        System.out.println(q.peek()); // 20

        q.push(40);
        q.push(50);
        q.push(60);

        while (!q.isEmpty()) {
            System.out.println(q.pop());
        }
    }
}
