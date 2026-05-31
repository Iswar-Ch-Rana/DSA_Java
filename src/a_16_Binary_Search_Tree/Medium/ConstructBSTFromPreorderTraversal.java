package a_16_Binary_Search_Tree.Medium;

import a_15_Binary_Tree.Custom.TreeNode;

/**
 * Construct a BST from its Preorder traversal.
 * <p>
 * Key insight: In preorder, the current element is always the root.
 * BST property tells us: every value >= bound belongs to the right subtree
 * of some ancestor — so we pass a `bound` down to know when to stop building
 * the left subtree and hand control back to the parent.
 * <p>
 * Time  : O(N) — each element processed exactly once.
 * Space : O(H) — recursion stack.
 */
public class ConstructBSTFromPreorderTraversal {

    /*
     * Mental Model:
     *
     *   preorder[i] is always the next root.
     *
     *   if i >= length OR preorder[i] >= bound → stop (out of this subtree's range)
     *
     *   root = preorder[i++]
     *   root.left  = build with bound = root.val   (left must be < root)
     *   root.right = build with bound = parent's bound (right inherits upper limit)
     *
     *   start: bound = Integer.MAX_VALUE (no upper limit at root level)
     */

    // ─────────────────────────────────────────────────────────────
    // Public API
    // ─────────────────────────────────────────────────────────────
    static TreeNode constructBST(int[] preorder) {
        // int[]{index} — mutable wrapper so index advances across recursive calls.
        return build(preorder, new int[]{0}, Integer.MAX_VALUE);
    }

    // ─────────────────────────────────────────────────────────────
    // Recursive builder
    // ─────────────────────────────────────────────────────────────
    private static TreeNode build(int[] preorder, int[] index, int bound) {
        // Stop if all elements consumed, or current element exceeds this subtree's bound.
        if (index[0] >= preorder.length || preorder[index[0]] >= bound) return null;

        TreeNode root = new TreeNode(preorder[index[0]++]);

        // Left subtree: values must be < root.val  → tighten bound to root.val.
        root.left = build(preorder, index, root.val);
        // Right subtree: values must be < parent's bound → pass bound unchanged.
        root.right = build(preorder, index, bound);

        return root;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // TC1: preorder = [8, 5, 1, 7, 10, 12]
        //       8
        //      / \
        //     5   10
        //    / \    \
        //   1   7    12
        // inorder (validation): [1, 5, 7, 8, 10, 12]
        System.out.println("TC1:");
        TreeNode.printTreeWithLines(constructBST(new int[]{8, 5, 1, 7, 10, 12}));

        // TC2: preorder = [1, 3]  → right-skewed
        //   1
        //    \
        //     3
        System.out.println("TC2:");
        TreeNode.printTreeWithLines(constructBST(new int[]{1, 3}));

        // TC3: preorder = [5, 3, 2, 4, 6, 7]
        //       5
        //      / \
        //     3   6
        //    / \   \
        //   2   4   7
        System.out.println("TC3:");
        TreeNode.printTreeWithLines(constructBST(new int[]{5, 3, 2, 4, 6, 7}));

        // TC4: single element
        System.out.println("TC4:");
        TreeNode.printTreeWithLines(constructBST(new int[]{42}));

        // TC5: left-skewed  [5, 4, 3, 2, 1]
        //         5
        //        /
        //       4
        //      /
        //     3 ...
        System.out.println("TC5:");
        TreeNode.printTreeWithLines(constructBST(new int[]{5, 4, 3, 2, 1}));
    }
}
