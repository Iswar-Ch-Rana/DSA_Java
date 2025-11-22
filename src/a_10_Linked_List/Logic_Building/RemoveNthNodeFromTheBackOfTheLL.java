package a_10_Linked_List.Logic_Building;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class RemoveNthNodeFromTheBackOfTheLL {
    public static void main(String[] args) {
        ListNode head1 = ListNode.arrayToLinkedList(new int[]{1,2,3,4,5});
        head1 = removeNthFromEnd(head1,2);
        ListNode.printLinkedList(head1);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // Creating pointers
        ListNode fastp = head;
        ListNode slowp = head;

        /* Move the fastp pointer
        N nodes ahead */
        for (int i = 0; i < n; i++) {
            fastp = fastp.next;
        }

        /* If fastp becomes NULL
        the Nth node from the
        end is the head */
        if (fastp == null) {
            return head.next;
        }

        /* Move both pointers
        Until fastp reaches the end */
        while (fastp.next != null) {
            fastp = fastp.next;
            slowp = slowp.next;
        }

        // Delete the Nth node from the end
        ListNode delNode = slowp.next;
        slowp.next = slowp.next.next;
        return head;
    }
}
