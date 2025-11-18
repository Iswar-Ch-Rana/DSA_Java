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

        /*
        printLinkedList(head);
        System.out.println("Length = " + lengthOfLinkedList(head));
        System.out.println("Search 20 = " + searchElement(head, 20));
        System.out.println("Search 50 = " + searchElement(head, 50));

        // Deletion from LinkedList
        //        head = deleteHead(head);
        //        head = deleteTail(head);
        //        head = deleteKthNode(head, 4);
        //        head = deleteNodeWithValueX(head, 20);
         */
        printLinkedList(head);

        printLinkedList(head);
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

    // Deletion of the head of LL
    public static Node deleteHead(Node head) {
        if (head == null || head.next == null) return null;

        Node temp = head;
        head = head.next;
        temp.next = null;
        return head;
    }

    // Deletion of the tail of LL
    public static Node deleteTail(Node head) {
        if (head == null || head.next == null) return null;

        Node current = head;
        while (current.next.next != null) { // Directly reach 2nd last
            current = current.next;
        }
        current.next = null;
        return head;
    }

    // Deletion of the Kth element of LL
    public static Node deleteKthNode(Node head, int k) {
        if (head == null || k <= 0) return head;
        if (k == 1) return head.next; // Delete first node

        Node current = head;
        int count = 1;

        while (current != null && count < k - 1) {
            current = current.next;
            count++;
        }

        if (current == null || current.next == null)
            return head; // K is out of bounds

        current.next = current.next.next;
        return head;
    }

    // Delete the element with value X
    public static Node deleteNodeWithValueX(Node head, int x) {
        if (head == null) return null;

        // If head is the node to be deleted
        if (head.data == x) return head.next;

        Node current = head;

        // Traverse until we find the node before the target
        while (current.next != null && current.next.data != x) {
            current = current.next;
        }

        // If target node is found
        if (current.next != null) {
            current.next = current.next.next;
        }

        return head;
    }
}
