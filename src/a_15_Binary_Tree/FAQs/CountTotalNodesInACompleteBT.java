package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

public class CountTotalNodesInACompleteBT {
    public static void main(String[] args) {
        // Input: root = [1,2,3,4,5,6]

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        System.out.println(countNodes(root)); // 6

        // Input : root = [1, 2, 3, 4, 5, 6, 7, 8, 9]

        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);
        root2.right.left = new TreeNode(6);
        root2.right.right = new TreeNode(7);
        root2.left.left.left = new TreeNode(8);
        root2.left.left.right = new TreeNode(9);

        System.out.println(countNodes(root2)); // 9
    }

    private static int countNodes(TreeNode root) {
        return 0;
    }
}
