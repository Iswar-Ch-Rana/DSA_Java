package a_10_Linked_List.FAQ_Medium;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class DeleteMiddleOfLinkedList {
    public static void main(String[] args) {
        ListNode head1 = ListNode.arrayToLinkedList(new int[]{32, 52});
        head1 = deleteMiddle(head1);
        ListNode.printLinkedList(head1);
    }

    public static ListNode deleteMiddle(ListNode head) {
        /* If the list is empty or has only one node,
         * return null as there is no middle node to delete */
        if (head == null || head.next == null) {
            return null;
        }

        // Initialize slow and fast pointers
        ListNode slow = head;
        ListNode fast = head.next.next;

        // Move 'fast' pointer twice as fast as 'slow'
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Delete the middle node by skipping it
        slow.next = slow.next.next;
        return head;
    }
}
