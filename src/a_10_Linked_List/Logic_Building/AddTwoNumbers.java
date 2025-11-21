package a_10_Linked_List.Logic_Building;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode head1 = ListNode.arrayToLinkedList(new int[]{3, 4, 2});
        ListNode head2 = ListNode.arrayToLinkedList(new int[]{4, 6, 3});

        ListNode.printLinkedList(addTwoNumbers(head1, head2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;

        while (l1 != null || l2 != null) {
            int v1 = (l1 != null) ? l1.val : 0;
            int v2 = (l2 != null) ? l2.val : 0;

            int sum = v1 + v2 + carry;
            carry = sum / 10;
            int val = sum % 10;

            ListNode temp = new ListNode(val);

            if (head == null) {
                head = temp;
                tail = temp;
            } else {
                tail.next = temp;
                tail = temp;
            }

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry > 0) {
            tail.next = new ListNode(carry);
        }

        return head;
    }
}
