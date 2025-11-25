package a_10_Linked_List.FAQ_Hard;

import java.util.HashMap;
import java.util.Map;

class ListNode1 {
    int val;
    ListNode1 next;
    ListNode1 random;

    ListNode1(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Node(" + val + ")";
    }
}

public class CopyRandomList {

    // Helper: Creates a linked list from the 2D array format [[val, random_index], ...]
    private static ListNode1 createList(int[][] nodes) {
        if (nodes == null || nodes.length == 0) return null;

        ListNode1[] nodeArray = new ListNode1[nodes.length];

        // Create all nodes first
        for (int i = 0; i < nodes.length; i++) {
            nodeArray[i] = new ListNode1(nodes[i][0]);
        }

        // Set next and random pointers
        for (int i = 0; i < nodes.length; i++) {
            if (i + 1 < nodes.length) {
                nodeArray[i].next = nodeArray[i + 1];
            }
            int randomIdx = nodes[i][1];
            if (randomIdx != -1) {
                nodeArray[i].random = nodeArray[randomIdx];
            }
        }

        return nodeArray[0];
    }

    // Helper: Prints the list values in order + random pointers
    private static void printListWithRandom(ListNode1 head) {
        if (head == null) {
            System.out.println("null");
            return;
        }

        Map<ListNode1, Integer> nodeToIndex = new HashMap<>();
        ListNode1 temp = head;
        int index = 0;
        while (temp != null) {
            nodeToIndex.put(temp, index++);
            temp = temp.next;
        }

        ListNode1 curr = head;
        boolean first = true;
        while (curr != null) {
            if (!first) System.out.print(" -> ");
            System.out.print(curr.val);
            first = false;
            curr = curr.next;
        }
        System.out.print("   |   Random pointers: ");

        curr = head;
        first = true;
        while (curr != null) {
            if (!first) System.out.print(", ");
            if (curr.random == null) {
                System.out.print("null");
            } else {
                System.out.print(curr.val + "→" + curr.random.val);
            }
            first = false;
            curr = curr.next;
        }
        System.out.println();
    }

    // Helper: Checks if two lists are deep copies (different objects, same structure/values)
    private static boolean isDeepCopy(ListNode1 original, ListNode1 copy) {
        if (original == copy) return false; // same reference → not deep copy

        while (original != null && copy != null) {
            if (original == copy) return false; // any shared node
            if (original.val != copy.val) return false;
            if ((original.random == null) != (copy.random == null)) return false;
            if (original.random != null && copy.random != null) {
                if (original.random.val != copy.random.val) return false;
            }
            original = original.next;
            copy = copy.next;
        }
        return original == null && copy == null;
    }

    public static void main(String[] args) {
        CopyRandomList solution = new CopyRandomList();

        // Test Case 1 - From first example
        int[][] input1 = {{1, -1}, {2, 0}, {3, 4}, {4, 1}, {5, 2}};
        ListNode1 head1 = createList(input1);
        System.out.println("Test Case 1:");
        printListWithRandom(head1);
        ListNode1 copy1 = solution.copyRandomList(head1);
        System.out.print("Copied:  ");
        printListWithRandom(copy1);
        System.out.println("Is Deep Copy? " + isDeepCopy(head1, copy1));
        System.out.println();

        // Test Case 2
        int[][] input2 = {{5, -1}, {3, -1}, {2, 1}, {1, 1}};
        ListNode1 head2 = createList(input2);
        System.out.println("Test Case 2:");
        printListWithRandom(head2);
        ListNode1 copy2 = solution.copyRandomList(head2);
        System.out.print("Copied:  ");
        printListWithRandom(copy2);
        System.out.println("Is Deep Copy? " + isDeepCopy(head2, copy2));
        System.out.println();

        // Test Case 3 - All random = null
        int[][] input3 = {{10, -1}, {20, -1}, {30, -1}};
        ListNode1 head3 = createList(input3);
        System.out.println("Test Case 3 (no random pointers):");
        printListWithRandom(head3);
        ListNode1 copy3 = solution.copyRandomList(head3);
        System.out.print("Copied:  ");
        printListWithRandom(copy3);
        System.out.println("Is Deep Copy? " + isDeepCopy(head3, copy3));
        System.out.println();

        // Test Case 4 - Single node, random points to itself
        int[][] input4 = {{7, 0}};
        ListNode1 head4 = createList(input4);
        System.out.println("Test Case 4 (single node, random → self):");
        printListWithRandom(head4);
        ListNode1 copy4 = solution.copyRandomList(head4);
        System.out.print("Copied:  ");
        printListWithRandom(copy4);
        System.out.println("Is Deep Copy? " + isDeepCopy(head4, copy4));
        System.out.println();

        // Test Case 5 - null head
        System.out.println("Test Case 5 (null head):");
        ListNode1 copy5 = solution.copyRandomList(null);
        System.out.println("Result: " + copy5);
        System.out.println();

        // Test Case 6 - Negative values
        int[][] input6 = {{-1, -1}, {-2, -1}, {-3, -1}, {10, -1}};
        ListNode1 head6 = createList(input6);
        System.out.println("Test Case 6 (negative values):");
        printListWithRandom(head6);
        ListNode1 copy6 = solution.copyRandomList(head6);
        System.out.print("Copied:  ");
        printListWithRandom(copy6);
        System.out.println("Is Deep Copy? " + isDeepCopy(head6, copy6));
    }

