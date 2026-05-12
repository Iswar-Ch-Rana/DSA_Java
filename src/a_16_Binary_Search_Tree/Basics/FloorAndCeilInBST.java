package a_16_Binary_Search_Tree.Basics;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.List;

/**
 * Floor and Ceil in a BST.
 * <p>
 * Floor(key) = largest node value <= key.
 * Ceil(key)  = smallest node value >= key.
 * Returns -1 when no floor/ceil exists.
 * <p>
 * Time  : O(H) — O(log N) balanced, O(N) skewed.
 * Space : O(1) — iterative, no extra space.
 */
public class FloorAndCeilInBST {

    /*
     * Mental Model:
     *
     *   if node == key → floor = ceil = key, done
     *   if node > key  → ceil = node (potential ceil), go left
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
                // current is a candidate ceil; go left to find smaller.
                ceil = current.val;
                current = current.left;
            } else {
                // current is a candidate floor; go right to find larger.
                floor = current.val;
                current = current.right;
            }
        }

        return List.of(floor, ceil);
    }

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

        System.out.println(floorCeilOfBST(root, 0)); // [-1, 1]   key < min
        System.out.println(floorCeilOfBST(root, 1)); // [1, 1]    exact match
        System.out.println(floorCeilOfBST(root, 2)); // [2, 2]    exact match
        System.out.println(floorCeilOfBST(root, 3)); // [3, 3]    exact match
        System.out.println(floorCeilOfBST(root, 4)); // [4, 4]    exact match (root)
        System.out.println(floorCeilOfBST(root, 5)); // [5, 5]    exact match
        System.out.println(floorCeilOfBST(root, 6)); // [6, 6]    exact match
        System.out.println(floorCeilOfBST(root, 7)); // [7, 7]    exact match
        System.out.println(floorCeilOfBST(root, 8)); // [7, -1]   key > max
    }
}
