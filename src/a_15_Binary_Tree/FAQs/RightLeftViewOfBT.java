package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.*;

/**
 * Right and Left Side View of Binary Tree.
 * <p>
 * Right View: DFS — traverse right child first; first node seen at each level is the rightmost.
 * Left View : BFS — peek the first node in the queue at each level (leftmost).
 * <p>
 * Time  : O(N) — every node is visited once.
 * Space : O(H) — recursion stack (DFS) / queue (BFS), where H = tree height.
 */
public class RightLeftViewOfBT {
    // --- Right Side View: DFS (right child first) ---
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // level -> first node encountered (rightmost due to right-first DFS)
        Map<Integer, TreeNode> levelMap = new TreeMap<>();
        if (root == null) return result;

        dfsRight(root, 0, levelMap);

        for (TreeNode node : levelMap.values()) {
            result.add(node.val);
        }

        return result;
    }

    // Traverse right child first; only record the first node seen per level.
    private void dfsRight(TreeNode node, int level, Map<Integer, TreeNode> levelMap) {
        if (node == null) return;

        // First visit at this level = rightmost visible node
        if (!levelMap.containsKey(level)) {
            levelMap.put(level, node);
        }

        dfsRight(node.right, level + 1, levelMap);
        dfsRight(node.left, level + 1, levelMap);
    }

    // --- Left Side View: BFS (level-order, peek first node per level) ---
    public List<Integer> leftSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            // First node in queue at this level = leftmost visible node
            result.add(queue.peekFirst().val);

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        RightLeftViewOfBT obj = new RightLeftViewOfBT();

        // ── TC1: Null root ──────────────────────────────────────────
        System.out.println("TC1 - Null:");
        System.out.println("  Right: " + obj.rightSideView(null)); // []
        System.out.println("  Left : " + obj.leftSideView(null));  // []

        // ── TC2: Single node ───────────────────────────────────────
        //   1
        System.out.println("TC2 - Single Node:");
        TreeNode root2 = new TreeNode(1);
        System.out.println("  Right: " + obj.rightSideView(root2)); // [1]
        System.out.println("  Left : " + obj.leftSideView(root2));  // [1]

        // ── TC3: Left-skewed (only left children) ──────────────────
        //   1
        //  /
        // 2
        //  \
        //   3   <- right view picks this (deepest left-skewed node is visible on right too)
        System.out.println("TC3 - Left Skewed:");
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(3);
        System.out.println("  Right: " + obj.rightSideView(root3)); // [1, 2, 3]
        System.out.println("  Left : " + obj.leftSideView(root3));  // [1, 2, 3]

        // ── TC4: Right-skewed (only right children) ─────────────────
        //   1
        //    \
        //     2
        //      \
        //       3
        System.out.println("TC4 - Right Skewed:");
        TreeNode root4 = new TreeNode(1);
        root4.right = new TreeNode(2);
        root4.right.right = new TreeNode(3);
        System.out.println("  Right: " + obj.rightSideView(root4)); // [1, 2, 3]
        System.out.println("  Left : " + obj.leftSideView(root4));  // [1, 2, 3]

        // ── TC5: Left child deeper than right ─────────────────────
        //      1
        //     / \
        //    2   3
        //   /
        //  4        <- visible from right (no node at level 3 on right side)
        System.out.println("TC5 - Left deeper than right:");
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.right = new TreeNode(3);
        root5.left.left = new TreeNode(4);
        System.out.println("  Right: " + obj.rightSideView(root5)); // [1, 3, 4]
        System.out.println("  Left : " + obj.leftSideView(root5));  // [1, 2, 4]

        // ── TC6: Right child deeper than left ─────────────────────
        //      1
        //     / \
        //    2   3
        //         \
        //          4   <- right view picks this; left view shows 2 at level 2
        System.out.println("TC6 - Right deeper than left:");
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(2);
        root6.right = new TreeNode(3);
        root6.right.right = new TreeNode(4);
        System.out.println("  Right: " + obj.rightSideView(root6)); // [1, 3, 4]
        System.out.println("  Left : " + obj.leftSideView(root6));  // [1, 2, 4]

        // ── TC7: Full binary tree ──────────────────────────────────
        //         1
        //       /   \
        //      2     3
        //     / \   / \
        //    4   5 6   7
        System.out.println("TC7 - Full Binary Tree:");
        TreeNode root7 = new TreeNode(1);
        root7.left = new TreeNode(2);
        root7.right = new TreeNode(3);
        root7.left.left = new TreeNode(4);
        root7.left.right = new TreeNode(5);
        root7.right.left = new TreeNode(6);
        root7.right.right = new TreeNode(7);
        System.out.println("  Right: " + obj.rightSideView(root7)); // [1, 3, 7]
        System.out.println("  Left : " + obj.leftSideView(root7));  // [1, 2, 4]

        // ── TC8: Tricky — left subtree has an extra deep node ──────
        //         1
        //       /   \
        //      2     3
        //     / \
        //    4   5
        //         \
        //          6    <- only node at level 4; visible from both sides
        System.out.println("TC8 - Extra deep left subtree:");
        TreeNode root8 = new TreeNode(1);
        root8.left = new TreeNode(2);
        root8.right = new TreeNode(3);
        root8.left.left = new TreeNode(4);
        root8.left.right = new TreeNode(5);
        root8.left.right.right = new TreeNode(6);
        System.out.println("  Right: " + obj.rightSideView(root8)); // [1, 3, 5, 6]
        System.out.println("  Left : " + obj.leftSideView(root8));  // [1, 2, 4, 6]
    }
}
