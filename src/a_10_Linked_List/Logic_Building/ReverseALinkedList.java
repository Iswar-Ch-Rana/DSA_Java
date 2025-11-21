package a_10_Linked_List.Logic_Building;


import a_10_Linked_List.CustomLinkedList.ListNode;

public class ReverseALinkedList {
    public static void main(String[] args) {
        ListNode head = ListNode.arrayToLinkedList(new int[]{1});
        ListNode.printLinkedList(head);
        head = reverseList(head);
        ListNode.printLinkedList(head);
    }

    public static ListNode reverseList(ListNode head) {
        ListNode curr = head;
        ListNode back = null;
        ListNode front = null;
        while (curr != null) {
            front = curr.next;
            curr.next = back;
            back = curr;
            curr = front;
        }
        return back;
    }
}
