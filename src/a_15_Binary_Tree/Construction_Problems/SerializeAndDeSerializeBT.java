package a_15_Binary_Tree.Construction_Problems;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Serialize and Deserialize a Binary Tree — 2 approaches.
 * <p>
 * Approach 1 (DFS / Preorder):
 * Serialize : Preorder DFS; write value then recurse left and right; "n" for null.
 * Deserialize: Preorder DFS consumes tokens left-to-right using an int[] index pointer.
 * Time: O(N)  Space: O(H) recursion stack
 * <p>
 * Approach 2 (BFS / Level-order):
 * Serialize : Level-order BFS; enqueue nulls too so token stream stays aligned.
 * Deserialize: Level-order BFS; each dequeued parent consumes the next 2 tokens.
 * Time: O(N)  Space: O(N) queue
 * <p>
 * Key insight: null markers ("n") are mandatory — without them tree shape is ambiguous.
 * Key gotcha : BFS queue must be LinkedList (not ArrayDeque) — ArrayDeque rejects nulls.
 * Key gotcha : DFS deserialize index must be int[] (not int field) — resets per call.
 */
public class SerializeAndDeSerializeBT {

    private static final String NULL_MARKER = "n";
    private static final String DELIMITER = " ";

    // ─────────────────────────────────────────────────────────────
    // Approach 1 — DFS (Preorder)
    // ─────────────────────────────────────────────────────────────

    // Preorder DFS: root → left → right; write "n" for null nodes.
    public String serializeDFS(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        dfsSerialize(root, sb);
        return sb.toString().trim();
    }

    private void dfsSerialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NULL_MARKER).append(DELIMITER);
            return;
        }
        sb.append(node.val).append(DELIMITER);
        dfsSerialize(node.left, sb);
        dfsSerialize(node.right, sb);
    }

    // int[] used as mutable index — avoids instance-variable bug across multiple calls.
    public TreeNode deserializeDFS(String data) {
        if (data == null || data.isEmpty()) return null;
        String[] tokens = data.split(DELIMITER);
        return dfsDeserialize(tokens, new int[]{0});
    }

    private TreeNode dfsDeserialize(String[] tokens, int[] idx) {
        if (idx[0] >= tokens.length) return null;

        String token = tokens[idx[0]++];
        if (token.equals(NULL_MARKER)) return null;

        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = dfsDeserialize(tokens, idx); // consume left subtree tokens
        node.right = dfsDeserialize(tokens, idx); // consume right subtree tokens
        return node;
    }

    // ─────────────────────────────────────────────────────────────
    // Approach 2 — BFS (Level-order)
    // ─────────────────────────────────────────────────────────────

    // Level-order BFS: enqueue both children (even nulls) to preserve shape.
    // LinkedList used — ArrayDeque would throw NullPointerException on null enqueue.
    public String serializeBFS(TreeNode root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current == null) {
                sb.append(NULL_MARKER).append(DELIMITER);
                continue;
            }

            sb.append(current.val).append(DELIMITER);
            queue.add(current.left);  // enqueue even if null
            queue.add(current.right);
        }

        return sb.toString().trim();
    }

    // Each dequeued parent consumes the next 2 tokens: left child, right child.
    public TreeNode deserializeBFS(String data) {
        if (data == null || data.isEmpty()) return null;

        String[] tokens = data.split(DELIMITER);
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // i always points to the left-child token of the current parent.
        for (int i = 1; i < tokens.length; i++) {
            TreeNode parent = queue.poll();

            if (!tokens[i].equals(NULL_MARKER)) {
                parent.left = new TreeNode(Integer.parseInt(tokens[i]));
                queue.add(parent.left);
            }

            i++; // advance to right-child token
            if (i < tokens.length && !tokens[i].equals(NULL_MARKER)) {
                parent.right = new TreeNode(Integer.parseInt(tokens[i]));
                queue.add(parent.right);
            }
        }

        return root;
    }

    // ─────────────────────────────────────────────────────────────
    // Main — verify both approaches produce the same trees
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
        verify(codec, "TC1 Standard      ", root1);

        // Test Case 2: Single node
        verify(codec, "TC2 Single node   ", new TreeNode(42));

        // Test Case 3: Skewed with gap
        //   1
        //  /
        // 2
        //  \
        //   3
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.left.right = new TreeNode(3);
        verify(codec, "TC3 Skewed+gap    ", root3);

        // Test Case 4: null root
        System.out.println("TC4 null root DFS : '" + codec.serializeDFS(null) + "'");
        System.out.println("TC4 null root BFS : '" + codec.serializeBFS(null) + "'");
        System.out.println();

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
        verify(codec, "TC5 Full BT       ", root5);
    }

    private static void verify(SerializeAndDeSerializeBT codec, String label, TreeNode root) {
        String dfs = codec.serializeDFS(root);
        String bfs = codec.serializeBFS(root);
        System.out.println(label + " DFS serialized : " + dfs);
        System.out.println(label + " BFS serialized : " + bfs);
        System.out.println(label + " DFS deserialized:");
        TreeNode.printTreeWithLines(codec.deserializeDFS(dfs));
        System.out.println(label + " BFS deserialized:");
        TreeNode.printTreeWithLines(codec.deserializeBFS(bfs));
        System.out.println();
    }
}
