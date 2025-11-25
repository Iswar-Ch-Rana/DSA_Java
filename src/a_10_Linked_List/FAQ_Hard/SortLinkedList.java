package a_10_Linked_List.FAQ_Hard;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class SortLinkedList {
    public static void main(String[] args) {
        ListNode head1 = ListNode.arrayToLinkedList(new int[]{5, 6, 1, 2, 1});
        head1 = sortList(head1);
        ListNode.printLinkedList(head1);

        ListNode head2 = ListNode.arrayToLinkedList(new int[]{5, 6, -1, -2, -3});
        head2 = sortList(head2);
        ListNode.printLinkedList(head2);

        ListNode head3 = ListNode.arrayToLinkedList(new int[]{-1, -2, -3, -1});
        head3 = sortList(head3);
        ListNode.printLinkedList(head3);
    }

    // Sort linked list using Merge Sort (O(N log N) TIME | O(log N) SPACE)
    public static ListNode sortList(ListNode head) {
        // Base case: 0 or 1 node -> already sorted
        if (head == null || head.next == null) return head;

        // Step 1: Split list into two halves using slow-fast pointer
        ListNode slow = head, fast = head, prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Disconnect left half from right
        prev.next = null;

        // Step 2: Recursively sort each half
        ListNode left = sortList(head);
        ListNode right = sortList(slow);

        // Step 3: Merge sorted halves
        return merge(left, right);
    }

    // Merge two sorted linked lists (like in merge sort)
    private static ListNode merge(ListNode list1, ListNode list2) {
        // If any list is empty, return the other
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;

        // Compare nodes and build sorted list
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }

        // Attach remaining nodes
        temp.next = (list1 != null) ? list1 : list2;

        return dummy.next;
    }
}
