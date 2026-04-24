package a_15_Binary_Tree.Traversals;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PrePostInorderInOneTraversal {
    public static void main(String[] args) {
        // [1, 4, null, 4, 2]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(2);
        System.out.println(treeTraversal(root));
    }

    public static List<List<Integer>> treeTraversal(TreeNode root) {
        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        dfs(root, preorder, inorder, postorder);

        List<List<Integer>> result = new ArrayList<>();
        result.add(inorder);
        result.add(preorder);
        result.add(postorder);

        return result;
    }

    private static void dfs(TreeNode node,
                            List<Integer> preorder,
                            List<Integer> inorder,
                            List<Integer> postorder) {

        if (node == null) return;

        // Preorder
        preorder.add(node.val);

        dfs(node.left, preorder, inorder, postorder);

        // Inorder
        inorder.add(node.val);

        dfs(node.right, preorder, inorder, postorder);

        // Postorder
        postorder.add(node.val);
    }
}
