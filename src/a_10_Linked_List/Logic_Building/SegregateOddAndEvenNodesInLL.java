package a_10_Linked_List.Logic_Building;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class SegregateOddAndEvenNodesInLL {
    public static void main(String[] args) {
        ListNode head1 = ListNode.arrayToLinkedList(new int[]{4,3,2,1});
        head1 = oddEvenList(head1);
        ListNode.printLinkedList(head1);
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode odd = head, even = head.next;
        ListNode evenStart = head.next;

        while (even != null && even.next != null) {
            odd.next = even.next;
            even.next = even.next.next;

            odd = odd.next;
            even = even.next;
        }
        odd.next = evenStart;
        return head;
    }
}
