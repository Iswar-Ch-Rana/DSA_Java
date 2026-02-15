package a_15_Binary_Tree.Medium_Problems;

import a_15_Binary_Tree.Custom.TreeNode;

public class MaximumPathSum {
    public static void main(String[] args) {
        // [1, 2, 3]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        System.out.println(maxPathSum(root));

        // [-10, 9, 20, null, null, 15, 7]
        TreeNode root1 = new TreeNode(-10);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);
        System.out.println(maxPathSum(root1));
    }

    private static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    private static int maxGain(TreeNode node) {
        if (node == null) return 0;

        // 1 Get max contribution from left and right (ignore negatives)
        int leftGain = Math.max(0, maxGain(node.left));
        int rightGain = Math.max(0, maxGain(node.right));

        // 2 Path passing through this node (can take both sides)
        int currentPathSum = node.val + leftGain + rightGain;

        // 3 Update global maximum
        maxSum = Math.max(maxSum, currentPathSum);

        // 4 Return max downward path to parent (only one side)
        return node.val + Math.max(leftGain, rightGain);
    }
}
