package a_15_Binary_Tree.Construction_Problems;

public class RequirementsNeededToConstructAUniqueBT {
    public static void main(String[] args) {
        /*
         * To construct a unique binary tree, we need the following information:
         *
         * 1. Preorder Traversal: This traversal visits the root node first, then
         * recursively visits the left subtree and finally the right subtree. It helps
         * in identifying the root node of the tree.
         *
         * 2. Inorder Traversal: This traversal visits the left subtree first, then
         * visits the root node, and finally visits the right subtree. It helps in
         * determining the structure of the tree and how the nodes are connected.
         *
         * With both preorder and inorder traversals, we can uniquely reconstruct the
         * binary tree. The preorder traversal gives us the order of nodes, while the
         * inorder traversal provides information about the left and right subtrees,
         * allowing us to build the tree accurately.
         */

        RequirementsNeededToConstructAUniqueBT obj = new RequirementsNeededToConstructAUniqueBT();
        int a = 1, b = 2;
        System.out.println(obj.uniqueBinaryTree(a, b)); // Output: true

        a = 1;
        b = 3;
        System.out.println(obj.uniqueBinaryTree(a, b)); // Output: false

        a = 3;
        b = 1;
        System.out.println(obj.uniqueBinaryTree(a, b)); // Output: false

    }

    public boolean uniqueBinaryTree(int a, int b) {
        return !(a == b || (a == 1 && b == 3) || (a == 3 && b == 1));
    }
}
