package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.*;

/// ### Bottom View of Binary Tree
///
/// **Logic:**
/// 1. Use **Vertical Order Traversal** (BFS with horizontal distance).
/// 2. Track horizontal distance `line`: root=0, left=-1, right=+1.
/// 3. Use a `TreeMap` to store `line` -> `node.val`.
/// 4. Overwrite values in the map as you go down; the last node seen at each `line` is the bottom-most.
///
/// **Complexity Analysis:**
/// - **Time**: O(N log W) — N nodes visited, W width of tree (TreeMap insertion).
/// - **Space**: O(N) — Queue for BFS and Map for results.
public class BottomViewOfBT {
    public static void main(String[] args) {
        // Test Case 1: Standard Tree
        TreeNode root1 = new TreeNode(20);
        root1.left = new TreeNode(8);
        root1.right = new TreeNode(22);
        root1.left.left = new TreeNode(5);
        root1.left.right = new TreeNode(3);
        root1.right.right = new TreeNode(25);
        root1.left.right.left = new TreeNode(10);
        root1.left.right.right = new TreeNode(14);

        System.out.println("--- Test Case 1 ---");
        TreeNode.printTreeWithLines(root1);
        System.out.println("Bottom View: " + bottomView(root1));
        System.out.println();

        // Test Case 2: Deeper Tree
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(4);
        root2.left.right.right = new TreeNode(5);
        root2.left.right.right.right = new TreeNode(6);

        System.out.println("--- Test Case 2 (Skewed/Deep) ---");
        TreeNode.printTreeWithLines(root2);
        System.out.println("Bottom View: " + bottomView(root2));
        System.out.println();
    }

    static class Pair {
        TreeNode node;
        int line;

        Pair(TreeNode node, int line) {
            this.node = node;
            this.line = line;
        }
    }

    /*
     * Mental Model:
     *
     *   BFS with horizontal distance (line):
     *     root = 0, left = line-1, right = line+1
     *     OVERWRITE map[line] on every visit
     *     last node seen at each line = bottom-most
     *   TreeMap keeps lines sorted left → right
     */
    private static List<Integer> bottomView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        // TreeMap keeps horizontal distance sorted (left → right)
        Map<Integer, Integer> map = new TreeMap<>();

        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            TreeNode node = p.node;
            int line = p.line;

            // Overwrite existing value for this horizontal line
            // The last node processed at this line is the bottom-most
            map.put(line, node.val);

            if (node.left != null) {
                queue.offer(new Pair(node.left, line - 1));
            }

            if (node.right != null) {
                queue.offer(new Pair(node.right, line + 1));
            }
        }

        ans.addAll(map.values());
        return ans;
    }
}
