package a_15_Binary_Tree.Medium_Problems;

import a_15_Binary_Tree.Custom.TreeNode;

public class DiameterOfBinaryTree {
    public static void main(String[] args) {
        //  [1, 2, 3, 4, 5]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(diameterOfBinaryTree(root));

        // [1, 2, 3, null, 4, null, 5]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(5);
        System.out.println(diameterOfBinaryTree(root1));

        // [5, 1, 2, 8, 3, null, 5, null, 4]
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.left.left = new TreeNode(8);
        root2.left.right = new TreeNode(3);
        root2.left.right.left = new TreeNode(4);
        root2.right = new TreeNode(2);
        root2.right.right = new TreeNode(5);
        System.out.println(diameterOfBinaryTree(root2));
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        int[] diameter = new int[1];
        if (root == null) return 0;
        height(root, diameter);
        return diameter[0];
    }

    private static int height(TreeNode node, int[] diameter) {
        if (node == null) return 0;
        int leftHeight = height(node.left, diameter);
        int rightHeight = height(node.right, diameter);
        diameter[0] = Math.max(diameter[0], leftHeight + rightHeight);
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
