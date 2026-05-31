package a_16_Binary_Search_Tree.Medium;

import a_15_Binary_Tree.Custom.TreeNode;

/**
 * Check if a Binary Tree is a valid BST.
 * <p>
 * Method 1 — Range validation (top-down):
 * Pass allowed [min, max] range; every node must fall strictly inside it.
 * Uses Long.MIN/MAX to handle edge cases with Integer.MIN/MAX node values.
 * <p>
 * Method 2 — Inorder traversal:
 * Inorder of a valid BST is strictly increasing.
 * Track previous value; if current <= prev, not a BST.
 * <p>
 * Time  : O(N) — every node visited once.
 * Space : O(H) — recursion stack.
 */
public class CheckIfTreeIsBSTOrNot {

    // ─────────────────────────────────────────────────────────────
    // Method 1: Range validation (top-down)
    // ─────────────────────────────────────────────────────────────

    /*
     * Mental Model:
     *
     *   every node must satisfy: min < node.val < max
     *   go left  → upper bound  tightens to node.val
     *   go right → lower bound  tightens to node.val
     *
     *   start with (-∞, +∞); narrow as you descend.
     */
    public boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) return true;

        // Node must lie strictly within (min, max).
        if (node.val <= min || node.val >= max) return false;

        // Left subtree: all values must be < node.val (tighten max).
        // Right subtree: all values must be > node.val (tighten min).
        return validate(node.left, min, node.val)
                && validate(node.right, node.val, max);
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: Inorder traversal — strictly increasing check
    // ─────────────────────────────────────────────────────────────

    /*
     * Mental Model:
     *
     *   inorder of a BST = sorted ascending
     *   traverse inorder; if current <= previous → not a BST
     *
     *   use long[] for prev so it resets correctly per call (avoids instance-field bug).
     */
    public boolean isValidBSTInOrder(TreeNode root) {
        // long[]{prev} — mutable wrapper so recursion can share and update it.
        return inorder(root, new long[]{Long.MIN_VALUE});
    }

    private boolean inorder(TreeNode node, long[] prev) {
        if (node == null) return true;

        // Check left subtree first (inorder).
        if (!inorder(node.left, prev)) return false;

        // Current node must be strictly greater than the previous inorder value.
        if (node.val <= prev[0]) return false;
        prev[0] = node.val;

        // Check right subtree.
        return inorder(node.right, prev);
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        CheckIfTreeIsBSTOrNot obj = new CheckIfTreeIsBSTOrNot();

        // TC1: Valid BST
        //       5
        //      / \
        //     3   6
        //    / \   \
        //   2   4   7
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(7);
        System.out.println("TC1 Range   : " + obj.isValidBST(root1));        // true
        System.out.println("TC1 Inorder : " + obj.isValidBSTInOrder(root1)); // true

        // TC2: Invalid BST (left subtree has 4 > 3)
        //       5
        //      / \
        //     3   6
        //    / \   \
        //   4   2   7
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(6);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(2);
        root2.right.right = new TreeNode(7);
        System.out.println("TC2 Range   : " + obj.isValidBST(root2));        // false
        System.out.println("TC2 Inorder : " + obj.isValidBSTInOrder(root2)); // false

        // TC3: Simple valid BST
        //   2
        //  / \
        // 1   3
        TreeNode root3 = new TreeNode(2);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(3);
        System.out.println("TC3 Range   : " + obj.isValidBST(root3));        // true
        System.out.println("TC3 Inorder : " + obj.isValidBSTInOrder(root3)); // true

        // TC4: Tricky — node violates ancestor constraint (not just parent)
        //       10
        //      /
        //     5
        //      \
        //       15   ← 15 > 10 violates root constraint
        TreeNode root4 = new TreeNode(10);
        root4.left = new TreeNode(5);
        root4.left.right = new TreeNode(15);
        System.out.println("TC4 Range   : " + obj.isValidBST(root4));        // false
        System.out.println("TC4 Inorder : " + obj.isValidBSTInOrder(root4)); // false

        // TC5: Single node
        TreeNode root5 = new TreeNode(1);
        System.out.println("TC5 Range   : " + obj.isValidBST(root5));        // true
        System.out.println("TC5 Inorder : " + obj.isValidBSTInOrder(root5)); // true
    }
}
