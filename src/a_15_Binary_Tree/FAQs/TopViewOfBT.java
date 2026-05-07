package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.*;

/// ### Top View of Binary Tree
///
/// **Logic:**
/// 1. Use **Vertical Order Traversal** (BFS with horizontal distance).
/// 2. Track horizontal distance `line`: root=0, left=-1, right=+1.
/// 3. Use a `TreeMap` to store `line` -> `node.val`.
/// 4. Only store the first node encountered for each `line` (the top-most).
///
/// **Complexity Analysis:**
/// - **Time**: O(N log W) — N nodes visited, W width of tree (TreeMap insertion).
/// - **Space**: O(N) — Queue for BFS and Map for results.
public class TopViewOfBT {
    public static void main(String[] args) {
        // Test Case 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);

        System.out.println("--- Test Case 1 ---");
        System.out.println("Top View: " + topView(root1));
        System.out.println();

        // Test Case 2
        TreeNode root2 = new TreeNode(10);
        root2.left = new TreeNode(20);
        root2.right = new TreeNode(30);
        root2.left.left = new TreeNode(40);
        root2.left.right = new TreeNode(60);
        root2.right.left = new TreeNode(90);
        root2.right.right = new TreeNode(100);

        System.out.println("--- Test Case 2 ---");
        System.out.println("Top View: " + topView(root2));
        System.out.println();

        // Test Case 3
        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(1);
        root3.right = new TreeNode(2);
        root3.left.left = new TreeNode(8);
        root3.right.left = new TreeNode(4);
        root3.right.right = new TreeNode(5);
        root3.right.right.left = new TreeNode(6);

        System.out.println("--- Test Case 3 ---");
        System.out.println("Top View: " + topView(root3));
        System.out.println();
    }

    static class Pair {
        TreeNode node;
        int line;

        public Pair(TreeNode node, int line) {
            this.node = node;
            this.line = line;
        }
    }

    private static List<Integer> topView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        // TreeMap keeps horizontal distance sorted (left → right)
        Map<Integer, Integer> map = new TreeMap<>();

        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            TreeNode node = p.node;
            int line = p.line;

            // Only put if the horizontal line hasn't been seen yet
            // The first node processed at this line is the top-most
            if (!map.containsKey(line)) {
                map.put(line, node.val);
            }

            if (node.left != null) {
                queue.add(new Pair(node.left, line - 1));
            }

            if (node.right != null) {
                queue.add(new Pair(node.right, line + 1));
            }
        }

        ans.addAll(map.values());
        return ans;
    }
}
