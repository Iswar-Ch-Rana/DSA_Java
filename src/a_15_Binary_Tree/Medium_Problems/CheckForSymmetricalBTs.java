package a_15_Binary_Tree.Medium_Problems;

import a_15_Binary_Tree.Custom.TreeNode;

public class CheckForSymmetricalBTs {
    public static void main(String[] args) {
        // [1, 2, 2, 3, 4, 4, 3]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);
        System.out.println(isSymmetric(root));

        // [1, 2, 2, null, 3, null, 3]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(2);
        root1.left.right = new TreeNode(3);
        root1.right.right = new TreeNode(3);
        System.out.println(isSymmetric(root1));

        // [1, 2, 2, 3, null, null, 3]
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.right.right = new TreeNode(3);
        System.out.println(isSymmetric(root2));
    }

    public static boolean isSymmetric(TreeNode root) {
        return checkSymmetric(root.left, root.right);
    }

    private static boolean checkSymmetric(TreeNode left, TreeNode right) {
        // Case 1: both null
        if (left == null && right == null) return true;

        // Case 2: one null, one not
        if (left == null || right == null) return false;

        // Case 3: values differ
        if (left.val != right.val) return false;

        // Case 4: check mirror children
        return checkSymmetric(left.left, right.right)
                && checkSymmetric(left.right, right.left);
    }

}
