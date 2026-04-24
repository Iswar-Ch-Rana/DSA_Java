package a_15_Binary_Tree.Traversals;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOrder {
    public static void main(String[] args) {
        // [1, 4, null, 4, 2]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        List<Integer> list = new ArrayList<>();
//        recursiveInorder(root, list);
        iterativeInorder(root, list);
        System.out.println(list);

        // [1 null 2 3]
        TreeNode root1 = new TreeNode(1);
        root1.right = new TreeNode(2);
        root1.right.left = new TreeNode(3);
        List<Integer> list1 = new ArrayList<>();
//        recursiveInorder(root1, list1);
        iterativeInorder(root1, list1);
        System.out.println(list1);
    }

    public static void recursiveInorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        recursiveInorder(root.left , list);
        list.add(root.val);
        recursiveInorder(root.right, list);
    }

    public static void iterativeInorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> st = new Stack<>();
        TreeNode current = root;

        while (true) {
            if (current != null) {
                st.push(current);
                current = current.left;
            } else {
                if (st.isEmpty())
                    break;
                current = st.pop();
                list.add(current.val);
                current = current.right;
            }
        }
    }
}
