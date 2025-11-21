package a_10_Linked_List.CustomLinkedList;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
        val = 0;
        next = null;
    }

    public ListNode(int data1) {
        val = data1;
        next = null;
    }

    public ListNode(int data1, ListNode next1) {
        val = data1;
        next = next1;
    }
    

    public static void main(String[] args) {
        
    }
    
    // Print linked list
    public static void printLinkedList(ListNode head) {
        ListNode current = head;

        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }

    // Search element in linked list
    public static boolean searchElement(ListNode head, int target) {
        ListNode current = head;

        while (current != null) {
            if (current.val == target)
                return true;

            current = current.next;
        }

        return false;
    }

    // Convert array to linked list
    public static ListNode arrayToLinkedList(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }

        return head;
    }

    // Length of linked list
    public static int lengthOfLinkedList(ListNode head) {
        int count = 0;
        ListNode current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    // Deletion of the head of LL
    public static ListNode deleteHead(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode temp = head;
        head = head.next;
        temp.next = null;
        return head;
    }

    // Deletion of the tail of LL
    public static ListNode deleteTail(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode current = head;
        while (current.next.next != null) { // Directly reach 2nd last
            current = current.next;
        }
        current.next = null;
        return head;
    }

    // Deletion of the Kth element of LL
    public static ListNode deleteKthNode(ListNode head, int k) {
        if (head == null || k <= 0) return head;
        if (k == 1) return head.next; // Delete first node

        ListNode current = head;
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
    public static ListNode deleteNodeWithValueX(ListNode head, int x) {
        if (head == null) return null;

        // If head is the node to be deleted
        if (head.val == x) return head.next;

        ListNode current = head;

        // Traverse until we find the node before the target
        while (current.next != null && current.next.val != x) {
            current = current.next;
        }

        // If target node is found
        if (current.next != null) {
            current.next = current.next.next;
        }

        return head;
    }

    // Insertion at the head of LL
    public static ListNode insertAtHead(ListNode head, int X) {
        ListNode temp = new ListNode(X);
        temp.next = head;
        return temp;
    }

    // Insertion at the tail of LL
    public static ListNode insertAtTail(ListNode head, int X) {
        if (head == null)
            return new ListNode(X);

        ListNode temp = head;
        // Traversing until the last node
        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = new ListNode(X);
        return head;
    }

    // Insertion at the Kth position of LL
    public static ListNode insertAtKthPosition(ListNode head, int X, int K) {
        if (K <= 0) return head; // Invalid K

        if (K == 1) { // Insert at the head
            return new ListNode(X, head);
        }

        // Traverse to find Kth position
        ListNode temp = head;
        int count = 1;

        while (temp != null && count < K - 1) {
            temp = temp.next;
            count++;
        }

        // If temp is null, K > length, insert at end
        ListNode newNode = new ListNode(X);

        if (temp == null) {
            // Insert at the end
            ListNode curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = newNode;
        } else {
            // Normal case: insert at Kth position
            newNode.next = temp.next;
            temp.next = newNode;
        }

        return head;
    }

    // Insertion before the value X in LL
    public static ListNode insertBeforeX(ListNode head, int X, int val) {
        if (head == null) {
            return new ListNode(X); // If list is empty, return new node
        }

        // If value to insert before is at head
        if (head.val == val) {
            return new ListNode(X, head);
        }

        ListNode temp = head;
        ListNode prev = null;

        // Find the node with value 'val'
        while (temp != null && temp.val != val) {
            prev = temp;
            temp = temp.next;
        }

        // If value not found, return head as it is
        if (temp == null) return head;

        // Create new node and insert before target node
        ListNode newNode = new ListNode(X);
        prev.next = newNode;
        newNode.next = temp;

        return head;
    }
}
