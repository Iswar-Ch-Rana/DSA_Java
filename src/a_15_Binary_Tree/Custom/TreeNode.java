package a_15_Binary_Tree.Custom;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    /** Horizontal character slots per node. Increase for wider spacing. */
    private static final int CELL_W = 4;

    /**
     * Prints a clean binary tree where each edge is exactly one '/' or '\'.
     *
     * Algorithm:
     *  1. In-order traversal assigns x-slot (column index) and depth (row index)
     *     to every node — parent always lands between its children naturally.
     *  2. Canvas rows alternate: even rows = node values, odd rows = connectors.
     *  3. Each connector is a SINGLE '/' or '\' placed at the midpoint column
     *     between parent and child, so the tree always looks like:
     *
     *                         1
     *               /                 \
     *              2                   3
     *            /   \               /   \
     *           4     5             6     7
     *                / \
     *               8   9
     */
    public static void printTreeWithLines(TreeNode root) {
        if (root == null) return;

        // Step 1: assign (xSlot, depth) via in-order traversal
        Map<TreeNode, int[]> pos = new LinkedHashMap<>();
        int[] xCounter = {0};
        assignPositions(root, 0, pos, xCounter);

        // Step 2: size the canvas
        int maxX = 0, maxDepth = 0;
        for (int[] p : pos.values()) {
            if (p[0] > maxX)     maxX     = p[0];
            if (p[1] > maxDepth) maxDepth = p[1];
        }
        int canvasW = (maxX + 1) * CELL_W + 2;
        int canvasH = maxDepth * 2 + 1;   // alternating node-rows and slash-rows

        char[][] canvas = new char[canvasH][canvasW];
        for (char[] row : canvas) Arrays.fill(row, ' ');

        // Step 3: draw ONE connector character per edge, at the midpoint column
        for (Map.Entry<TreeNode, int[]> e : pos.entrySet()) {
            TreeNode node = e.getKey();
            int[] p = e.getValue();
            int parentCol = p[0] * CELL_W + CELL_W / 2;
            int slashRow  = p[1] * 2 + 1;

            if (node.left != null) {
                int childCol = pos.get(node.left)[0] * CELL_W + CELL_W / 2;
                int mid = (parentCol + childCol) / 2;
                safeSet(canvas, slashRow, mid, '/');
            }
            if (node.right != null) {
                int childCol = pos.get(node.right)[0] * CELL_W + CELL_W / 2;
                int mid = (parentCol + childCol) / 2;
                safeSet(canvas, slashRow, mid, '\\');
            }
        }

        // Step 4: paint node values (overwrites any connector that lands on same cell)
        for (Map.Entry<TreeNode, int[]> e : pos.entrySet()) {
            TreeNode node = e.getKey();
            int[] p = e.getValue();
            int centreCol = p[0] * CELL_W + CELL_W / 2;
            int nodeRow   = p[1] * 2;
            String label  = String.valueOf(node.val);
            int startCol  = centreCol - label.length() / 2;
            for (int i = 0; i < label.length(); i++) {
                safeSet(canvas, nodeRow, startCol + i, label.charAt(i));
            }
        }

        // Step 5: print, stripping trailing whitespace on each line
        StringBuilder sb = new StringBuilder();
        for (char[] row : canvas) {
            int last = row.length - 1;
            while (last >= 0 && row[last] == ' ') last--;
            if (last >= 0) sb.append(row, 0, last + 1);
            sb.append('\n');
        }
        System.out.print(sb.toString().stripTrailing());
        System.out.println();
    }

    /**
     * In-order traversal: left → current → right.
     * Naturally centres each parent between its children's x-slots.
     */
    private static void assignPositions(TreeNode node, int depth,
                                        Map<TreeNode, int[]> pos, int[] xCounter) {
        if (node == null) return;
        assignPositions(node.left,  depth + 1, pos, xCounter);
        pos.put(node, new int[]{xCounter[0]++, depth});
        assignPositions(node.right, depth + 1, pos, xCounter);
    }

    private static void safeSet(char[][] canvas, int row, int col, char ch) {
        if (row >= 0 && row < canvas.length && col >= 0 && col < canvas[0].length) {
            canvas[row][col] = ch;
        }
    }

    public static int getHeight(TreeNode node) {
        if (node == null) return 0;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }
}