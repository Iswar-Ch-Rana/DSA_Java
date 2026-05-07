package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

/// ### Lowest Common Ancestor (LCA) in Binary Tree
///
/// **Logic:**
/// 1. Use **DFS (Recursion)** to search for nodes `p` and `q`.
/// 2. If current node is `null`, `p`, or `q`, return it.
/// 3. Recurse on left and right subtrees.
/// 4. If both recursion calls return non-null, the current node is the LCA.
/// 5. If only one call returns non-null, return that value (LCA is deeper or only one node found).
///
/// **Complexity Analysis:**
/// - **Time**: O(N) — Each node is visited once.
/// - **Space**: O(H) — Recursion stack depth.
public class LCAInBT {
    public static void main(String[] args) {
        // Test Case 1
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(5);
        root1.right = new TreeNode(1);
        root1.left.left = new TreeNode(6);
        root1.left.right = new TreeNode(2);
        root1.right.left = new TreeNode(0);
        root1.right.right = new TreeNode(8);
        root1.left.right.left = new TreeNode(7);
        root1.left.right.right = new TreeNode(4);

        System.out.println("--- Test Case 1 ---");
        TreeNode p1 = root1.left; // 5
        TreeNode q1 = root1.right; // 1
        System.out.println("LCA of " + p1.val + " and " + q1.val + ": " + lowestCommonAncestor(root1, p1, q1).val);
        System.out.println();

        // Test Case 2
        TreeNode root2 = root1; // Same tree
        System.out.println("--- Test Case 2 ---");
        TreeNode p2 = root1.left; // 5
        TreeNode q2 = root1.left.right.right; // 4
        System.out.println("LCA of " + p2.val + " and " + q2.val + ": " + lowestCommonAncestor(root2, p2, q2).val);
        System.out.println();

        // Test Case 3
        TreeNode root3 = new TreeNode(7);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.left.left = new TreeNode(8);
        root3.left.right = new TreeNode(10);
        root3.right.left = new TreeNode(4);
        root3.right.right = new TreeNode(5);
        root3.left.right.left = new TreeNode(6);

        System.out.println("--- Test Case 3 ---");
        TreeNode p3 = root3.left.right.left; // 6
        TreeNode q3 = root3.left.right; // 10
        System.out.println("LCA of " + p3.val + " and " + q3.val + ": " + lowestCommonAncestor(root3, p3, q3).val);
        System.out.println();
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: if root is null or we found either p or q
        if (root == null || root == p || root == q) return root;

        // Search in left and right subtrees
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // Result phase:
        // If both sides return something, root is the lowest common ancestor
        if (left != null && right != null) {
            return root;
        }

        // Otherwise return the side that found something
        return left != null ? left : right;
    }
}
