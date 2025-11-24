package a_10_Linked_List.FAQ_Hard;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class RotateList {
    public static void main(String[] args) {
        ListNode head = ListNode.arrayToLinkedList(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        head = rotateRight(head, 9);
        ListNode.printLinkedList(head);
    }

    // Most optimise
    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        // Step 1: Find length and connect tail to head (make circular list)
        int size = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            size++;
        }
        tail.next = head;  // Create the circular linked list

        // Step 2: Find new tail (size - k % size - 1) and new head (next of new tail)
        k = k % size;
        int stepsToNewTail = size - k;
        ListNode newTail = head;
        for (int i = 1; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }

        // Step 3: Set new head and break the cycle
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }

    public static ListNode rotateRight1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // Step 1: Find length
        int size = lengthOfLinkedList(head);
        k = k % size;  // Optimize k
        if (k == 0) return head;

        // Step 2: Rotate at position size - k
        return rotateByK(head, size - k);
    }

    private static ListNode rotateByK(ListNode head, int k) {
        ListNode splitNode = head;
        for (int i = 1; i < k; i++) {
            splitNode = splitNode.next;
        }

        ListNode newHead = splitNode.next;
        splitNode.next = null;  // Break

        // Step 3: Reach new tail and connect
        ListNode curr = newHead;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = head;

        return newHead;
    }

    private static int lengthOfLinkedList(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}