    // Solution 2 TIME O(N) , SPACE - O(1)
    ListNode1 copyRandomList(ListNode1 head) {
        // If the original list is empty, return null
        if (head == null) return null;

        // Insert nodes in between
        insertCopyInBetween(head);
        // Connect random pointers
        connectRandomPointers(head);
        // Retrieve deep copy of linked list
        return getDeepCopyList(head);
    }

    ListNode1 getDeepCopyList(ListNode1 head) {
        ListNode1 temp = head;
        // Create a dummy node
        ListNode1 dummyNode = new ListNode1(-1);
        // Initialize a result pointer
        ListNode1 res = dummyNode;

        while (temp != null) {
            /*Creating a new List by 
            pointing to copied nodes*/
            res.next = temp.next;
            res = res.next;

            /*Disconnect and revert back 
            to the initial state of the 
            original linked list*/
            temp.next = temp.next.next;
            temp = temp.next;
        }
        
        /*Return the deep copy 
        of the list starting 
        from the dummy node*/
        return dummyNode.next;
    }

    void connectRandomPointers(ListNode1 head) {
        ListNode1 temp = head;
        while (temp != null) {
            // Access the copied node
            ListNode1 copyNode = temp.next;

            /*If the original node has a random pointer
            point the copied node's random to the
            corresponding copied random node
            set the copied node's random to null
            if the original random is null*/
            if (temp.random != null) {

                copyNode.random = temp.random.next;
            } else {

                copyNode.random = null;
            }

            // Move to next original node
            temp = temp.next.next;
        }
    }

    void insertCopyInBetween(ListNode1 head) {
        ListNode1 temp = head;
        while (temp != null) {
            ListNode1 nextElement = temp.next;
            // Create a new node with the same data
            ListNode1 copy = new ListNode1(temp.val);

            copy.next = nextElement;

            temp.next = copy;

            temp = nextElement;
        }
    }

    // Solution 1 TIME - O(N) , SPACE - O(N)
    public ListNode1 copyRandomList1(ListNode1 head) {
        HashMap<ListNode1, ListNode1> map = new HashMap<>();

        // Create the map of old and new
        ListNode1 temp = head;
        while (temp != null) {
            ListNode1 newList = new ListNode1(temp.val);
            map.put(temp, newList);
            temp = temp.next;
        }

        temp = head;
        ListNode1 copyHead = map.get(head);
        ListNode1 copyTemp = copyHead;
        while (temp != null) {
            ListNode1 next1 = map.get(temp.next);
            copyTemp.random = map.get(temp.random);
            copyTemp.next = next1;
            copyTemp = copyTemp.next;
            temp = temp.next;
        }
        return copyHead;
    }
}