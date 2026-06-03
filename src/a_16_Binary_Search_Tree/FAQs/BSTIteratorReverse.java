package a_16_Binary_Search_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

// ─────────────────────────────────────────────────────────────────────────────
// BST Iterator Reverse — lazy reverse-inorder (R → root → L) using a stack.
//
// Mental Model:
//   Constructor: push rightmost path (root → right → right → ...)
//   next()     : pop top → that's the next largest
//                go left → push rightmost path of left subtree
//   hasNext()  : stack non-empty
//
// Mirror of BSTIterator:
//   Forward  → push leftmost path,  go right on pop
//   Backward → push rightmost path, go left  on pop  ← only difference
//
// Time  : O(1) amortised per next()
// Space : O(H)
// ─────────────────────────────────────────────────────────────────────────────
public class BSTIteratorReverse {

    private final Deque<TreeNode> stack = new ArrayDeque<>();

    BSTIteratorReverse(TreeNode root) {
        pushRightPath(root);
    }

    int next() {
        TreeNode node = stack.pop();      // next largest = top of stack
        pushRightPath(node.left);         // prepare left subtree's rightmost path
        return node.val;
    }

    boolean hasNext() {
        return !stack.isEmpty();
    }

    // Push all nodes along the rightmost path starting from 'node'.
    private void pushRightPath(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.right;
        }
    }

    public static void main(String[] args) {
        // TC1: Standard BST
        //       7
        //      / \
        //     3   15
        //        /  \
        //       9   20
        // reverse-inorder: [20, 15, 9, 7, 3]
        TreeNode root1 = new TreeNode(7);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(15);
        root1.right.left = new TreeNode(9);
        root1.right.right = new TreeNode(20);
        System.out.print("TC1: ");
        printAll(new BSTIteratorReverse(root1)); // 20 15 9 7 3

        // TC2: Single node
        System.out.print("TC2: ");
        printAll(new BSTIteratorReverse(new TreeNode(42))); // 42

        // TC3: Left-skewed  → reverse-inorder: [3, 2, 1]
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(1);
        System.out.print("TC3: ");
        printAll(new BSTIteratorReverse(root3)); // 3 2 1

        // TC4: Right-skewed → reverse-inorder: [3, 2, 1]
        TreeNode root4 = new TreeNode(1);
        root4.right = new TreeNode(2);
        root4.right.right = new TreeNode(3);
        System.out.print("TC4: ");
        printAll(new BSTIteratorReverse(root4)); // 3 2 1

        // TC5: null root
        System.out.print("TC5: ");
        printAll(new BSTIteratorReverse(null));  // (empty)
    }

    static void printAll(BSTIteratorReverse it) {
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();
    }
}
