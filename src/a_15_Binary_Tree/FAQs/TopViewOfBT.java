package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.*;

public class TopViewOfBT {
    public static void main(String[] args) {
        // [1, 2, 3, 4, 5, 6, 7]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println(topView(root));

        //  [10, 20, 30, 40, 60, 90, 100]
        TreeNode root1 = new TreeNode(10);
        root1.left = new TreeNode(20);
        root1.right = new TreeNode(30);
        root1.left.left = new TreeNode(40);
        root1.left.right = new TreeNode(60);
        root1.right.left = new TreeNode(90);
        root1.right.right = new TreeNode(100);
        System.out.println(topView(root1));

        // [5, 1, 2, 8, null, 4, 5, null, 6]
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(8);
        root2.right.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        root2.right.right.left = new TreeNode(6);
        System.out.println(topView(root2));
    }


    static class Pair {
        public TreeNode node;
        public int HD;

        public Pair(TreeNode node, int HD) {
            this.HD = HD;
            this.node = node;
        }
    }

    private static List<Integer> topView(TreeNode root) {

        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(root, 0));
        TreeMap<Integer, Integer> map = new TreeMap<>();
        while (!queue.isEmpty()) {
            Pair ele = queue.poll();
            TreeNode node = ele.node;
            int HD = ele.HD;

            if (!map.containsKey(HD)) {
                map.put(HD, node.val);
            }

            if (node.left != null) {
                queue.add(new Pair(node.left, HD - 1));
            }

            if (node.right != null) {
                queue.add(new Pair(node.right, HD + 1));
            }

        }
        return new ArrayList<>(map.values());
    }
}
