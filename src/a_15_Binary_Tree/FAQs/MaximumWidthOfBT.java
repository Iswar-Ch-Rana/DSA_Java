package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

public class MaximumWidthOfBT {
    public static void main(String[] args) {
        //  root = [1, 3, 2, 5, 3, null, 9]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(9);
        System.out.println(widthOfBinaryTree(root)); // Output: 4

        //  root = [1, 3, 2, 5, null, null, 9, 6, null, 7]
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(2);
        root1.left.left = new TreeNode(5);
        root1.right.right = new TreeNode(9);
        root1.left.left.left = new TreeNode(6);
        root1.left.left.right = new TreeNode(7);
        System.out.println(widthOfBinaryTree(root1)); // Output: 4

        //  root = [5, 1, 2, 8, null, 4, 5, null, 6]
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(8);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        root2.right.right.left = new TreeNode(6);
        System.out.println(widthOfBinaryTree(root2)); // Output: 4
    }

    static class Pair {
        TreeNode node;
        long index;

        public Pair(TreeNode node, long index) {
            this.node = node;
            this.index = index;
        }
    }

    public static int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        long maxWidth = 0;

        Queue<Pair> q = new ArrayDeque<>();
        q.offer(new Pair(root, 0L));

        while (!q.isEmpty()) {
            int size = q.size();

            long first = q.peek().index;          // first index of level
            long last = first;

            for (int i = 0; i < size; i++) {
                Pair curr = q.poll();

                // normalize index to prevent overflow
                long index = curr.index - first;
                last = index;

                if (curr.node.left != null) {
                    q.offer(new Pair(curr.node.left, 2 * index + 1));
                }

                if (curr.node.right != null) {
                    q.offer(new Pair(curr.node.right, 2 * index + 2));
                }
            }

            maxWidth = Math.max(maxWidth, last + 1);
        }

        return (int) maxWidth;
    }
}
