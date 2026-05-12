package a_15_Binary_Tree.Traversal_In_Constant_Space;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Morris Inorder Traversal — O(N) time, O(1) space (no stack, no recursion).
 * <p>
 * Idea: Temporarily thread each node's inorder predecessor's right pointer back
 * to the current node so we can "return" to it after finishing its left subtree.
 * <p>
 * Two phases per node with a left child:
 * Phase 1 (thread not yet created) → create thread, go left.
 * Phase 2 (thread already exists)  → thread found means left subtree done;
 * remove thread, collect current, go right.
 * <p>
 * Time  : O(N) — each edge is traversed at most twice (thread create + remove).
 * Space : O(1) — no extra data structure; threads are temporary modifications.
 */
public class MorrisInorderTraversal {

    /*
     * Mental Model:
     *
     *   if no left:
     *       VISIT, go right
     *
     *   else:
     *       find predecessor (rightmost of left subtree)
     *
     *       if thread absent:
     *           create thread, go left
     *
     *       if thread present:
     *           remove thread, VISIT, go right
     */
    public List<Integer> getInorder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {

            if (current.left == null) {
                // No left subtree — collect and move right.
                result.add(current.val);
                current = current.right;
            } else {
                // Find inorder predecessor: rightmost node in left subtree.
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Phase 1: Thread not yet created.
                    // Create thread: predecessor → current, then explore left.
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Phase 2: Thread already exists → left subtree fully visited.
                    // Remove thread (restore tree), collect current, move right.
                    predecessor.right = null;
                    result.add(current.val);
                    current = current.right;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MorrisInorderTraversal morris = new MorrisInorderTraversal();

        // Test Case 1: Right-skewed with left child
        //   1
        //    \
        //     2
        //    /
        //   3
        // Expected inorder: [1, 3, 2]
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(3);
        System.out.println("TC1: " + morris.getInorder(root1)); // [1, 3, 2]

        // Test Case 2: Left-heavy tree
        //     1
        //    /
        //   4
        //  / \
        // 4   2
        // Expected inorder: [4, 4, 2, 1]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(4);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(2);
        System.out.println("TC2: " + morris.getInorder(root2)); // [4, 4, 2, 1]

        // Test Case 3: Mixed tree
        //        5
        //       / \
        //      1   2
        //     /   / \
        //    8   4   5
        //     \
        //      6
        // Expected inorder: [8, 6, 1, 5, 4, 2, 5]
        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.left.left = new TreeNode(8);
        root3.right.left = new TreeNode(4);
        root3.right.right = new TreeNode(5);
        root3.left.left.right = new TreeNode(6);
        System.out.println("TC3: " + morris.getInorder(root3)); // [8, 6, 1, 5, 4, 2, 5]

        // Test Case 4: Single node
        TreeNode root4 = new TreeNode(42);
        System.out.println("TC4: " + morris.getInorder(root4)); // [42]

        // Test Case 5: null root
        System.out.println("TC5: " + morris.getInorder(null)); // []
    }
}
