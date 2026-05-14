package a_16_Binary_Search_Tree.Medium;

import a_15_Binary_Tree.Custom.TreeNode;

/**
 * Delete a node from a BST.
 * <p>
 * Two approaches:
 * Method 1 — Iterative : find parent of target, rewire, O(1) extra space.
 * Method 2 — Recursive : cleaner, O(H) stack space.
 * <p>
 * Deletion has 3 cases:
 * 1. No left child  → replace with right subtree.
 * 2. No right child → replace with left subtree.
 * 3. Both children  → attach right subtree to the bottom-right of left subtree,
 * return left subtree as new root of this position.
 * <p>
 * Time  : O(H)  — H = height of tree.
 * Space : O(1) iterative / O(H) recursive.
 * <p>
 * Mental Model:
 * <p>
 * Find node (BST search):
 * val < root → go left
 * val > root → go right
 * val == root → DELETE
 * <p>
 * Delete (3 cases):
 * no left  → return right
 * no right → return left
 * both     → attach right to bottom-right of left, return left
 */
public class DeleteNodeInBST {

    // ─────────────────────────────────────────────────────────────
    // Method 1: Iterative
    // ─────────────────────────────────────────────────────────────
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        // If root itself is the target, handle directly.
        if (root.val == key) return handleDeletion(root);

        TreeNode originalRoot = root;

        while (root != null) {
            if (key < root.val) {
                if (root.left != null && root.left.val == key) {
                    root.left = handleDeletion(root.left);
                    break;
                }
                root = root.left;
            } else {
                if (root.right != null && root.right.val == key) {
                    root.right = handleDeletion(root.right);
                    break;
                }
                root = root.right;
            }
        }

        return originalRoot;
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: Recursive (cleaner, O(H) stack)
    // ─────────────────────────────────────────────────────────────
    public TreeNode deleteNodeRecursive(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNodeRecursive(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNodeRecursive(root.right, key);
        } else {
            // Found the node — handle deletion.
            return handleDeletion(root);
        }

        return root;
    }

    // ─────────────────────────────────────────────────────────────
    // Shared helper: rewire the node to be deleted
    // ─────────────────────────────────────────────────────────────
    private TreeNode handleDeletion(TreeNode node) {
        // Case 1: no left child → right subtree takes over.
        if (node.left == null) return node.right;

        // Case 2: no right child → left subtree takes over.
        if (node.right == null) return node.left;

        // Case 3: both children exist.
        // Attach right subtree to the bottom-right of left subtree.
        TreeNode rightSubtree = node.right;
        TreeNode bottomRightOfLeft = findLastRight(node.left);
        bottomRightOfLeft.right = rightSubtree;

        return node.left;
    }

    // Find rightmost node in a subtree (used to attach right subtree).
    private TreeNode findLastRight(TreeNode node) {
        if (node.right == null) return node;
        return findLastRight(node.right);
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        DeleteNodeInBST solution = new DeleteNodeInBST();

        // TC1: Delete an internal node with two children (key = 3)
        //        5
        //       / \
        //      3   6
        //     / \   \
        //    2   4   7
        TreeNode root1 = buildTree();
        System.out.println("TC1 Before:");
        TreeNode.printTreeWithLines(root1);
        System.out.println("TC1 After deleting 3 (Iterative):");
        TreeNode.printTreeWithLines(solution.deleteNode(root1, 3));

        TreeNode root1r = buildTree();
        System.out.println("TC1 After deleting 3 (Recursive):");
        TreeNode.printTreeWithLines(solution.deleteNodeRecursive(root1r, 3));

        // TC2: Delete a key not in the tree (key = 0)
        TreeNode root2 = buildTree();
        System.out.println("TC2 After deleting 0 (not found):");
        TreeNode.printTreeWithLines(solution.deleteNode(root2, 0));

        // TC3: Delete the root (key = 5)
        TreeNode root3 = buildTree();
        System.out.println("TC3 After deleting root 5:");
        TreeNode.printTreeWithLines(solution.deleteNode(root3, 5));

        // TC4: Delete a leaf node (key = 7)
        TreeNode root4 = buildTree();
        System.out.println("TC4 After deleting leaf 7:");
        TreeNode.printTreeWithLines(solution.deleteNode(root4, 7));

        // TC5: Delete node with only right child (key = 6)
        TreeNode root5 = buildTree();
        System.out.println("TC5 After deleting 6 (only right child):");
        TreeNode.printTreeWithLines(solution.deleteNode(root5, 6));
    }

    // Builds the shared test tree: [5, 3, 6, 2, 4, null, 7]
    private static TreeNode buildTree() {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);
        return root;
    }
}
