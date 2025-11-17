package a_10_Linked_List.Fundamentals_Doubly_LL;

class Node {
    int data;
    Node next;

    Node() {
        this.data = -1;
        this.next = null;
    }

    Node(int val) {
        this.data = val;
        this.next = null;
    }

    Node(int val, Node next) {
        this.data = val;
        this.next = next;
    }
}

public class LinkedList {

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        Node head = arrayToLinkedList(arr);

        printLinkedList(head);
        System.out.println("Length = " + lengthOfLinkedList(head));
        System.out.println("Search 20 = " + searchElement(head, 20));
        System.out.println("Search 50 = " + searchElement(head, 50));
    }

    // Print linked list
    public static void printLinkedList(Node head) {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    // Search element in linked list
    public static boolean searchElement(Node head, int target) {
        Node current = head;

        while (current != null) {
            if (current.data == target)
                return true;

            current = current.next;
        }

        return false;
    }

    // Convert array to linked list
    public static Node arrayToLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        Node head = new Node(arr[0]);
        Node current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }

        return head;
    }

    // Length of linked list
    public static int lengthOfLinkedList(Node head) {
        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }
}
