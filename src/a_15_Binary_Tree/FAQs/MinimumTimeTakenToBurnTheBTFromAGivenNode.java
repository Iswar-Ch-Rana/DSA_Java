package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

public class MinimumTimeTakenToBurnTheBTFromAGivenNode {
    public static void main(String[] args) {
        // Input : root = [1, 2, 3, 4, null, 5, 6, null, 7]. target = 1
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);

        System.out.println(timeToBurnTree(root, 1)); // 3

        // Input : root = [1, 2, 3, null, 5, null, 4], target = 4
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(5);
        root2.right.right = new TreeNode(4);

        System.out.println(timeToBurnTree(root2, 4)); // 4

        // Input : root = [1, 2, 3, 6, 5, 8, 4], target = 4
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.left = new TreeNode(6);
        root3.left.right = new TreeNode(5);
        root3.right.left = new TreeNode(8);
        root3.right.right = new TreeNode(4);

        System.out.println(timeToBurnTree(root3, 4)); // 4
    }

    public static int timeToBurnTree(TreeNode root, int start) {
        return 0;
    }
}
