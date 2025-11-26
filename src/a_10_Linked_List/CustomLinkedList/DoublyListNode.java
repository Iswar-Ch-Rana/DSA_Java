package a_10_Linked_List.CustomLinkedList;

public class DoublyListNode {
    public int val;
    public DoublyListNode next;
    public DoublyListNode prev;

    // Constructors
    public DoublyListNode() {
        val = 0;
        next = null;
        prev = null;
    }

    public DoublyListNode(int data) {
        val = data;
        next = null;
        prev = null;
    }

    public DoublyListNode(int data, DoublyListNode prev, DoublyListNode next) {
        val = data;
        this.prev = prev;
        this.next = next;
    }

    // ========================= PRINT =========================
    public static void printForward(DoublyListNode head) {
        DoublyListNode current = head;
        System.out.print("Forward: ");
        while (current != null) {
            System.out.print(current.val + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void printBackward(DoublyListNode tail) {
        DoublyListNode current = tail;
        System.out.print("Backward: ");
        while (current != null) {
            System.out.print(current.val + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    // ========================= ARRAY TO DLL =========================
    public static DoublyListNode arrayToDoublyLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        DoublyListNode head = new DoublyListNode(arr[0]);
        DoublyListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            DoublyListNode newNode = new DoublyListNode(arr[i]);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        return head;
    }

    // ========================= LENGTH =========================
    public static int length(DoublyListNode head) {
        int count = 0;
        DoublyListNode current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // ========================= SEARCH =========================
    public static boolean search(DoublyListNode head, int target) {
        DoublyListNode current = head;
        while (current != null) {
            if (current.val == target) return true;
            current = current.next;
        }
        return false;
    }

    // ========================= INSERT AT HEAD =========================
    public static DoublyListNode insertAtHead(DoublyListNode head, int val) {
        DoublyListNode newNode = new DoublyListNode(val);
        if (head != null) {
            newNode.next = head;
            head.prev = newNode;
        }
        return newNode;
    }

    // ========================= INSERT AT TAIL =========================
    public static DoublyListNode insertAtTail(DoublyListNode head, int val) {
        DoublyListNode newNode = new DoublyListNode(val);
        if (head == null) return newNode;

        DoublyListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        newNode.prev = current;
        return head;
    }

    // ========================= INSERT AT KTH POSITION =========================
    public static DoublyListNode insertAtKth(DoublyListNode head, int val, int k) {
        if (k <= 0) return head;
        if (k == 1) return insertAtHead(head, val);

        DoublyListNode newNode = new DoublyListNode(val);
        DoublyListNode current = head;
        int count = 1;

        while (current != null && count < k - 1) {
            current = current.next;
            count++;
        }

        if (current == null) {
            // Insert at end if k > length
            return insertAtTail(head, val);
        }

        newNode.next = current.next;
        newNode.prev = current;
        if (current.next != null) {
            current.next.prev = newNode;
        }
        current.next = newNode;
        return head;
    }

    // ========================= DELETE HEAD =========================
    public static DoublyListNode deleteHead(DoublyListNode head) {
        if (head == null || head.next == null) return null;
        head = head.next;
        head.prev = null;
        return head;
    }

    // ========================= DELETE TAIL =========================
    public static DoublyListNode deleteTail(DoublyListNode head) {
        if (head == null || head.next == null) return null;

        DoublyListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        if (current.prev != null) {
            current.prev.next = null;
        }
        return head;
    }

    // ========================= DELETE KTH NODE =========================
    public static DoublyListNode deleteKth(DoublyListNode head, int k) {
        if (head == null || k <= 0) return head;
        if (k == 1) return deleteHead(head);

        DoublyListNode current = head;
        int count = 1;
        while (current != null && count < k) {
            current = current.next;
            count++;
        }

        if (current == null) return head; // k > length

        if (current.next != null) {
            current.next.prev = current.prev;
        }
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next; // deleting head
        }
        return head;
    }

    // ========================= DELETE NODE WITH VALUE =========================
    public static DoublyListNode deleteValue(DoublyListNode head, int val) {
        if (head == null) return null;
        if (head.val == val) return deleteHead(head);

        DoublyListNode current = head;
        while (current != null && current.val != val) {
            current = current.next;
        }

        if (current == null) return head; // not found

        if (current.next != null) {
            current.next.prev = current.prev;
        }
        if (current.prev != null) {
            current.prev.next = current.next;
        }
        return head;
    }

    // ========================= MAIN - FULL TESTING =========================
    public static void main(String[] args) {
        System.out.println("=== Doubly Linked List Operations Demo ===\n");

        int[] arr = {10, 20, 30, 40, 50};
        DoublyListNode head = arrayToDoublyLinkedList(arr);

        System.out.println("Original List:");
        printForward(head);
        printBackward(head);

        System.out.println("\nInsert 5 at head:");
        head = insertAtHead(head, 5);
        printForward(head);

        System.out.println("\nInsert 60 at tail:");
        head = insertAtTail(head, 60);
        printForward(head);

        System.out.println("\nInsert 35 at position 4:");
        head = insertAtKth(head, 35, 4);
        printForward(head);

        System.out.println("\nDelete head:");
        head = deleteHead(head);
        printForward(head);

        System.out.println("\nDelete tail:");
        head = deleteTail(head);
        printForward(head);

        System.out.println("\nDelete 3rd node:");
        head = deleteKth(head, 3);
        printForward(head);

        System.out.println("\nDelete node with value 30:");
        head = deleteValue(head, 30);
        printForward(head);

        System.out.println("\nSearch 40: " + search(head, 40));
        System.out.println("Search 999: " + search(head, 999));
        System.out.println("Length: " + length(head));
    }
}