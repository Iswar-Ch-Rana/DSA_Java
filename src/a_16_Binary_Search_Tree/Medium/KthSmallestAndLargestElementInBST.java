package a_16_Binary_Search_Tree.Medium;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.List;

/**
 * Kth Smallest and Kth Largest element in a BST.
 * <p>
 * Key insight: BST inorder (L → root → R) gives ascending order.
 * BST reverse-inorder (R → root → L) gives descending order.
 * <p>
 * Kth Smallest → inorder,         stop at k-th visit.
 * Kth Largest  → reverse-inorder, stop at k-th visit.
 * <p>
 * Time  : O(H + k) — traverses at most k nodes after reaching the leftmost/rightmost.
 * Space : O(H)     — recursion stack.
 */
public class KthSmallestAndLargestElementInBST {

    /*
     * Mental Model:
     *
     *   kSmallest → inorder (L → root → R):
     *       increment counter on visit
     *       if counter == k → record and stop
     *
     *   kLargest  → reverse-inorder (R → root → L):
     *       same, but visit right first
     */

    // ─────────────────────────────────────────────────────────────
    // Public API
    // ─────────────────────────────────────────────────────────────

    public List<Integer> kSmallestAndLargest(TreeNode root, int k) {
        // int[]{count, result} — int[] used so primitives are mutable across recursive calls.
        int[] smallest = {0, -1};
        int[] largest = {0, -1};

        kSmallest(root, k, smallest);
        kLargest(root, k, largest);

        return List.of(smallest[1], largest[1]);
    }

    // ─────────────────────────────────────────────────────────────
    // Kth Smallest: inorder — L → root → R
    // ─────────────────────────────────────────────────────────────
    private void kSmallest(TreeNode node, int k, int[] state) {
        if (node == null || state[0] == k) return; // early exit once found

        kSmallest(node.left, k, state);

        state[0]++;
        if (state[0] == k) {
            state[1] = node.val;
            return;
        }

        kSmallest(node.right, k, state);
    }

    // ─────────────────────────────────────────────────────────────
    // Kth Largest: reverse-inorder — R → root → L
    // ─────────────────────────────────────────────────────────────
    private void kLargest(TreeNode node, int k, int[] state) {
        if (node == null || state[0] == k) return; // early exit once found

        kLargest(node.right, k, state);

        state[0]++;
        if (state[0] == k) {
            state[1] = node.val;
            return;
        }

        kLargest(node.left, k, state);
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        KthSmallestAndLargestElementInBST sol = new KthSmallestAndLargestElementInBST();

        // TC1:
        //     3
        //    / \
        //   1   4
        //    \
        //     2
        // inorder: [1,2,3,4]  k=1 → smallest=1, largest=4
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(4);
        root1.left.right = new TreeNode(2);
        System.out.println("TC1 k=1: " + sol.kSmallestAndLargest(root1, 1)); // [1, 4]

        // TC2:
        //       5
        //      /  \
        //     3    6
        //    /
        //   2
        //  /
        // 1
        // inorder: [1,2,3,5,6]  k=3 → smallest=3, largest=3
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(2);
        root2.left.left.left = new TreeNode(1);
        System.out.println("TC2 k=3: " + sol.kSmallestAndLargest(root2, 3)); // [3, 3]

        // TC3: same tree as TC1, k=2 → smallest=2, largest=3
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(4);
        root3.left.right = new TreeNode(2);
        System.out.println("TC3 k=2: " + sol.kSmallestAndLargest(root3, 2)); // [2, 3]

        // TC4: single node, k=1 → smallest=10, largest=10
        System.out.println("TC4 k=1: " + sol.kSmallestAndLargest(new TreeNode(10), 1)); // [10, 10]

        // TC5: right-skewed, inorder: [1,2,3,4,5]  k=2 → smallest=2, largest=4
        TreeNode root5 = new TreeNode(1);
        root5.right = new TreeNode(2);
        root5.right.right = new TreeNode(3);
        root5.right.right.right = new TreeNode(4);
        root5.right.right.right.right = new TreeNode(5);
        System.out.println("TC5 k=2: " + sol.kSmallestAndLargest(root5, 2)); // [2, 4]
    }
}
