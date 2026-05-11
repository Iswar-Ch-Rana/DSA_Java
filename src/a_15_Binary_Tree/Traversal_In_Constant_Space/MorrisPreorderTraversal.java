package a_15_Binary_Tree.Traversal_In_Constant_Space;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Morris Preorder Traversal — O(1) space, no stack/recursion.
 * <p>
 * Idea: Temporarily thread each node's inorder predecessor's right pointer back
 * to the current node, allowing us to return "up" the tree without a stack.
 * <p>
 * Two phases per node (when left exists):
 * Phase 1 — thread absent  : COLLECT current, create thread, go LEFT.
 * Phase 2 — thread present : remove thread, go RIGHT.
 * <p>
 * Contrast with Morris Inorder:
 * Inorder  → collect in Phase 2 (after thread removal)
 * Preorder → collect in Phase 1 (before thread creation)  ← only difference
 * <p>
 * Time  : O(N)  — each edge traversed at most twice.
 * Space : O(1)  — no auxiliary stack; threads are temporary.
 */
public class MorrisPreorderTraversal {

    public List<Integer> getPreorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        TreeNode current = root;

        while (current != null) {

            if (current.left == null) {
                // No left subtree — collect and move right.
                result.add(current.val);
                current = current.right;
            } else {
                // Find inorder predecessor (rightmost node in left subtree).
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Phase 1: thread absent → COLLECT, create thread, go left.
                    result.add(current.val);
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Phase 2: thread present → remove thread, go right.
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MorrisPreorderTraversal morris = new MorrisPreorderTraversal();

        // TC1: Right-skewed with left child
        //   1
        //    \
        //     2
        //    /
        //   3
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(3);
        System.out.println("TC1: " + morris.getPreorder(root1)); // [1, 2, 3]

        // TC2: Left-heavy tree
        //     1
        //    /
        //   4
        //  / \
        // 4   2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(4);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(2);
        System.out.println("TC2: " + morris.getPreorder(root2)); // [1, 4, 4, 2]

        // TC3: Mixed tree
        //        5
        //       / \
        //      1   2
        //     /   / \
        //    8   4   5
        //     \
        //      6
        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.left.left = new TreeNode(8);
        root3.right.left = new TreeNode(4);
        root3.right.right = new TreeNode(5);
        root3.left.left.right = new TreeNode(6);
        System.out.println("TC3: " + morris.getPreorder(root3)); // [5, 1, 8, 6, 2, 4, 5]

        // TC4: Single node
        System.out.println("TC4: " + morris.getPreorder(new TreeNode(42))); // [42]

        // TC5: Null root
        System.out.println("TC5: " + morris.getPreorder(null)); // []
    }
}
