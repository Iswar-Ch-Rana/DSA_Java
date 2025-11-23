package a_10_Linked_List.FAQ_Medium;

import a_10_Linked_List.CustomLinkedList.ListNode;

import java.util.HashSet;

public class GetIntersectionNode {
    public static void main(String[] args) {
        GetIntersectionNode solution = new GetIntersectionNode();

        // Case 1: Intersection at node with value 8
        ListNode common = new ListNode(8, new ListNode(10));

        ListNode headA1 = new ListNode(4, new ListNode(1, common));
        ListNode headB1 = new ListNode(5, new ListNode(6, new ListNode(1, common)));

        ListNode result1 = solution.getIntersectionNode(headA1, headB1);
        System.out.println("Test Case 1 Intersection at: " + (result1 != null ? result1.val : "null"));

        // Case 2: No intersection
        ListNode headA2 = new ListNode(1, new ListNode(2, new ListNode(3)));
        ListNode headB2 = new ListNode(4, new ListNode(5));

        ListNode result2 = solution.getIntersectionNode(headA2, headB2);
        System.out.println("Test Case 2 Intersection at: " + (result2 != null ? result2.val : "null"));

        // Case 3: One list empty
        ListNode headA3 = null;
        ListNode headB3 = new ListNode(1);

        ListNode result3 = solution.getIntersectionNode(headA3, headB3);
        System.out.println("Test Case 3 Intersection at: " + (result3 != null ? result3.val : "null"));

        // Case 4: Both empty
        ListNode headA4 = null;
        ListNode headB4 = null;

        ListNode result4 = solution.getIntersectionNode(headA4, headB4);
        System.out.println("Test Case 4 Intersection at: " + (result4 != null ? result4.val : "null"));

        // Case 5: Intersection at head
        ListNode commonHead = new ListNode(99);
        ListNode headA5 = commonHead;
        ListNode headB5 = commonHead;

        ListNode result5 = solution.getIntersectionNode(headA5, headB5);
        System.out.println("Test Case 5 Intersection at: " + (result5 != null ? result5.val : "null"));
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // Edge case
        if (headA == null || headB == null) return null;

        // Initialize two pointers to traverse the lists
        ListNode d1 = headA;
        ListNode d2 = headB;

        // Traverse both lists until the pointers meet
        while (d1 != d2) {
            // Move both the pointers by one place
            d1 = d1.next;
            d2 = d2.next;

            // If intersection is found
            if (d1 == d2) return d1;

            // If either of the two pointers reaches end, place at the front of next linked list
            if (d1 == null) d1 = headB;
            if (d2 == null) d2 = headA;
        }

        // Return the intersection node
        return d1;
    }

    // Solution 2 TIME - O(M+N) SPACE - O(1)
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode t1 = headA, t2 = headB;
        int n1 = 0, n2 = 0;
        while (t1 != null) {
            n1++;
            t1 = t1.next;
        }

        while (t2 != null) {
            n2++;
            t2 = t2.next;
        }

        ListNode ans;
        if (n1 > n2) {
            ans = solve(headA, headB, n1 - n2);
        } else {
            ans = solve(headB, headA, n2 - n1);
        }
        return ans;
    }

    private ListNode solve(ListNode headA, ListNode headB, int i) {
        while (i != 0) {
            i--;
            headA = headA.next;
        }

        while (headA != null) {
            if (headA.equals(headB))
                return headA;

            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    // Solution 1 TIME - O(M+N) SPACE - O(M)
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        // Create a hash set to store the nodes
        // Of the first list
        HashSet<ListNode> nodes_set = new HashSet<>();

        // Traverse the first linked list
        // And add all its nodes to the set
        while (headA != null) {
            nodes_set.add(headA);
            headA = headA.next;
        }

        // Traverse the second linked list
        // And check for intersection
        while (headB != null) {
            // If a node from the second list is found in the set,
            // It means there is an intersection
            if (nodes_set.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }

        // No intersection found, return null
        return null;
    }
}
