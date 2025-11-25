package a_10_Linked_List.FAQ_Hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode child;

    ListNode() {
        val = 0;
        next = null;
        child = null;
    }

    ListNode(int data1) {
        val = data1;
        next = null;
        child = null;
    }

    ListNode(int data1, ListNode next1, ListNode next2) {
        val = data1;
        next = next1;
        child = next2;
    }
}

public class FlattenLinkedList {

    // Helper function to print the flattened list (using child pointer)
    private static void printFlattenedList(ListNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.child != null) {
                System.out.print(" -> ");
            }
            curr = curr.child;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FlattenLinkedList solution = new FlattenLinkedList();

        // -------------------------------------------------
        // Test Case 1 (the first example in the problem)
        // -------------------------------------------------
        // Level 1: 5 -> 7 -> 8 -> 10
        // Child of 7: 1 -> 2 -> 3 -> 4
        // Child of 8: 6
        // Child of 10: 9 -> 11 -> 12
        ListNode head1 = new ListNode(5);
        head1.next = new ListNode(7);
        head1.next.next = new ListNode(8);
        head1.next.next.next = new ListNode(10);

        // child lists
        head1.next.child = new ListNode(1);
        head1.next.child.next = new ListNode(2);
        head1.next.child.next.next = new ListNode(3);
        head1.next.child.next.next.next = new ListNode(4);

        head1.next.next.child = new ListNode(6);

        head1.next.next.next.child = new ListNode(9);
        head1.next.next.next.child.next = new ListNode(11);
        head1.next.next.next.child.next.next = new ListNode(12);

        System.out.println("Test Case 1:");
        ListNode flattened1 = solution.flattenLinkedList(head1);
        System.out.print("Output: ");
        printFlattenedList(flattened1);   // Expected: 1->2->3->4->5->6->7->8->9->10->11->12

        // -------------------------------------------------
        // Test Case 2 (the second example in the problem)
        // -------------------------------------------------
        ListNode head2 = new ListNode(2);
        head2.next = new ListNode(10);
        head2.next.next = new ListNode(13);

        // child lists
        head2.child = new ListNode(4);
        head2.child.next = new ListNode(5);

        head2.next.child = new ListNode(12);
        head2.next.child.next = new ListNode(16);
        head2.next.child.next.next = new ListNode(17);

        head2.next.next.child = new ListNode(20);

        System.out.println("\nTest Case 2:");
        ListNode flattened2 = solution.flattenLinkedList(head2);
        System.out.print("Output: ");
        printFlattenedList(flattened2);   // Expected: 2->4->5->10->12->13->16->17->20

        // -------------------------------------------------
        // Test Case 3 - Single level, no children
        // -------------------------------------------------
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(3);
        head3.next.next = new ListNode(5);

        System.out.println("\nTest Case 3 (no children):");
        ListNode flattened3 = solution.flattenLinkedList(head3);
        printFlattenedList(flattened3);   // Expected: 1->3->5

        // -------------------------------------------------
        // Test Case 4 - One node with a long child list
        // -------------------------------------------------
        ListNode head4 = new ListNode(100);
        head4.child = new ListNode(1);
        head4.child.next = new ListNode(2);
        head4.child.next.next = new ListNode(3);
        head4.child.next.next.next = new ListNode(99);

        System.out.println("\nTest Case 4 (one node with child list):");
        ListNode flattened4 = solution.flattenLinkedList(head4);
        printFlattenedList(flattened4);   // Expected: 1->2->3->99->100

        // -------------------------------------------------
        // Test Case 5 - Empty list (head = null)
        // -------------------------------------------------
        System.out.println("\nTest Case 5 (null head):");
        ListNode flattened5 = solution.flattenLinkedList(null);
        printFlattenedList(flattened5);   // Expected: null
    }

    // Solution 2 Optimal
    public ListNode flattenLinkedList(ListNode head) {
        if (head == null || head.next == null) {
            return head; // Return head
        }

        ListNode mergedHead = flattenLinkedList(head.next);

        head = merge(head, mergedHead);
        return head;
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        if (list2 == null) return null;
        if (list1 == null) return null;


        ListNode dummy = new ListNode(-1);
        ListNode temp = dummy;
        while (list1 != null && list2 != null) {
            int val1 = list1.val;
            int val2 = list2.val;

            if (val1 < val2) {
                temp.child = list1;
                list1 = list1.child;
            } else {
                temp.child = list2;
                list2 = list2.child;
            }
            temp = temp.child;
        }
        if (list1 == null) {
            temp.child = list2;
        } else {
            temp.child = list1;
        }
        return dummy.child;
    }


    // Solution 1 Brute Force
    public ListNode flattenLinkedList1(ListNode head) {
        if (head == null) return null;
        List<Integer> arr = new ArrayList<>();

        ListNode temp = head;

        // push all the element to an array
        while (temp != null) {
            ListNode t1 = temp;
            while (t1 != null) {
                arr.add(t1.val);
                t1 = t1.child;
            }
            temp = temp.next;
        }

        // sort all the element;
        arr.sort(Comparator.naturalOrder());

        ListNode head2 = new ListNode(arr.get(0));
        ListNode temp2 = head2;
        for (int i = 1; i < arr.size(); i++) {
            temp2.child = new ListNode(arr.get(i));
            temp2 = temp2.child;
        }
        while (head2 != null) {
            System.out.println(head2.val);
            head2 = head2.child;
        }
        return head2;
    }
}