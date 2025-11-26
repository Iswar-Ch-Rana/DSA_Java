package a_10_Linked_List.FAQ_DLL;

import a_10_Linked_List.CustomLinkedList.DoublyListNode;

public class DeleteAllOccurrences {
    public static void main(String[] args) {
        DoublyListNode head1 = DoublyListNode.arrayToDoublyLinkedList(new int[]{1, 2, 3, 1, 4});
        head1 = deleteAllOccurrences(head1, 1);
        DoublyListNode.printForward(head1);

        DoublyListNode head2 = DoublyListNode.arrayToDoublyLinkedList(new int[]{2, 3, 1, 4, 2});
        head2 = deleteAllOccurrences(head2, 2);
        DoublyListNode.printForward(head2);

        DoublyListNode head3 = DoublyListNode.arrayToDoublyLinkedList(new int[]{7, 7, 7, 7});
        head3 = deleteAllOccurrences(head3, 7);
        DoublyListNode.printForward(head3);
    }

    public static DoublyListNode deleteAllOccurrences(DoublyListNode head, int target) {
        DoublyListNode temp = head;

        while (temp != null) {
            if (temp.val == target) {
                // If deleting head, move head forward
                if (temp == head) {
                    head = temp.next;
                }

                // Disconnect temp from the list
                if (temp.prev != null) {
                    temp.prev.next = temp.next;
                }
                if (temp.next != null) {
                    temp.next.prev = temp.prev;
                }
            }

            // Move to next before breaking pointers
            temp = temp.next;
        }

        return head;
    }
}
