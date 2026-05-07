package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.*;

/**
 * Minimum time to burn an entire binary tree from a given start node.
 *
 * Core idea (same for both approaches):
 *   Step 1 — Build a child->parent map so we can travel upward during BFS.
 *   Step 2 — BFS from startNode, spreading fire to left, right, and parent each second.
 *
 * Two ways to do Step 1 — swap the call in timeToBurnTree as needed:
 *   buildParentMapDFS  -> recursive DFS  | simple, but risks stack overflow on deep trees
 *   buildParentMapBFS  -> iterative BFS  | safe for all tree shapes, no recursion
 *
 * Time: O(N)  Space: O(N)
 */
public class MinimumTimeTakenToBurnTheBTFromAGivenNode {

    // ─── Entry Point ──────────────────────────────────────────────
    public static int timeToBurnTree(TreeNode root, int start) {
        if (root == null) return 0;

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();

        // Swap buildParentMapDFS  <->  buildParentMapBFS  to change approach.
        TreeNode startNode = buildParentMapDFS(root, parentMap, start);

        return burnFromStart(startNode, parentMap);
    }

    // ─── Step 1a: Recursive DFS (simple; stack overflow risk on skewed trees) ───
    private static TreeNode buildParentMapDFS(TreeNode root, Map<TreeNode, TreeNode> parentMap, int start) {
        if (root == null) return null;
        if (root.val == start) return root;

        if (root.left != null) {
            parentMap.put(root.left, root);
            TreeNode leftResult = buildParentMapDFS(root.left, parentMap, start);
            if (leftResult != null) return leftResult;
        }

        if (root.right != null) {
            parentMap.put(root.right, root);
            return buildParentMapDFS(root.right, parentMap, start);
        }

        return null;
    }

    // ─── Step 1b: Iterative BFS (safe for all tree shapes, no recursion) ────────
    private static TreeNode buildParentMapBFS(TreeNode root, Map<TreeNode, TreeNode> parentMap, int start) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        TreeNode startNode = null;

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.val == start) startNode = current;

            if (current.left != null) {
                parentMap.put(current.left, current);
                queue.add(current.left);
            }
            if (current.right != null) {
                parentMap.put(current.right, current);
                queue.add(current.right);
            }
        }

        return startNode;
    }

    // ─── Step 2: BFS fire spread — shared by both approaches ─────────────────────
    private static int burnFromStart(TreeNode startNode, Map<TreeNode, TreeNode> parentMap) {
        if (startNode == null) return 0;

        Queue<TreeNode> queue = new ArrayDeque<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.add(startNode);
        visited.add(startNode);

        int time = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                if (current == null) continue;

                enqueueIfUnvisited(parentMap.get(current), queue, visited); // spread up to parent
                enqueueIfUnvisited(current.left, queue, visited); // spread to left child
                enqueueIfUnvisited(current.right, queue, visited); // spread to right child
            }

            time++;
        }

        // last iteration increments time with no new nodes burned
        return time - 1;
    }

    private static void enqueueIfUnvisited(TreeNode node, Queue<TreeNode> queue, Set<TreeNode> visited) {
        if (node != null && !visited.contains(node)) {
            queue.add(node);
            visited.add(node);
        }
    }

    // ─── Main ────────────────────────────────────────────────────
    public static void main(String[] args) {
        // Test Case 1: root = [1, 2, 3, 4, null, 5, 6, null, 7], start = 1  -> expected: 3
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);
        System.out.println("TC1: " + timeToBurnTree(root, 1)); // 3

        // Test Case 2: root = [1, 2, 3, null, 5, null, 4], start = 4  -> expected: 4
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.right = new TreeNode(5);
        root2.right.right = new TreeNode(4);
        System.out.println("TC2: " + timeToBurnTree(root2, 4)); // 4

        // Test Case 3: root = [1, 2, 3, 6, 5, 8, 4], start = 4  -> expected: 4
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.left = new TreeNode(6);
        root3.left.right = new TreeNode(5);
        root3.right.left = new TreeNode(8);
        root3.right.right = new TreeNode(4);
        System.out.println("TC3: " + timeToBurnTree(root3, 4)); // 4

        // Test Case 4: Single node, start = root  -> expected: 0
        TreeNode root4 = new TreeNode(1);
        System.out.println("TC4: " + timeToBurnTree(root4, 1)); // 0
    }
}
