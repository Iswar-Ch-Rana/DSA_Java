package a_16_Binary_Search_Tree.Basics;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.List;

/**
 * Floor and Ceil in a BST.
 * <p>
 * Floor(key) = largest node value  <= key.
 * Ceil(key)  = smallest node value >= key.
 * Returns -1 when no floor/ceil exists.
 * <p>
 * Method 1 — Iterative (combined floor + ceil in one pass): O(H) time, O(1) space.
 * Method 2 — Recursive (separate floor / ceil):             O(H) time, O(H) space.
 */
public class FloorAndCeilInBST {

    // ─────────────────────────────────────────────────────────────
    // Method 1: Iterative — floor + ceil in one pass
    // ─────────────────────────────────────────────────────────────

    /*
     * Mental Model:
     *
     *   if node == key → floor = ceil = key, done
     *   if node > key  → ceil  = node (potential ceil),  go left
     *   if node < key  → floor = node (potential floor), go right
     */
    public static List<Integer> floorCeilOfBST(TreeNode root, int key) {
        int floor = -1, ceil = -1;
        TreeNode current = root;

        while (current != null) {
            if (current.val == key) {
                // Exact match: floor and ceil are both the key itself.
                return List.of(key, key);
            } else if (current.val > key) {
                // current is a candidate ceil; go left to find a smaller one.
                ceil = current.val;
                current = current.left;
            } else {
                // current is a candidate floor; go right to find a larger one.
                floor = current.val;
                current = current.right;
            }
        }

        return List.of(floor, ceil);
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: Recursive — separate floor / ceil helpers
    // ─────────────────────────────────────────────────────────────

    /*
     * Mental Model (floor):
     *
     *   if null            → -1 (not found)
     *   if node == target  → exact match
     *   if node > target   → go left  (node too large)
     *   if node < target   → try right subtree; if nothing better found, node is the floor
     *
     * Mental Model (ceil) — mirror of floor:
     *
     *   if null            → -1 (not found)
     *   if node == target  → exact match
     *   if node < target   → go right  (node too small)
     *   if node > target   → try left subtree; if nothing better found, node is the ceil
     */
    static int floor(TreeNode root, int target) {
        if (root == null) return -1;
        if (root.val == target) return root.val;
        if (root.val > target) return floor(root.left, target);

        // root.val < target → root is a floor candidate; check right for a closer one.
        int rightResult = floor(root.right, target);
        return (rightResult == -1) ? root.val : rightResult;
    }

    static int ceil(TreeNode root, int target) {
        if (root == null) return -1;
        if (root.val == target) return root.val;
        if (root.val < target) return ceil(root.right, target);

        // root.val > target → root is a ceil candidate; check left for a closer one.
        int leftResult = ceil(root.left, target);
        return (leftResult == -1) ? root.val : leftResult;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        //       4
        //      / \
        //     2   6
        //    / \ / \
        //   1  3 5  7
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);

        System.out.println("── Iterative (combined) ──");
        System.out.println(floorCeilOfBST(root, 0)); // [-1, 1]   key < min
        System.out.println(floorCeilOfBST(root, 1)); // [1, 1]    exact match
        System.out.println(floorCeilOfBST(root, 4)); // [4, 4]    exact match (root)
        System.out.println(floorCeilOfBST(root, 7)); // [7, 7]    exact match (max)
        System.out.println(floorCeilOfBST(root, 8)); // [7, -1]   key > max

        System.out.println("\n── Recursive (separate) ──");
        System.out.println("floor(0)=" + floor(root, 0) + "  ceil(0)=" + ceil(root, 0)); // -1, 1
        System.out.println("floor(1)=" + floor(root, 1) + "  ceil(1)=" + ceil(root, 1)); // 1,  1
        System.out.println("floor(4)=" + floor(root, 4) + "  ceil(4)=" + ceil(root, 4)); // 4,  4
        System.out.println("floor(8)=" + floor(root, 8) + "  ceil(8)=" + ceil(root, 8)); // 7, -1
    }
}
