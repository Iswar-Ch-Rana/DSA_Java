package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.*;

public class VerticalOrderTraversal {
    public static void main(String[] args) {

    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null) return ans;
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(0, root));

        while (!q.isEmpty()) {
            int len = q.size();

            for (int i = 0; i < len; i++) {
                Pair p = q.poll();

                if (p.node.left != null) {
                    q.add(new Pair(p.column - 1, p.node.left));
                }

                if (p.node.right != null) {
                    q.add(new Pair(p.column + 1, p.node.right));
                }

                map.computeIfAbsent(p.column, k -> new ArrayList<>()).add(p.node.val);
            }
        }
        System.out.println(map);

        return ans;

    }

    class Pair {
        public int column;
        public TreeNode node;

        Pair(int column, TreeNode node) {
            this.column = column;
            this.node = node;
        }
    }
}
