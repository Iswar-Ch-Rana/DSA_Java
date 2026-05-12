package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.*;

/**
 * Vertical Order Traversal using BFS.
 * - Column index: left = -1, right = +1
 * - BFS preserves top-to-bottom order within each column
 * - TreeMap returns columns from left to right
 */
public class VerticalOrderTraversal {
    public static void main(String[] args) {
        // Input: root = [1, 2, 3, 4, null, 5, 6]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);

        VerticalOrderTraversal obj = new VerticalOrderTraversal();
        System.out.println(obj.verticalOrder(root)); // [[4], [2], [1, 5], [3], [6]]

        // Input: root = [3, 9, 20, null, null, 15, 7]
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(9);
        root2.right = new TreeNode(20);
        root2.right.left = new TreeNode(15);
        root2.right.right = new TreeNode(7);

        System.out.println(obj.verticalOrder(root2)); // [[9], [3, 15], [20], [7]]

        // Input: root = [1, 2, 3, 4, 5, 6, 7]
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);
        root3.left.left = new TreeNode(4);
        root3.left.right = new TreeNode(5);
        root3.right.left = new TreeNode(6);
        root3.right.right = new TreeNode(7);

        System.out.println(obj.verticalOrder(root3)); // [[4], [2], [1, 5, 6], [3], [7]]
    }

    /*
     * Mental Model:
     *
     *   BFS with column index:
     *     root = 0, left = col-1, right = col+1
     *     collect values per column via TreeMap (auto-sorted left → right)
     *     BFS ensures top-to-bottom order within each column
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // column -> nodes in that vertical line (in BFS visit order)
        Map<Integer, List<Integer>> columnMap = new TreeMap<>();
        // Pair: (column index, node)
        Queue<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(0, root));

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                Pair current = queue.poll();
                if (current == null) {
                    continue;
                }

                if (current.node.left != null) {
                    queue.add(new Pair(current.column - 1, current.node.left));
                }

                if (current.node.right != null) {
                    queue.add(new Pair(current.column + 1, current.node.right));
                }

                // Append current node value to its column bucket.
                columnMap.computeIfAbsent(current.column, k -> new ArrayList<>()).add(current.node.val);
            }
        }

        result.addAll(columnMap.values());
        return result;
    }

    private static class Pair {
        // Horizontal distance from root.
        public int column;
        public TreeNode node;

        Pair(int column, TreeNode node) {
            this.column = column;
            this.node = node;
        }
    }
}
