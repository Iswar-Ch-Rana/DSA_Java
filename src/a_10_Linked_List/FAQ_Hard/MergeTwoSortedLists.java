package a_10_Linked_List.FAQ_Hard;

import a_10_Linked_List.CustomLinkedList.ListNode;

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode head1 = ListNode.arrayToLinkedList(new int[]{2, 4, 7, 9});
        ListNode head2 = ListNode.arrayToLinkedList(new int[]{1, 2, 5, 6});

        ListNode merged1 = mergeTwoLists(head1, head2);
        ListNode.printLinkedList(merged1);

        ListNode head3 = ListNode.arrayToLinkedList(new int[]{1, 2, 3, 4});
        ListNode head4 = ListNode.arrayToLinkedList(new int[]{5, 6, 10});

        ListNode merged2 = mergeTwoLists(head3, head4);
        ListNode.printLinkedList(merged2);
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // Create a dummy node to serve as
        // the head of the merged list
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;

        // Traverse both lists simultaneously
        while (list1 != null && list2 != null) {
            /*Compare elements of both lists
            and link the smaller node
            to the merged list*/
            if (list1.val <= list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            // Move the temporary pointer
            // to the next node
            temp = temp.next;
        }

        /*If any list still
        has remaining elements,
        append them to the merged list*/
        if (list1 != null) {
            temp.next = list1;
        } else {
            temp.next = list2;
        }

        // Return merged list
        return dummyNode.next;
    }
}
