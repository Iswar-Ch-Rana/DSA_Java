package a_16_Binary_Search_Tree.Medium;

import a_15_Binary_Tree.Custom.TreeNode;

/**
 * Insert a value into a BST.
 * <p>
 * Idea: BST property guides us to the exact null slot where the new node belongs.
 * Walk left/right until you hit null — insert there.
 * <p>
 * Time  : O(H) — H = height; O(log N) avg, O(N) worst (skewed).
 * Space : O(H) recursive stack | O(1) iterative.
 */
public class InsertGivenNodeInBST {

    /*
     * Mental Model:
     *
     *   if null → insert here (base case / leaf slot found)
     *
     *   if val < root → go left
     *   if val > root → go right
     *
     *   return root  (re-link parent on the way back up)
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: Recursive
    // ─────────────────────────────────────────────────────────────
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val); // leaf slot found

        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);   // go left
        } else {
            root.right = insertIntoBST(root.right, val); // go right
        }

        return root; // re-link parent on the way back up
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: Iterative — O(1) space, preferred for skewed trees
    // ─────────────────────────────────────────────────────────────
    public TreeNode insertIntoBSTIterative(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) return newNode;

        TreeNode current = root;

        while (true) {
            if (val < current.val) {
                if (current.left == null) {
                    current.left = newNode;
                    break;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = newNode;
                    break;
                }
                current = current.right;
            }
        }

        return root;
    }

    public static void main(String[] args) {
        InsertGivenNodeInBST solution = new InsertGivenNodeInBST();

        // TC1: Insert 5 into BST — lands as right child of 4
        //       4                    4
        //      / \       →          / \
        //     2   7                2   7
        //    / \                  / \ /
        //   1   3                1  3 5
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        System.out.println("TC1 Recursive:");
        TreeNode.printTreeWithLines(solution.insertIntoBST(root1, 5));

        // TC2: Insert 25 into BST — lands as left child of 30
        //         40                      40
        //        /  \        →           /  \
        //       20   60                 20   60
        //      / \                     / \
        //     10  30                  10  30
        //                                /
        //                               25
        TreeNode root2 = new TreeNode(40);
        root2.left = new TreeNode(20);
        root2.right = new TreeNode(60);
        root2.left.left = new TreeNode(10);
        root2.left.right = new TreeNode(30);
        System.out.println("TC2 Iterative:");
        TreeNode.printTreeWithLines(solution.insertIntoBSTIterative(root2, 25));

        // TC3: Insert into null root
        System.out.println("TC3 null root:");
        TreeNode.printTreeWithLines(solution.insertIntoBST(null, 10));

        // TC4: Insert into single node (goes right)
        TreeNode root4 = new TreeNode(5);
        System.out.println("TC4 single node, insert 7:");
        TreeNode.printTreeWithLines(solution.insertIntoBST(root4, 7));

        // TC5: Insert into single node (goes left)
        TreeNode root5 = new TreeNode(5);
        System.out.println("TC5 single node, insert 3:");
        TreeNode.printTreeWithLines(solution.insertIntoBSTIterative(root5, 3));
    }
}
