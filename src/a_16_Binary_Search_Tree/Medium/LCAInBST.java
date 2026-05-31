package a_16_Binary_Search_Tree.Medium;

import a_15_Binary_Tree.Custom.TreeNode;

/**
 * Lowest Common Ancestor (LCA) in a BST.
 * <p>
 * BST property makes this much simpler than LCA in a generic binary tree:
 * - If both p and q are smaller  → LCA is in left subtree.
 * - If both p and q are larger   → LCA is in right subtree.
 * - Otherwise (they split here)  → current node is the LCA.
 * <p>
 * Method 1 — Recursive : O(H) time, O(H) space (stack).
 * Method 2 — Iterative : O(H) time, O(1) space ← preferred.
 */
public class LCAInBST {

    /*
     * Mental Model:
     *
     *   if both p, q < root → go left
     *   if both p, q > root → go right
     *   else                → root is the split point = LCA
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: Recursive
    // ─────────────────────────────────────────────────────────────
    TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (p.val < root.val && q.val < root.val)
            return lca(root.left, p, q);   // both in left subtree
        else if (p.val > root.val && q.val > root.val)
            return lca(root.right, p, q);  // both in right subtree
        else
            return root;                   // split point → this is the LCA
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: Iterative — O(1) space, preferred
    // ─────────────────────────────────────────────────────────────
    TreeNode lcaIterative(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode current = root;

        while (current != null) {
            if (p.val < current.val && q.val < current.val)
                current = current.left;    // both in left subtree
            else if (p.val > current.val && q.val > current.val)
                current = current.right;   // both in right subtree
            else
                return current;            // split point → this is the LCA
        }

        return null; // p or q not in tree
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        LCAInBST sol = new LCAInBST();

        // TC1:
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

        print(sol, root1, root1.left.left, root1.left.right);   // LCA(2,4) = 3
        print(sol, root1, root1.left.left, root1.right.right);  // LCA(2,7) = 5
        print(sol, root1, root1.left, root1.right);             // LCA(3,6) = 5  (one on each side)
        print(sol, root1, root1.left.left, root1.left);         // LCA(2,3) = 3  (one is ancestor)

        // TC2:
        //     2
        //    / \
        //   1   4
        //      / \
        //     3   6
        TreeNode root2 = new TreeNode(2);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.right.left = new TreeNode(3);
        root2.right.right = new TreeNode(6);

        print(sol, root2, root2.left, root2.right.right);       // LCA(1,6) = 2
        print(sol, root2, root2.right.left, root2.right.right); // LCA(3,6) = 4
    }

    // Helper to print both recursive and iterative results side-by-side.
    private static void print(LCAInBST sol, TreeNode root, TreeNode p, TreeNode q) {
        TreeNode r = sol.lca(root, p, q);
        TreeNode i = sol.lcaIterative(root, p, q);
        System.out.printf("LCA(%d, %d) → recursive=%s  iterative=%s%n",
                p.val, q.val,
                r != null ? r.val : "null",
                i != null ? i.val : "null");
    }
}
