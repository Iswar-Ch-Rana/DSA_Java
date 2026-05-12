package a_15_Binary_Tree.Construction_Problems;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.HashMap;
import java.util.Map;

import static a_15_Binary_Tree.Custom.TreeNode.printTreeWithLines;

/**
 * Construct Binary Tree from Postorder and Inorder Traversal.
 * <p>
 * Idea:
 * - Postorder gives the root of every subtree (LAST element, traverse right-to-left).
 * - Inorder splits the tree: everything left of root → left subtree, right → right subtree.
 * - Use a HashMap for O(1) inorder index lookup.
 * - Build RIGHT subtree before LEFT (because postorder is left→right→root,
 * so reading right-to-left gives: root→right→left).
 * <p>
 * Time  : O(N)  — each node is processed once.
 * Space : O(N)  — HashMap + recursion stack (O(H)).
 * <p>
 * Key insight: postOrderIndex starts at last index and decrements globally.
 * Pass it as int[] so mutation is visible across stack frames.
 * <p>
 * Contrast with Preorder construction:
 * Preorder  → index starts at 0, increments, builds LEFT before RIGHT.
 * Postorder → index starts at end, decrements, builds RIGHT before LEFT.
 */
public class ConstructBTFromPostorderAndInorder {

    public static void main(String[] args) {
        // Test Case 1: standard tree
        // inorder   = [9, 3, 15, 20, 7]
        // postorder = [9, 15, 7, 20, 3]
        int[] postorder = {9, 15, 7, 20, 3};
        int[] inorder = {9, 3, 15, 20, 7};
        printTreeWithLines(buildTree(postorder, inorder));

        // Test Case 2: simple 3-node tree
        // inorder   = [2, 1, 3]
        // postorder = [2, 3, 1]
        postorder = new int[]{2, 3, 1};
        inorder = new int[]{2, 1, 3};
        printTreeWithLines(buildTree(postorder, inorder));

        // Test Case 3: left-skewed
        // inorder   = [3, 2, 1]
        // postorder = [3, 2, 1]
        postorder = new int[]{3, 2, 1};
        inorder = new int[]{3, 2, 1};
        printTreeWithLines(buildTree(postorder, inorder));

        // Test Case 4: single node
        postorder = new int[]{42};
        inorder = new int[]{42};
        printTreeWithLines(buildTree(postorder, inorder));
    }

    private static TreeNode buildTree(int[] postorder, int[] inorder) {
        if (postorder == null || postorder.length == 0) return null;

        // Build inorder index map for O(1) lookup: value -> index
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        // int[] used as a mutable pointer; starts at end, decrements each call
        int[] postOrderIndex = {postorder.length - 1};

        return buildSubTree(postorder, inorderIndexMap, postOrderIndex, 0, inorder.length - 1);
    }

    /**
     * Recursively builds the subtree for inorder range [start, end].
     *
     * @param postorder       full postorder array
     * @param inorderIndexMap value -> inorder index map
     * @param postOrderIndex  mutable pointer into postorder array (int[1]), moves right-to-left
     * @param start           left boundary in inorder
     * @param end             right boundary in inorder
     */
    /*
     * Mental Model:
     *
     *   postorder[index--] → root of current subtree (start from end)
     *   find root in inorder → left of it = left subtree, right = right subtree
     *   recurse RIGHT first (inorderIdx+1, end), then LEFT (start, inorderIdx-1)
     *
     *   index starts at end, decrements backward (right → left in postorder)
     *   ← only diff vs Preorder: index direction + right before left
     */
    private static TreeNode buildSubTree(
            int[] postorder,
            Map<Integer, Integer> inorderIndexMap,
            int[] postOrderIndex,
            int start,
            int end) {

        if (start > end || postOrderIndex[0] < 0) return null;

        // Last unprocessed postorder element is the root of this subtree
        int rootVal = postorder[postOrderIndex[0]--];
        TreeNode root = new TreeNode(rootVal);

        // Find root's position in inorder to split left and right subtrees
        int inorderIdx = inorderIndexMap.get(rootVal);

        // RIGHT must be built before LEFT (postorder reversed = root→right→left)
        root.right = buildSubTree(postorder, inorderIndexMap, postOrderIndex, inorderIdx + 1, end);
        root.left = buildSubTree(postorder, inorderIndexMap, postOrderIndex, start, inorderIdx - 1);

        return root;
    }
}
