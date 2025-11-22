package a_10_Linked_List.FAQ_Medium;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class FindMiddleOfLinkedList {
    public static void main(String[] args) {
        ListNode head1 = ListNode.arrayToLinkedList(new int[]{1,3, 2});
        ListNode temp = middleOfLinkedList(head1);
        System.out.println(temp.val);
    }

    public static ListNode middleOfLinkedList(ListNode head) {
        ListNode fast = head, slow = head;

        while (fast.next != null && slow.next != null) {
            fast = fast.next;
            if (fast.next != null) fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}
