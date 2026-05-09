package a_15_Binary_Tree.Construction_Problems;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialize and Deserialize a Binary Tree (BFS / Level-order).
 * <p>
 * Serialize : BFS; write node values and "n" for nulls into a space-separated string.
 * Deserialize: Rebuild level-by-level — each dequeued parent consumes the next two tokens
 * (left child, right child) from the token array.
 * <p>
 * Time  : O(N) for both serialize and deserialize.
 * Space : O(N) for queue + output string.
 * <p>
 * Key insight: null markers ("n") in the serialized string are what allow
 * exact reconstruction — without them, tree shape is ambiguous.
 */
public class SerializeAndDeSerializeBT {

    private static final String NULL_MARKER = "n";
    private static final String DELIMITER = " ";

    // ─────────────────────────────────────────────────────────────
    // Serialize: BFS → space-separated string
    // ─────────────────────────────────────────────────────────────
    public String serialize(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        // LinkedList is used here (not ArrayDeque) because it allows null elements.
        // We enqueue null children so their "n" marker is written when dequeued,
        // keeping the token stream perfectly aligned for deserialization.
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current == null) {
                // Null node → write marker; do not enqueue further children.
                sb.append(NULL_MARKER).append(DELIMITER);
                continue;
            }

            sb.append(current.val).append(DELIMITER);
            // Enqueue both children (even if null) to preserve tree shape.
            queue.add(current.left);
            queue.add(current.right);
        }

        return sb.toString().trim();
    }

    // ─────────────────────────────────────────────────────────────
    // Deserialize: token array → tree (BFS, consuming 2 tokens per parent)
    // ─────────────────────────────────────────────────────────────
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;

        String[] tokens = data.split(DELIMITER);
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // i always points to the left-child token of the current parent.
        for (int i = 1; i < tokens.length; i++) {
            TreeNode parent = queue.poll();

            // Left child
            if (!tokens[i].equals(NULL_MARKER)) {
                parent.left = new TreeNode(Integer.parseInt(tokens[i]));
                queue.add(parent.left);
            }

            // Right child (next token)
            i++;
            if (i < tokens.length && !tokens[i].equals(NULL_MARKER)) {
                parent.right = new TreeNode(Integer.parseInt(tokens[i]));
                queue.add(parent.right);
            }
        }

        return root;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        SerializeAndDeSerializeBT codec = new SerializeAndDeSerializeBT();

        // Test Case 1: Standard tree
        //       1
        //      / \
        //     2   3
        //        / \
        //       4   5
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.right.left = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        String s1 = codec.serialize(root1);
        System.out.println("TC1 Serialized  : " + s1);
        System.out.println("TC1 Deserialized:");
        TreeNode.printTreeWithLines(codec.deserialize(s1));

        // Test Case 2: Single node
        TreeNode root2 = new TreeNode(42);
        String s2 = codec.serialize(root2);
        System.out.println("TC2 Serialized  : " + s2);
        System.out.println("TC2 Deserialized:");
        TreeNode.printTreeWithLines(codec.deserialize(s2));

        // Test Case 3: Left-skewed
        //   1
        //  /
        // 2
        //  \
        //   3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.left.right = new TreeNode(3);
        String s3 = codec.serialize(root3);
        System.out.println("TC3 Serialized  : " + s3);
        System.out.println("TC3 Deserialized:");
        TreeNode.printTreeWithLines(codec.deserialize(s3));

        // Test Case 4: null root
        String s4 = codec.serialize(null);
        System.out.println("TC4 Serialized  : '" + s4 + "'");
        System.out.println("TC4 Deserialized: " + codec.deserialize(s4)); // null

        // Test Case 5: Full binary tree
        //        1
        //       / \
        //      2   3
        //     / \ / \
        //    4  5 6  7
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.right = new TreeNode(3);
        root5.left.left = new TreeNode(4);
        root5.left.right = new TreeNode(5);
        root5.right.left = new TreeNode(6);
        root5.right.right = new TreeNode(7);
        String s5 = codec.serialize(root5);
        System.out.println("TC5 Serialized  : " + s5);
        System.out.println("TC5 Deserialized:");
        TreeNode.printTreeWithLines(codec.deserialize(s5));
    }
}
