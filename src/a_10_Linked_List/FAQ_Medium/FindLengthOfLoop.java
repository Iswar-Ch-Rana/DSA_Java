package a_10_Linked_List.FAQ_Medium;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class FindLengthOfLoop {
    public int findLengthOfLoop(ListNode head) {
        if (head == null || head.next == null) return 0;

        ListNode slow = head, fast = head;

        // Step 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;        // move 1 step
            fast = fast.next.next;   // move 2 steps
            if (slow == fast) break; // cycle detected
        }

        // Check if no cycle
        if (fast == null || fast.next == null) return 0;

        // Step 2: Count the length of cycle
        int count = 1;
        fast = fast.next;  // move fast by 1

        while (fast != slow) {
            fast = fast.next;
            count++;
        }

        return count;
    }
}
