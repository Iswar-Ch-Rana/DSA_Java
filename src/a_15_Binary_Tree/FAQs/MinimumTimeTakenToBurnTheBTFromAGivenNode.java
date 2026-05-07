package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.*;

/**
 * Minimum time to burn an entire binary tree from a given start node.
 * Idea:
 * 1) Build parent links + find start node using DFS.
 * 2) Run BFS from start, spreading fire to left, right, and parent each second.
 * Time  : O(N)
 * Space : O(N)
 */
public class MinimumTimeTakenToBurnTheBTFromAGivenNode {
    public static int timeToBurnTree(TreeNode root, int start) {
        if (root == null) {
            return 0;
        }

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        TreeNode startNode = buildParentMapAndFindStart(root, parentMap, start);

        return burnFromStart(startNode, parentMap);
    }

    private static int burnFromStart(TreeNode startNode, Map<TreeNode, TreeNode> parentMap) {
        if (startNode == null) {
            return 0;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(startNode);
        visited.add(startNode);

        int time = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current == null) {
                    continue;
                }

                enqueueIfUnvisited(parentMap.get(current), queue, visited);
                enqueueIfUnvisited(current.left, queue, visited);
                enqueueIfUnvisited(current.right, queue, visited);
            }

            time++;
        }

        return time - 1;

    }

    private static void enqueueIfUnvisited(TreeNode node, Queue<TreeNode> queue, Set<TreeNode> visited) {
        if (node != null && !visited.contains(node)) {
            queue.add(node);
            visited.add(node);
        }
    }

    private static TreeNode buildParentMapAndFindStart(TreeNode root, Map<TreeNode, TreeNode> parentMap, int start) {
        if (root == null) return null;

        if (root.val == start) return root;

        if (root.left != null) {
            parentMap.put(root.left, root);
            TreeNode leftResult = buildParentMapAndFindStart(root.left, parentMap, start);
            if (leftResult != null) return leftResult;
        }

        if (root.right != null) {
            parentMap.put(root.right, root);
            TreeNode rightResult = buildParentMapAndFindStart(root.right, parentMap, start);
            return rightResult;
        }

        return null;
    }

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
}
