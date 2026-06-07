package a_16_Binary_Search_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

/**
 * Recover BST — exactly two nodes were swapped by mistake.
 * <p>
 * Key insight: inorder of a valid BST is strictly ascending.
 * A swap creates 1 or 2 "violations" (prev.val > current.val).
 * - Adjacent swap  → 1 violation  → first=prev, second=current
 * - Non-adjacent   → 2 violations → first=prev at 1st, second=current at 2nd
 * After finding both, swap their values.
 * <p>
 * Method 1 — Recursive inorder   : O(N) time, O(H) space.
 * Method 2 — Morris inorder      : O(N) time, O(1) space ← preferred.
 */
public class CorrectBSTWithTwoNodesSwapped {

    /*
     * Mental Model:
     *
     *   do inorder traversal, track prev node
     *
     *   on violation (prev.val > current.val):
     *       1st violation → first  = prev,    second = current
     *       2nd violation → second = current  (first stays)
     *
     *   after traversal → swap first.val and second.val
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: Recursive Inorder — O(N) time, O(H) space
    // ─────────────────────────────────────────────────────────────
    void recoverTree(TreeNode root) {
        // TreeNode[]{first, second, prev} — array so primitives stay mutable across calls.
        TreeNode[] state = {null, null, null};
        inorder(root, state);
        // swap the two misplaced node values
        int temp = state[0].val;
        state[0].val = state[1].val;
        state[1].val = temp;
    }

    private void inorder(TreeNode node, TreeNode[] state) {
        if (node == null) return;

        inorder(node.left, state);

        TreeNode prev = state[2];
        TreeNode first = state[0];

        if (prev != null && prev.val > node.val) {
            if (first == null) state[0] = prev;   // 1st violation: record prev as first
            state[1] = node;                       // always update second to current
        }
        state[2] = node;                           // advance prev

        inorder(node.right, state);
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: Morris Inorder — O(N) time, O(1) space
    // Same violation logic, no recursion stack.
    // ─────────────────────────────────────────────────────────────
    void recoverTreeMorris(TreeNode root) {
        TreeNode first = null, second = null, prev = null;
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                // visit current
                if (prev != null && prev.val > current.val) {
                    if (first == null) first = prev;
                    second = current;
                }
                prev = current;
                current = current.right;
            } else {
                // find inorder predecessor
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // Phase 1: create thread, go left
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // Phase 2: remove thread, visit current
                    predecessor.right = null;
                    if (prev != null && prev.val > current.val) {
                        if (first == null) first = prev;
                        second = current;
                    }
                    prev = current;
                    current = current.right;
                }
            }
        }

        // swap misplaced values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        CorrectBSTWithTwoNodesSwapped sol = new CorrectBSTWithTwoNodesSwapped();

        // TC1: Adjacent swap
        //   Before:      After:
        //     1            1
        //    /            /
        //   3            2
        //    \            \
        //     2            3
        // inorder before: [3,2,1] → 1 violation
        // inorder after : [1,2,3]
        TreeNode tc1a = buildTC1(), tc1b = buildTC1();
        System.out.println("TC1 Before: ");
        TreeNode.printTreeWithLines(tc1a);
        sol.recoverTree(tc1a);
        System.out.println("TC1 Method1 After: ");
        TreeNode.printTreeWithLines(tc1a);
        sol.recoverTreeMorris(tc1b);
        System.out.println("TC1 Method2 After: ");
        TreeNode.printTreeWithLines(tc1b);

        // TC2: Non-adjacent swap
        //   Before:      After:
        //     3            2
        //    / \          / \
        //   1   4        1   4
        //      /            /
        //     2            3
        // inorder before: [1,3,2,4] → 1 violation, non-adjacent
        // inorder after : [1,2,3,4]
        TreeNode tc2a = buildTC2(), tc2b = buildTC2();
        System.out.println("TC2 Before: ");
        TreeNode.printTreeWithLines(tc2a);
        sol.recoverTree(tc2a);
        System.out.println("TC2 Method1 After: ");
        TreeNode.printTreeWithLines(tc2a);
        sol.recoverTreeMorris(tc2b);
        System.out.println("TC2 Method2 After: ");
        TreeNode.printTreeWithLines(tc2b);

        // TC3: Root swapped with a leaf
        //   Before:      After:
        //     6            2
        //    / \          / \
        //   2   8        6   8
        //  / \          / \
        // 1   4        1   4
        // inorder before: [1,2,4,6,8] → already valid? No: root=6, but
        // actually swapped 6 and 2: inorder [1,6,4,2,8] → 2 violations
        TreeNode tc3a = buildTC3(), tc3b = buildTC3();
        System.out.println("TC3 Before: ");
        TreeNode.printTreeWithLines(tc3a);
        sol.recoverTree(tc3a);
        System.out.println("TC3 Method1 After: ");
        TreeNode.printTreeWithLines(tc3a);
        sol.recoverTreeMorris(tc3b);
        System.out.println("TC3 Method2 After: ");
        TreeNode.printTreeWithLines(tc3b);

        // TC4: Simple 2-node swap at root level
        //   Before:  After:
        //     3        1
        //    /        /
        //   1        3
        // inorder before: [1,3] → swapped root and left → [3,1]
        TreeNode tc4a = buildTC4(), tc4b = buildTC4();
        System.out.println("TC4 Before: ");
        TreeNode.printTreeWithLines(tc4a);
        sol.recoverTree(tc4a);
        System.out.println("TC4 Method1 After: ");
        TreeNode.printTreeWithLines(tc4a);
        sol.recoverTreeMorris(tc4b);
        System.out.println("TC4 Method2 After: ");
        TreeNode.printTreeWithLines(tc4b);
    }

    // ─── tree builders ───
    private static TreeNode buildTC1() {
        TreeNode r = new TreeNode(1);
        r.left = new TreeNode(3);
        r.left.right = new TreeNode(2);
        return r;
    }

    private static TreeNode buildTC2() {
        TreeNode r = new TreeNode(3);
        r.left = new TreeNode(1);
        r.right = new TreeNode(4);
        r.right.left = new TreeNode(2);
        return r;
    }

    private static TreeNode buildTC3() {
        // valid BST: 2,1,6,4,8 — then swap 2 and 6
        TreeNode r = new TreeNode(6);   // should be 2
        r.left = new TreeNode(2);        // should be 6
        r.right = new TreeNode(8);
        r.left.left = new TreeNode(1);
        r.left.right = new TreeNode(4);
        return r;
    }

    private static TreeNode buildTC4() {
        // valid BST: 1,3 — swap root 1 with left 3
        TreeNode r = new TreeNode(3);   // should be 1
        r.left = new TreeNode(1);        // should be 3
        return r;
    }
}
