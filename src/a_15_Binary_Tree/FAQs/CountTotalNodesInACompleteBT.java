package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

/**
 * Count total nodes in a Complete Binary Tree — 3 approaches.
 * <p>
 * Method 1 (Optimized): Check left/right boundary heights.
 * If equal -> perfect subtree -> nodes = 2^h - 1. Else recurse.
 * Time: O((log N)^2)  Space: O(log N)
 * <p>
 * Method 2 (Naive): Simple recursion, works on any binary tree.
 * Time: O(N)  Space: O(H)
 * <p>
 * Method 3 (Binary Search on Last Row): Find whether last-row leaf at a
 * given index exists using bitwise path traversal.
 * Time: O((log N)^2)  Space: O(1)
 */
public class CountTotalNodesInACompleteBT {

    // ─────────────────────────────────────────────────────────────
    // Method 1: Perfect-subtree shortcut (boundary height comparison)
    // ─────────────────────────────────────────────────────────────
    /*
     * Mental Model:
     *
     *   if leftDepth == rightDepth → perfect subtree → return (2^h - 1)
     *   else → recurse on left and right, add 1 for root
     *
     *   Key: leftDepth goes left all the way, rightDepth goes right all the way
     */
    private static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftDepth = getLeftHeight(root);
        int rightDepth = getRightHeight(root);

        // Equal depths -> subtree is perfect -> 2^h - 1 nodes (no traversal needed)
        if (leftDepth == rightDepth) {
            return (int) ((1L << leftDepth) - 1);
        }

        // Not perfect -> recurse on both sides + root
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    private static int getLeftHeight(TreeNode root) {
        int height = 0;
        TreeNode current = root;
        while (current != null) {
            height++;
            current = current.left;
        }
        return height;
    }

    private static int getRightHeight(TreeNode root) {
        int height = 0;
        TreeNode current = root;
        while (current != null) {
            height++;
            current = current.right;
        }
        return height;
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: Naive recursive count (works on any binary tree)
    // ─────────────────────────────────────────────────────────────
    private static int countNodesNaive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // Count left subtree + right subtree + this node
        return countNodesNaive(root.left) + countNodesNaive(root.right) + 1;
    }

    // ─────────────────────────────────────────────────────────────
    // Method 3: Binary search on last row
    //   - Tree height h -> last row has indices [2^(h-1), 2^h - 1]
    //   - Binary search: for mid index, check if that leaf node exists
    //     by traversing path using bits (MSB -> LSB tells go left/right)
    // ─────────────────────────────────────────────────────────────
    private static int countNodesBinarySearch(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int h = getLeftHeight(root) - 1; // height of tree (0-indexed)

        // All nodes exist above last row: 2^h - 1
        // Binary search on last row: indices [2^h .. 2^(h+1) - 1]
        long lo = (long) Math.pow(2, h);
        long hi = (long) Math.pow(2, h + 1) - 1;

        while (lo < hi) {
            long mid = lo + (hi - lo + 1) / 2;
            if (nodeExists(root, h, mid)) {
                lo = mid;       // mid node exists, try higher
            } else {
                hi = mid - 1;   // mid node absent, try lower
            }
        }

        // lo is the last existing leaf index -> total nodes = lo (since we 1-index at level 0)
        return (int) lo;
    }

    /**
     * Checks if the leaf node at a given index in the last row exists.
     * Uses bit traversal: bit h-1 down to 0 of `index` tells left(0) or right(1).
     */
    private static boolean nodeExists(TreeNode root, int height, long index) {
        TreeNode current = root;
        for (int i = height - 1; i >= 0; i--) {
            if (current == null) {
                return false;
            }
            // Check the i-th bit of index to decide direction
            if ((index >> i & 1) == 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current != null;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // Test Case 1: root = [1, 2, 3, 4, 5, 6]  -> expected: 6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        System.out.println("TC1 Method1 (Optimized)      : " + countNodes(root));           // 6
        System.out.println("TC1 Method2 (Naive)          : " + countNodesNaive(root));       // 6
        System.out.println("TC1 Method3 (Binary Search)  : " + countNodesBinarySearch(root)); // 6

        System.out.println();

        // Test Case 2: root = [1, 2, 3, 4, 5, 6, 7, 8, 9]  -> expected: 9
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(7);
        root2.left.left.left = new TreeNode(8);
        root2.left.left.right = new TreeNode(9);

        System.out.println("TC2 Method1 (Optimized)      : " + countNodes(root2));            // 9
        System.out.println("TC2 Method2 (Naive)          : " + countNodesNaive(root2));        // 9
        System.out.println("TC2 Method3 (Binary Search)  : " + countNodesBinarySearch(root2)); // 9

        System.out.println();

        // Test Case 3: Single node -> expected: 1
        TreeNode root3 = new TreeNode(1);
        System.out.println("TC3 Method1 (Optimized)      : " + countNodes(root3));             // 1
        System.out.println("TC3 Method2 (Naive)          : " + countNodesNaive(root3));         // 1
        System.out.println("TC3 Method3 (Binary Search)  : " + countNodesBinarySearch(root3));  // 1

        System.out.println();

        // Test Case 4: null -> expected: 0
        System.out.println("TC4 Method1 (Optimized)      : " + countNodes(null));              // 0
        System.out.println("TC4 Method2 (Naive)          : " + countNodesNaive(null));          // 0
        System.out.println("TC4 Method3 (Binary Search)  : " + countNodesBinarySearch(null));   // 0
    }
}
