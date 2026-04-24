package a_15_Binary_Tree.Medium_Problems;

import a_15_Binary_Tree.Custom.TreeNode;

public class CheckIfTwoTreesAreIdenticalOrNot {
    public static void main(String[] args) {
        // [1, 4, null, 4, 2]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        System.out.println(isIdentical(root, root));
    }

    public static boolean isIdentical(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val)
            return false;
        return isIdentical(root1.left, root2.left) && isIdentical(root1.right, root2.right);
    }
}
