package a_10_Linked_List.FAQ_DLL;

import a_10_Linked_List.CustomLinkedList.DoublyListNode;

public class RemoveDuplicates {
    public static void main(String[] args) {
        DoublyListNode head1 = DoublyListNode.arrayToDoublyLinkedList(new int[]{1, 1, 3, 3, 5});
        head1 = removeDuplicates(head1);
        DoublyListNode.printForward(head1);
    }

    public static DoublyListNode removeDuplicates(DoublyListNode head) {
        if (head == null) return null;

        DoublyListNode curr = head;

        while (curr != null && curr.next != null) {
            if (curr.val == curr.next.val) {
                // Remove duplicate node (curr.next)
                DoublyListNode duplicate = curr.next;
                curr.next = duplicate.next;

                if (duplicate.next != null) {
                    duplicate.next.prev = curr;
                }
                duplicate.prev = null;
                duplicate.next = null;
            } else {
                curr = curr.next; // Only move forward if unique
            }
        }

        return head; // Always return head (not temp)
    }
}
