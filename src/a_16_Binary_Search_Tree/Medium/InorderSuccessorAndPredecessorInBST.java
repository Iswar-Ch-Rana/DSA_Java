package a_16_Binary_Search_Tree.Medium;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Inorder Predecessor and Successor in a BST.
 * <p>
 * Predecessor = largest node smaller than key  (left subtree's rightmost, or last left-turn ancestor).
 * Successor   = smallest node larger than key  (right subtree's leftmost, or last right-turn ancestor).
 * Returns -1 when predecessor/successor does not exist.
 * <p>
 * Method 1 (Brute)   : Build inorder list → find key → peek index±1.  Time O(N), Space O(N).
 * Method 2 (Optimal) : BST property walk, no extra space.              Time O(H), Space O(1).
 */
public class InorderSuccessorAndPredecessorInBST {

    /*
     * Mental Model (Optimal — BST walk):
     *
     *   key < node → node is a successor candidate, go left
     *   key > node → node is a predecessor candidate, go right
     *   key == node:
     *       predecessor = rightmost node of left subtree
     *       successor   = leftmost  node of right subtree
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1 (Brute): inorder list → index lookup
    // Time O(N), Space O(N)
    // ─────────────────────────────────────────────────────────────
    private static int[] findBrute(TreeNode root, int key) {
        List<Integer> inorderList = new ArrayList<>();
        buildInorder(root, inorderList);

        int[] result = {-1, -1}; // {predecessor, successor}
        int index = inorderList.indexOf(key);

        if (index == -1) return result;

        if (index - 1 >= 0) result[0] = inorderList.get(index - 1); // predecessor
        if (index + 1 < inorderList.size()) result[1] = inorderList.get(index + 1); // successor

        return result;
    }

    private static void buildInorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        buildInorder(node.left, list);
        list.add(node.val);
        buildInorder(node.right, list);
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2 (Optimal): BST property walk — O(H), O(1)
    // ─────────────────────────────────────────────────────────────
    private static int[] findOptimal(TreeNode root, int key) {
        int[] result = {-1, -1}; // {predecessor, successor}
        TreeNode current = root;

        while (current != null) {
            if (key < current.val) {
                // current could be the successor (going left narrows it down).
                result[1] = current.val;
                current = current.left;
            } else if (key > current.val) {
                // current could be the predecessor (going right narrows it down).
                result[0] = current.val;
                current = current.right;
            } else {
                // Exact match: predecessor = rightmost of left, successor = leftmost of right.
                if (current.left != null) result[0] = rightmost(current.left);
                if (current.right != null) result[1] = leftmost(current.right);
                break;
            }
        }

        return result;
    }

    // Rightmost node of a subtree = largest value = inorder predecessor.
    private static int rightmost(TreeNode node) {
        while (node.right != null) node = node.right;
        return node.val;
    }

    // Leftmost node of a subtree = smallest value = inorder successor.
    private static int leftmost(TreeNode node) {
        while (node.left != null) node = node.left;
        return node.val;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        //         5
        //        / \
        //       2   10
        //      / \ /  \
        //     1  4 7   12
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(12);

        // TC1: key = 10, has both left (7) and right (12) subtree
        // pred = 7, succ = 12
        print("TC1 key=10", root, 10);

        // TC2: key = 12 (max node) — no successor
        // pred = 10, succ = -1
        print("TC2 key=12", root, 12);

        // TC3: key = 1 (min node) — no predecessor
        // pred = -1, succ = 2
        print("TC3 key=1 ", root, 1);

        // TC4: key = 5 (root) — predecessor via right of left subtree, successor via left of right subtree
        // pred = 4, succ = 7
        print("TC4 key=5 ", root, 5);

        // TC5: key = 7 — no left child, successor determined by ancestor last-right-turn
        // pred = 5, succ = 10
        print("TC5 key=7 ", root, 7);
    }

    private static void print(String label, TreeNode root, int key) {
        int[] brute = findBrute(root, key);
        int[] optimal = findOptimal(root, key);
        System.out.printf("%s → Brute[pred=%d, succ=%d]  Optimal[pred=%d, succ=%d]%n",
                label, brute[0], brute[1], optimal[0], optimal[1]);
    }
}
