package a_15_Binary_Tree.Medium_Problems;

import a_15_Binary_Tree.Custom.TreeNode;

public class CheckForBalancedBinaryTree {
    public static void main(String[] args) {
        // [3, 9, 20, null, null, 15, 7]
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println(isBalanced(root));

        // [1, 2, null, null, 3]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.left.left = new TreeNode(3);
        System.out.println(isBalanced(root1));

        // [5, 1, 2, 8, 3, null, 5, null, 4]
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.left.left = new TreeNode(8);
        root2.left.right = new TreeNode(3);
        root2.left.right.left = new TreeNode(4);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(5);
        System.out.println(isBalanced(root2));

    }

    public static boolean isBalanced(TreeNode root) {
        int height = checkHeight(root);
        return height != -1;
    }

    public static int checkHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = checkHeight(node.left);
        if (leftHeight == -1) return -1;

        int rightHeight = checkHeight(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1)
            return -1;

        return 1 + Math.max(leftHeight, rightHeight);
    }
}
