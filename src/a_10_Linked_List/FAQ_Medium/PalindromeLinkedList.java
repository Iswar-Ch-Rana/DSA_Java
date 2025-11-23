package a_10_Linked_List.FAQ_Medium;

import a_10_Linked_List.CustomLinkedList.ListNode;

import java.util.Stack;

public class PalindromeLinkedList {
    private static ListNode left;

    public static void main(String[] args) {
        ListNode head1 = ListNode.arrayToLinkedList(new int[]{9, 9, 9, 9, 9});

        System.out.println(isPalindrome1(head1));
        System.out.println(isPalindrome2(head1));
        System.out.println(isPalindrome(head1));
    }

    // Approach 3 TIME - O(N) SPACE - O(1)
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        // find the 2nd half of the linkedList
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // reverse the list from slows next
        ListNode curr = slow.next, prev = null;
        while (curr != null) {
            ListNode temp1 = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp1;
        }

        // compare
        ListNode first = head, second = prev;
        while (first != null && second != null) {
            if (first.val != second.val) {
                return false;
            }
            first = first.next;
            second = second.next;
        }
        return true;
    }

    // Approach 2 TIME - O(N) SPACE - O(N)
    public static boolean isPalindrome2(ListNode head) {
        left = head;
        return solve(head);
    }

    private static boolean solve(ListNode right) {
        // base case – reached end of list
        if (right == null) return true;

        // recursive call till end
        boolean result = solve(right.next);

        // compare left and right
        if (left.val != right.val) return false;

        left = left.next; // move left forward for next comparison
        return result;
    }

    // Approach 1 TIME - O(N) SPACE - O(N)
    public static boolean isPalindrome1(ListNode head) {
        Stack<Integer> st = new Stack<>();

        ListNode temp = head;
        while (temp != null) {
            st.add(temp.val);
            temp = temp.next;
        }

        while (head != null) {
            if (st.pop() != head.val) return false;
            head = head.next;
        }
        return true;
    }
}
