package a_15_Binary_Tree.FAQs;

import a_15_Binary_Tree.Custom.TreeNode;

import java.util.*;

public class PrintAllNodesAtADistanceOfKInBT {

    public static void main(String[] args) {
        // root = [3,5,1,6,2,0,8,N,N,7,4], target = 5, k = 2

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        System.out.println(distanceK(root, root.left, 2)); // [7,4,1]
        System.out.println(distanceK(root, root.left, 3)); // [0,8]
    }

    /*
     * Mental Model:
     *
     *   Step 1: Build parent map (BFS from root)
     *   Step 2: BFS from target, spreading to left, right, parent
     *           stop when level == k → collect all nodes in queue
     */
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) return ans;

        // Step 1: Build parent map
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        buildParent(root, parent);

        // Step 2: BFS from target
        Queue<TreeNode> q = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        q.offer(target);
        visited.add(target);

        int level = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            if (level == k) {
                while (!q.isEmpty()) {
                    ans.add(q.poll().val);
                }
                return ans;
            }

            for (int i = 0; i < size; i++) {
                TreeNode curr = q.poll();

                // left
                if (curr.left != null && !visited.contains(curr.left)) {
                    q.offer(curr.left);
                    visited.add(curr.left);
                }

                // right
                if (curr.right != null && !visited.contains(curr.right)) {
                    q.offer(curr.right);
                    visited.add(curr.right);
                }

                // parent
                TreeNode par = parent.get(curr);
                if (par != null && !visited.contains(par)) {
                    q.offer(par);
                    visited.add(par);
                }
            }

            level++;
        }

        return ans;
    }

    private static void buildParent(TreeNode root, Map<TreeNode, TreeNode> parent) {
        if (root == null) return;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();

            if (curr.left != null) {
                parent.put(curr.left, curr);
                q.offer(curr.left);
            }

            if (curr.right != null) {
                parent.put(curr.right, curr);
                q.offer(curr.right);
            }
        }
    }
}