package a_16_Binary_Search_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Two Sum in a BST — find if any two nodes sum to target.
 * <p>
 * Brute  : inorder → sorted list → two pointers.  O(N) time, O(N) space.
 * Optimal: BST forward + reverse iterators as two pointers. O(N) time, O(H) space.
 */
public class TwoSumInBST {

    // ─────────────────────────────────────────────────────────────
    // Brute: inorder → sorted list → two pointers
    //
    // Mental Model:
    //   inorder(BST) = sorted array
    //   left pointer from start, right from end
    //   sum < target → left++,  sum > target → right--,  else → true
    // ─────────────────────────────────────────────────────────────
    boolean twoSumBrute(TreeNode root, int target) {
        List<Integer> inorder = new ArrayList<>();
        buildInorder(root, inorder);

        int left = 0, right = inorder.size() - 1;
        while (left < right) {
            int sum = inorder.get(left) + inorder.get(right);
            if (sum < target) left++;
            else if (sum > target) right--;
            else return true;
        }
        return false;
    }

    private void buildInorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        buildInorder(node.left, list);
        list.add(node.val);
        buildInorder(node.right, list);
    }

    // ─────────────────────────────────────────────────────────────
    // Optimal: forward + reverse BST iterators as two pointers
    //
    // Mental Model:
    //   forward  iterator → next smallest  (inorder)
    //   backward iterator → next largest   (reverse-inorder)
    //   same two-pointer logic as sorted array, but O(H) space
    // ─────────────────────────────────────────────────────────────
    boolean twoSumOptimal(TreeNode root, int target) {
        BSTIterator forward = new BSTIterator(root);
        BSTIteratorReverse backward = new BSTIteratorReverse(root);

        int left = forward.next();
        int right = backward.next();

        while (left < right) {
            int sum = left + right;
            if (sum < target) left = forward.next();
            else if (sum > target) right = backward.next();
            else return true;
        }
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        TwoSumInBST sol = new TwoSumInBST();

        // TC1: Standard BST, pair exists
        //       5
        //      / \
        //     3   6
        //    / \   \
        //   2   4   7
        // inorder: [2, 3, 4, 5, 6, 7], target=9 → (2,7) or (3,6) → true
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(6);
        root1.left.left = new TreeNode(2);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(7);
        System.out.println("TC1 target=9  Brute:" + sol.twoSumBrute(root1, 9) + " Optimal:" + sol.twoSumOptimal(root1, 9));   // true

        // TC2: Same tree, pair doesn't exist
        System.out.println("TC2 target=28 Brute:" + sol.twoSumBrute(root1, 28) + " Optimal:" + sol.twoSumOptimal(root1, 28));  // false

        // TC3: Exact sum = two same-valued nodes doesn't exist → false
        System.out.println("TC3 target=10 Brute:" + sol.twoSumBrute(root1, 10) + " Optimal:" + sol.twoSumOptimal(root1, 10));  // false (no 5+5)

        // TC4: Single node, target = node*2 → false (can't use same node twice)
        TreeNode root4 = new TreeNode(1);
        System.out.println("TC4 target=2  Brute:" + sol.twoSumBrute(root4, 2) + " Optimal:" + sol.twoSumOptimal(root4, 2));   // false

        // TC5: Two nodes, target matches
        TreeNode root5 = new TreeNode(1);
        root5.right = new TreeNode(2);
        System.out.println("TC5 target=3  Brute:" + sol.twoSumBrute(root5, 3) + " Optimal:" + sol.twoSumOptimal(root5, 3));   // true
    }
}
