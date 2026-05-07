package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayList;
import java.util.List;

/// ### Boundary Traversal (Anti-Clockwise)
///
/// **Logic:**
/// 1. **Root**: Add if not a leaf.
/// 2. **Left Boundary**: Top -> Down (no leaves).
/// 3. **Leaves**: Left -> Right.
/// 4. **Right Boundary**: Down -> Top (no leaves).
///
/// **Complexity:**
/// - **Time**: O(N) — One pass through relevant nodes.
/// - **Space**: O(H) — Height of tree for recursion.
public class BoundaryTraversal {
    public static void main(String[] args) {
        // Test Case 1: Standard Tree
        //      1
        //     / \
        //    2   3
        //   / \ / \
        //  4  5 6  7
        //    / \
        //   8   9
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        root1.right.left = new TreeNode(6);
        root1.right.right = new TreeNode(7);
        root1.left.right.left = new TreeNode(8);
        root1.left.right.right = new TreeNode(9);

        System.out.println("--- Test Case 1 (Standard) ---");
        TreeNode.printTreeWithLines(root1);
        System.out.println("Boundary: " + boundary(root1));
        System.out.println();

        // Test Case 2: Single Node
        TreeNode root2 = new TreeNode(10);
        System.out.println("--- Test Case 2 (Single Node) ---");
        TreeNode.printTreeWithLines(root2);
        System.out.println("Boundary: " + boundary(root2));
        System.out.println();

        // Test Case 3: Left Skewed
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(3);
        System.out.println("--- Test Case 3 (Left Skewed) ---");
        TreeNode.printTreeWithLines(root3);
        System.out.println("Boundary: " + boundary(root3));
        System.out.println();

        // Test Case 4: Right Skewed
        TreeNode root4 = new TreeNode(1);
        root4.right = new TreeNode(2);
        root4.right.right = new TreeNode(3);
        System.out.println("--- Test Case 4 (Right Skewed) ---");
        TreeNode.printTreeWithLines(root4);
        System.out.println("Boundary: " + boundary(root4));
        System.out.println();
    }

    public static List<Integer> boundary(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        // 1. Add Root if not leaf
        if (!isLeaf(root)) ans.add(root.val);

        // 2. Add Left Boundary (Top-Down, excluding leaves)
        addLeftBoundary(root.left, ans);

        // 3. Add all Leaves (Left-Right)
        addLeaves(root, ans);

        // 4. Add Right Boundary (Bottom-Up, excluding leaves)
        List<Integer> temp = new ArrayList<>();
        addRightBoundary(root.right, temp);
        for (int i = temp.size() - 1; i >= 0; i--) {
            ans.add(temp.get(i));
        }

        return ans;
    }

    private static void addLeftBoundary(TreeNode root, List<Integer> ans) {
        if (root == null || isLeaf(root)) return;
        ans.add(root.val);
        if (root.left != null) {
            addLeftBoundary(root.left, ans);
        } else {
            addLeftBoundary(root.right, ans);
        }
    }

    private static void addLeaves(TreeNode root, List<Integer> ans) {
        if (root == null) return;
        if (isLeaf(root)) {
            ans.add(root.val);
            return;
        }
        addLeaves(root.left, ans);
        addLeaves(root.right, ans);
    }

    private static void addRightBoundary(TreeNode root, List<Integer> temp) {
        if (root == null || isLeaf(root)) return;
        temp.add(root.val);
        if (root.right != null) {
            addRightBoundary(root.right, temp);
        } else {
            addRightBoundary(root.left, temp);
        }
    }

    private static boolean isLeaf(TreeNode root) {
        return (root.left == null) && (root.right == null);
    }
}
