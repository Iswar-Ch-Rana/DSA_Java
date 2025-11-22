package a_10_Linked_List.FAQ_Medium;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class DetectALoopInLL {
    public static void main(String[] args) {
        ListNode head = ListNode.arrayToLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode head2 = ListNode.arrayToLinkedList(new int[]{1, 2, 3, 4, 5});
        ListNode.createCycle(head, 1);
        System.out.println(hasCycle(head));
        System.out.println(hasCycle(head2));
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode slow = head;
        ListNode fast = head;

        // Floyd’s Cycle Detection
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true; // Cycle exists
        }
        return false;
    }
}
