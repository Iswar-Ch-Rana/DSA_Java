package a_15_Binary_Tree.Medium_Problems;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class MaximumDepthInBT {
    public static void main(String[] args) {
        // [1, 4, null, 4, 2]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        System.out.println(maxDepth(root));
        System.out.println(maxDepth1(root));


    }

    // Iterative Model
    public static int maxDepth1(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> stack = new ArrayDeque<>();
        int depth = 0;

        stack.offer(root);
        while (!stack.isEmpty()) {
            int size = stack.size();
            depth++;

            for (int i = 0; i < size; i++) {
                TreeNode node = stack.poll();
                if (node.left != null) stack.offer(node.left);
                if (node.right != null) stack.offer(node.right);
            }
        }
        return depth;
    }


    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        return 1 + Math.max(left, right);
    }
}
