package a_16_Binary_Search_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// ─────────────────────────────────────────────────────────────────────────────
// Brute Force: build full inorder list upfront, walk with an index pointer.
//
// Mental Model:
//   Constructor: inorder DFS → fill list
//   next()     : return list[index++]
//   hasNext()  : index < list.size()
//
// Time  : O(N) constructor, O(1) next/hasNext
// Space : O(N) — entire tree stored in list
// ─────────────────────────────────────────────────────────────────────────────
class BSTIteratorBrute {
    private final List<Integer> inorder = new ArrayList<>();
    private int index = 0;

    BSTIteratorBrute(TreeNode root) {
        buildInorder(root);
    }

    public int next() {
        return inorder.get(index++);
    }

    public boolean hasNext() {
        return index < inorder.size();
    }

    private void buildInorder(TreeNode node) {
        if (node == null) return;
        buildInorder(node.left);
        inorder.add(node.val);
        buildInorder(node.right);
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Optimal: lazy inorder using a stack — only push what's needed.
//
// Mental Model:
//   Constructor: push leftmost path (root → left → left → ...)
//   next()     : pop top → that's next smallest
//                go right → push leftmost path of right subtree
//   hasNext()  : stack non-empty
//
// Time  : O(1) amortised per next() — each node pushed/popped exactly once.
// Space : O(H) — stack holds at most one root-to-leaf path at a time.
// ─────────────────────────────────────────────────────────────────────────────
class BSTIterator {
    private final Deque<TreeNode> stack = new ArrayDeque<>();

    BSTIterator(TreeNode root) {
        pushLeftPath(root);
    }

    public int next() {
        TreeNode node = stack.pop();      // next smallest = top of stack
        pushLeftPath(node.right);         // prepare right subtree's leftmost path
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    private void pushLeftPath(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}

public class BSTIteratorMain {
    public static void main(String[] args) {
        // TC1: Standard BST
        //       7
        //      / \
        //     3   15
        //        /  \
        //       9   20
        // inorder: [3, 7, 9, 15, 20]
        TreeNode root1 = new TreeNode(7);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(15);
        root1.right.left = new TreeNode(9);
        root1.right.right = new TreeNode(20);

        System.out.print("TC1 Brute  : ");
        printAll(new BSTIteratorBrute(root1));
        System.out.print("TC1 Optimal: ");
        printAll(new BSTIterator(root1));    // 3 7 9 15 20

        // TC2: Single node
        System.out.print("TC2 Brute  : ");
        printAll(new BSTIteratorBrute(new TreeNode(42)));
        System.out.print("TC2 Optimal: ");
        printAll(new BSTIterator(new TreeNode(42)));         // 42

        // TC3: Left-skewed
        //   3
        //  /
        // 2
        // /
        // 1
        TreeNode root3 = new TreeNode(3);
        root3.left = new TreeNode(2);
        root3.left.left = new TreeNode(1);

        System.out.print("TC3 Brute  : ");
        printAll(new BSTIteratorBrute(root3));
        System.out.print("TC3 Optimal: ");
        printAll(new BSTIterator(root3));    // 1 2 3

        // TC4: Right-skewed
        TreeNode root4 = new TreeNode(1);
        root4.right = new TreeNode(2);
        root4.right.right = new TreeNode(3);

        System.out.print("TC4 Brute  : ");
        printAll(new BSTIteratorBrute(root4));
        System.out.print("TC4 Optimal: ");
        printAll(new BSTIterator(root4));    // 1 2 3

        // TC5: null root
        System.out.print("TC5 Brute  : ");
        printAll(new BSTIteratorBrute(null));
        System.out.print("TC5 Optimal: ");
        printAll(new BSTIterator(null));     // (empty)
    }

    // shared print helper
    static void printAll(BSTIteratorBrute it) {
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();
    }

    static void printAll(BSTIterator it) {
        while (it.hasNext()) System.out.print(it.next() + " ");
        System.out.println();
    }
}
