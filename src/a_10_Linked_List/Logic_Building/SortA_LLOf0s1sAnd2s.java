package a_10_Linked_List.Logic_Building;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class SortA_LLOf0s1sAnd2s {
    public static void main(String[] args) {
        ListNode head1 = ListNode.arrayToLinkedList(new int[]{2, 0});
        head1 = sortList(head1);
        ListNode.printLinkedList(head1);
    }

    public static ListNode sortList(ListNode head) {
        ListNode head0s = new ListNode(-1), tail0s = head0s;
        ListNode head1s = new ListNode(-1), tail1s = head1s;
        ListNode head2s = new ListNode(-1), tail2s = head2s;

        ListNode temp = head;

        // Step 1: Distribute nodes into 0s, 1s, 2s bucket
        while (temp != null) {
            if (temp.val == 0) {
                tail0s.next = temp;
                tail0s = tail0s.next;
            } else if (temp.val == 1) {
                tail1s.next = temp;
                tail1s = tail1s.next;
            } else {
                tail2s.next = temp;
                tail2s = tail2s.next;
            }
            temp = temp.next;
        }

        // Step 2: Terminate last list
        tail2s.next = null;

        // Step 3: Connect three lists properly
        // Connect 0s → 1s (if 1s exist) else → 2s
        if (head1s.next != null) {
            tail0s.next = head1s.next;
            tail1s.next = head2s.next;
        } else {
            tail0s.next = head2s.next;
        }

        // Return head of sorted list
        return head0s.next;
    }
}
