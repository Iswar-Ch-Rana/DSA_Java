package a_15_Binary_Tree.Binary_Search_Tree;

import a_15_Binary_Tree.Custom.TreeNode;

public class SearchInBST {
    public static void main(String[] args) {
        //  root = [4, 2, 7, 1, 3] , val = 2
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(7);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        // root = [4, 2, 7, 1, 3] , val = 5
        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(7);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(3);

        // root = [10, 2, 12, 1, 4, null, null, null, null, 3] , val = 2
        TreeNode root3 = new TreeNode(10);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(12);
        root3.left.left = new TreeNode(1);
        root3.left.right = new TreeNode(4);
        root3.left.right.left = new TreeNode(3);

        SearchInBST search = new SearchInBST();
        System.out.println(search.searchBST(root, 2)); // 2
        System.out.println(search.searchBST(root2, 5)); // null
        System.out.println(search.searchBST(root3, 2)); // 2
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val) return root;

        if (val < root.val) {
            return searchBST(root.left, val);
        } else {
            return searchBST(root.right, val);
        }
    }
}
