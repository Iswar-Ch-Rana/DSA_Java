package a_10_Linked_List.FAQ_Medium;

import a_10_Linked_List.CustomLinkedList.ListNode;

import static a_10_Linked_List.Logic_Building.ReverseALinkedList.reverseList;

public class AddOne {
    public static void main(String[] args) {
        ListNode head1 = ListNode.arrayToLinkedList(new int[]{9, 9, 3, 9});
//        head1 = addOneWithoutReverse(head1);
        head1 = addOneWithoutReverse1(head1);
        ListNode.printLinkedList(head1);

    }

    // With recursion and backtrack O(N)
    public static ListNode addOneWithoutReverse1(ListNode head) {
        int carry = backtrack(head);
        if (carry == 1) {
            ListNode temp = new ListNode(1);
            temp.next = head;
            return temp;
        }
        return head;
    }

    private static int backtrack(ListNode head) {
        if (head == null)
            return 1;

        int carry = backtrack(head.next);
        head.val = carry + head.val;
        if (head.val > 9) {
            head.val = 0;
            return 1;
        } else {
            return 0;
        }
    }

    // using iterative O(3N)
    public static ListNode addOneWithoutReverse(ListNode head) {
        // Reverse the linked list
        head = reverseList(head);

        // Create a dummy node
        ListNode current = head;
        // Initialize carry with 1
        int carry = 1;

        while (current != null) {
            /* Sum the current node's value
            and the carry */
            int sum = current.val + carry;
            // Update carry
            carry = sum / 10;
            // Update the node's value
            current.val = sum % 10;

            /* If no carry left
            break the loop */
            if (carry == 0) {
                break;
            }

            /* If we've reached the end of the list
            and there's still a carry,
            add a new node with the carry value */
            if (current.next == null && carry != 0) {
                current.next = new ListNode(carry);
                break;
            }

            // Move to the next node
            current = current.next;
        }

        // Reverse the list
        head = reverseList(head);

        // New head
        return head;
    }
}
