package a_15_Binary_Tree.Traversals;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PostorderTraversal {
    public static void main(String[] args) {
        // [1, 4, null, 4, 2]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        List<Integer> list = new ArrayList<>();
        recursivePostorder(root, list);
        System.out.println(list);
    }

    public static void recursivePostorder(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }

        recursivePostorder(root.left, list);
        recursivePostorder(root.right, list);
        list.add(root.val);
    }
}
