package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayList;
import java.util.List;

/// ### Root to Leaf Paths in Binary Tree
///
/// **Logic:**
/// 1. Use **DFS (Recursion)** to traverse from root to leaves.
/// 2. Maintain a `path` list to store nodes of the current branch.
/// 3. **Backtracking:** After exploring both left and right subtrees of a node,
///    remove the node from `path` to explore other branches correctly.
/// 4. When a **leaf node** is reached (left and right are null), add a copy of `path` to `ans`.
///
/// **Complexity Analysis:**
/// - **Time**: O(N) — Each node is visited once.
/// - **Space**: O(H) — Recursion stack depth equal to height of tree.
public class PrintRootToLeafPathInBT {
    public static void main(String[] args) {
        // Test Case 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(4);

        System.out.println("--- Test Case 1 ---");
        System.out.println("Paths: " + allRootToLeaf(root1));
        System.out.println();

        // Test Case 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.left.right = new TreeNode(5);

        System.out.println("--- Test Case 2 ---");
        System.out.println("Paths: " + allRootToLeaf(root2));
        System.out.println();

        // Test Case 3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.left = new TreeNode(4);
        root3.right.left = new TreeNode(5);
        root3.right.right = new TreeNode(6);
        root3.right.right.left = new TreeNode(7);

        System.out.println("--- Test Case 3 ---");
        System.out.println("Paths: " + allRootToLeaf(root3));
        System.out.println();
    }

    public static List<List<Integer>> allRootToLeaf(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;

        solve(root, new ArrayList<>(), ans);
        return ans;
    }

    private static void solve(TreeNode node, List<Integer> path, List<List<Integer>> ans) {
        if (node == null) return;

        // 1. Action: Add current node to path
        path.add(node.val);

        // 2. Condition: If leaf node, record the path
        if (node.left == null && node.right == null) {
            ans.add(new ArrayList<>(path));
        } else {
            // 3. Recurse: Explore children
            solve(node.left, path, ans);
            solve(node.right, path, ans);
        }

        // 4. Backtrack: Remove current node before returning to parent
        path.remove(path.size() - 1);
    }
}
