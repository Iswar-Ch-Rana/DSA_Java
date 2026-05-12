package a_16_Binary_Search_Tree.Basics;

import a_15_Binary_Tree.Custom.TreeNode;

/**
 * Search in a Binary Search Tree.
 * <p>
 * BST property: left subtree &lt; root &lt; right subtree.
 * Use it to eliminate half the tree at each step — like binary search.
 * <p>
 * Method 1 (Recursive) : cleaner code, O(H) stack space.
 * Method 2 (Iterative) : O(1) space, preferred for very deep trees.
 * <p>
 * Time  : O(H) — O(log N) balanced, O(N) skewed.
 * Space : O(H) recursive / O(1) iterative.
 */
public class SearchInBST {

    /*
     * Mental Model:
     *
     *   if root is null or matches → return root
     *
     *   if val < root  → go left
     *   if val > root  → go right
     */
    // Method 1: Recursive
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;

        // BST property — eliminate half the tree each step.
        return val < root.val
                ? searchBST(root.left, val)
                : searchBST(root.right, val);
    }

    // Method 2: Iterative (O(1) space)
    public TreeNode searchBSTIterative(TreeNode root, int val) {
        TreeNode current = root;

        while (current != null) {
            if (current.val == val)
                return current;
            if (val < current.val)
                current = current.left;
            else
                current = current.right;
        }

        return null; // not found
    }

    public static void main(String[] args) {
        SearchInBST search = new SearchInBST();

        // TC1: val exists — root = [4, 2, 7, 1, 3], val = 2  → expected: node(2)
        //       4
        //      / \
        //     2   7
        //    / \
        //   1   3
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(7);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(3);
        System.out.println("TC1 Recursive : " + search.searchBST(root1, 2));          // 2
        System.out.println("TC1 Iterative : " + search.searchBSTIterative(root1, 2)); // 2

        // TC2: val not present — same tree, val = 5  → expected: null
        System.out.println("TC2 Recursive : " + search.searchBST(root1, 5));          // null
        System.out.println("TC2 Iterative : " + search.searchBSTIterative(root1, 5)); // null

        // TC3: val in deeper subtree — root = [10, 2, 12, 1, 4(left=3)], val = 2  → expected: node(2)
        //         10
        //        /  \
        //       2   12
        //      / \
        //     1   4
        //        /
        //       3
        TreeNode root3 = new TreeNode(10);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(12);
        root3.left.left = new TreeNode(1);
        root3.left.right = new TreeNode(4);
        root3.left.right.left = new TreeNode(3);
        System.out.println("TC3 Recursive : " + search.searchBST(root3, 2));          // 2
        System.out.println("TC3 Iterative : " + search.searchBSTIterative(root3, 2)); // 2

        // TC4: null root  → expected: null
        System.out.println("TC4 Recursive : " + search.searchBST(null, 1));           // null
        System.out.println("TC4 Iterative : " + search.searchBSTIterative(null, 1));  // null

        // TC5: single node, val matches  → expected: node(42)
        System.out.println("TC5 Recursive : " + search.searchBST(new TreeNode(42), 42));          // 42
        System.out.println("TC5 Iterative : " + search.searchBSTIterative(new TreeNode(42), 42)); // 42
    }
}
