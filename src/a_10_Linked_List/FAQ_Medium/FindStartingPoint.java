package a_10_Linked_List.FAQ_Medium;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class FindStartingPoint {
    public ListNode findStartingPoint(ListNode head) {
        if (head == null || head.next == null) return null;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        if (fast == null) return null;

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
