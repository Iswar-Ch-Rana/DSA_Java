package a_10_Linked_List.Fundamentals_Doubly_LL;

class Node {
    int val;
    Node next;
    Node prev;

    Node() {
        val = 0;
        next = null;
        prev = null;
    }

    Node(int data1) {
        val = data1;
        next = null;
        prev = null;
    }

    Node(int data1, Node next1, Node prev1) {
        val = data1;
        next = next1;
        prev = prev1;
    }
}

public class LinkedList {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        Node head = arrayToLinkedList(arr);
        /*
         * Delete DLL
         * ------ ---
         * head = deleteHead(head);
         * head = deleteTail(head);
         * head = deleteKthElement(head, 4);
         * head = deleteGivenNode(head.next.next);
         *
         * Insert node in DLL
         * ------ ---- -- ---
         * head = insertBeforeHead(head, 3);
         * head = insertBeforeTail(head, 1);
         * head = insertBeforeKthPosition(head, 1, 1);
         * insertBeforeGivenNode(head.next, 3);
         * */

        printLinkedList(head);

        printLinkedList(head);
    }

    // Insert before given node in DLL
    public static void insertBeforeGivenNode(Node node, int X) {
        if (node == null) return;

        Node newNode = new Node(X);

        // If node is head (no prev), prev pointers are null → just attach new node
        if (node.prev != null) {
            newNode.prev = node.prev;
            node.prev.next = newNode;
        }
        newNode.next = node;
        node.prev = newNode;
    }

    // Insert node before (kth node) in DLL
    public static Node insertBeforeKthPosition(Node head, int X, int K) {
        if (K <= 1 || head == null) {
            // If K is 1 or list is empty, new node becomes head
            return insertBeforeHead(head, X);
        }

        Node temp = head;
        int count = 1;

        // Move to K-th node
        while (temp != null && count < K) {
            temp = temp.next;
            count++;
        }

        // If K is beyond list size → no insertion
        if (temp == null) return head;

        // Now `temp` is the Kth node, insert before it
        Node newNode = new Node(X);

        newNode.next = temp;
        newNode.prev = temp.prev;

        if (temp.prev != null) {
            temp.prev.next = newNode;
        }
        temp.prev = newNode;

        // If inserting at position 1, update head
        return (K == 1) ? newNode : head;
    }

    // Insert node before tail in DLL
    public static Node insertBeforeTail(Node head, int X) {
        // case 1 - list is empty
        if (head == null) {
            return new Node(X);
        }

        // case 2 - head and tail both are same
        Node temp = new Node(X);
        if (head.next == null) {
            head.prev = temp;
            temp.next = head;
            head = temp;
            return head;
        }

        // case 3 - insert before tail
        Node tempHead = head;
        while (tempHead.next != null) {
            tempHead = tempHead.next;
        }

        tempHead.prev.next = temp;
        temp.next = tempHead;
        temp.prev = tempHead.prev;
        tempHead.prev = temp;

        return head;
    }

    // Insert node before head in DLL
    public static Node insertBeforeHead(Node head, int X) {
        if (head == null) {
            return new Node(X);
        }

        Node temp = new Node(X);
        head.prev = temp;
        temp.next = head;
        head = temp;
        return head;
    }

    // Removing given node in DLL
    public Node deleteGivenNode(Node node) {
        if (node == null) return null;

        // Case 1: Node is both head and tail (only one node in DLL)
        if (node.prev == null && node.next == null) {
            return null;
        }

        // Case 2: Node is the head
        if (node.prev == null) {  // no previous -> head
            Node newHead = node.next;
            newHead.prev = null;
            node.next = null; // detach
            return newHead;
        }

        // Case 3: Node is the tail
        if (node.next == null) {  // no next -> tail
            node.prev.next = null;
            node.prev = null;
            return null; // tail deletion doesn’t affect head
        }

        // Case 4: Node is in the middle
        node.prev.next = node.next;
        node.next.prev = node.prev;

        // Detach fully to avoid memory holding
        node.next = null;
        node.prev = null;

        return null; // doesn't impact head
    }

    // Delete Kth Element of DLL
    public static Node deleteKthElement(Node head, int k) {
        // If the list is empty, return null
        if (head == null)
            return null;

        int count = 0;
        Node kNode = head;

        // Traverse the list
        while (kNode != null) {
            count++;
            if (count == k) break;
            kNode = kNode.next;
        }

        // If k is larger than the list size
        if (kNode == null) return head;

        // Update the pointers
        Node prev = kNode.prev;
        Node front = kNode.next;

        // If node to be deleted is the only node in the list
        if (prev == null && front == null) {
            return null;
        }

        // If node to be deleted is head of the list
        else if (prev == null) {
            head = front;
            front.prev = null;
        }

        // If node to be deleted is tail of the list
        else if (front == null) {
            prev.next = null;
        }

        // If node to be deleted is in the middle of the list
        else {
            prev.next = front;
            front.prev = prev;
        }

        // Return modified list head
        return head;
    }

    // Delete Tail of DLL
    public static Node deleteTail(Node head) {
        if (head == null || head.next == null) return null;

        Node temp = head;
        while (temp.next != null) {
            temp = temp.next; // temp at last node
        }
        temp.prev.next = null;
        temp.prev = null; // help GC
        return head;
    }

    // Delete head of DLL
    public static Node deleteHead(Node head) {
        if (head == null || head.next == null) return null;

        Node temp = head;
        head = head.next;
        head.prev = null;
        temp.next = null; // break old head's link
        return head;
    }

    // Prints list forwards and backwards
    public static void printLinkedList(Node head) {
        Node temp = head;
        Node last = null;

        System.out.print("Forward: ");
        while (temp != null) {
            System.out.print(temp.val + " ");
            last = temp;
            temp = temp.next;
        }
        System.out.println("null");

        System.out.print("Backward: ");
        while (last != null) {
            System.out.print(last.val + " ");
            last = last.prev;
        }
        System.out.println("null");
    }

    // Searches for an element
    public static boolean searchElement(Node head, int target) {
        Node temp = head;
        while (temp != null) {
            if (temp.val == target) return true;
            temp = temp.next;
        }
        return false;
    }

    // Converts array into doubly linked list
    public static Node arrayToLinkedList(int[] arr) {
        if (arr.length == 0) return null;

        Node head = new Node(arr[0]);
        Node current = head;

        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        return head;
    }

    // Computes length of the linked list
    public static int lengthOfLinkedList(Node head) {
        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }
}

