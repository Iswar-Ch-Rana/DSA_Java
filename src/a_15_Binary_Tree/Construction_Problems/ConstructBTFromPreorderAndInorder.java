package a_15_Binary_Tree.Construction_Problems;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.HashMap;
import java.util.Map;

import static a_15_Binary_Tree.Custom.TreeNode.printTreeWithLines;

/**
 * Construct Binary Tree from Preorder and Inorder Traversal.
 * <p>
 * Idea:
 * - Preorder gives the root of every subtree (first element).
 * - Inorder splits the tree: everything left of root → left subtree, right → right subtree.
 * - Use a HashMap for O(1) inorder index lookup instead of O(N) linear scan.
 * <p>
 * Time  : O(N)  — each node is processed once.
 * Space : O(N)  — HashMap + recursion stack (O(H)).
 * <p>
 * Key insight: preOrderIndex advances globally across all recursive calls.
 * Pass it as int[] so mutation is visible across stack frames.
 */
public class ConstructBTFromPreorderAndInorder {

    public static void main(String[] args) {
        // Test Case 1: standard tree
        // preorder = [3, 9, 20, 15, 7], inorder = [9, 3, 15, 20, 7]
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        printTreeWithLines(buildTree(preorder, inorder));

        // Test Case 2: simple 3-node tree
        // preorder = [1, 2, 3], inorder = [2, 1, 3]
        preorder = new int[]{1, 2, 3};
        inorder = new int[]{2, 1, 3};
        printTreeWithLines(buildTree(preorder, inorder));

        // Test Case 3: left-skewed
        // preorder = [1, 2, 3], inorder = [3, 2, 1]
        preorder = new int[]{1, 2, 3};
        inorder = new int[]{3, 2, 1};
        printTreeWithLines(buildTree(preorder, inorder));

        // Test Case 4: single node
        preorder = new int[]{42};
        inorder = new int[]{42};
        printTreeWithLines(buildTree(preorder, inorder));
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) return null;

        // Build inorder index map for O(1) lookup: value -> index
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        // int[] used as a mutable pointer so preOrderIndex advances across all recursive calls
        int[] preOrderIndex = {0};

        return buildSubTree(preorder, inorderIndexMap, preOrderIndex, 0, inorder.length - 1);
    }

    /**
     * Recursively builds the subtree for inorder range [start, end].
     *
     * @param preorder        full preorder array
     * @param inorderIndexMap value -> inorder index map
     * @param preOrderIndex   mutable pointer into preorder array (int[1])
     * @param start           left boundary in inorder
     * @param end             right boundary in inorder
     */
    private static TreeNode buildSubTree(
            int[] preorder,
            Map<Integer, Integer> inorderIndexMap,
            int[] preOrderIndex,
            int start,
            int end) {

        if (start > end || preOrderIndex[0] >= preorder.length) return null;

        // Next preorder element is the root of this subtree
        int rootVal = preorder[preOrderIndex[0]++];
        TreeNode root = new TreeNode(rootVal);

        // Find root's position in inorder to split left and right subtrees
        int inorderIdx = inorderIndexMap.get(rootVal);

        root.left = buildSubTree(preorder, inorderIndexMap, preOrderIndex, start, inorderIdx - 1);
        root.right = buildSubTree(preorder, inorderIndexMap, preOrderIndex, inorderIdx + 1, end);

        return root;
    }
}
