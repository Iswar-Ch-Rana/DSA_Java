package a_18_Graphs.TheoryAndTraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * BFS (Breadth-First Search) on an undirected graph.
 * <p>
 * Idea: Visit all nodes level by level using a queue.
 * Mark nodes visited before enqueuing to avoid revisiting.
 * <p>
 * Time  : O(V + E) — each vertex and edge processed once.
 * Space : O(V)     — visited array + queue.
 */
public class BFSTraversal {

    /*
     * Mental Model:
     *
     *   mark start as visited, enqueue it
     *
     *   while queue not empty:
     *       dequeue node  → add to result
     *       for each neighbor:
     *           if not visited → mark visited, enqueue
     */

    // ─────────────────────────────────────────────────────────────
    // BFS — level-order graph traversal
    // ─────────────────────────────────────────────────────────────
    public List<Integer> bfs(int start, List<List<Integer>> adj, int n) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> result = new ArrayList<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            result.add(current);                        // visit node

            for (int neighbor : adj.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;           // mark before enqueue to avoid duplicates
                    queue.offer(neighbor);
                }
            }
        }

        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // Helper: build undirected adjacency list
    // ─────────────────────────────────────────────────────────────
    private static List<List<Integer>> buildAdj(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        return adj;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        BFSTraversal sol = new BFSTraversal();

        // TC1: Standard connected graph
        // 0 - 1 - 2
        //     |
        //     3
        // BFS from 0: [0, 1, 2, 3]
        List<List<Integer>> adj1 = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {1, 3}});
        System.out.println("TC1: " + sol.bfs(0, adj1, 4)); // [0, 1, 2, 3]

        // TC2: Linear chain 0-1-2-3-4
        // BFS from 0: [0, 1, 2, 3, 4]
        List<List<Integer>> adj2 = buildAdj(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}});
        System.out.println("TC2: " + sol.bfs(0, adj2, 5)); // [0, 1, 2, 3, 4]

        // TC3: Star graph — center 0, leaves 1,2,3,4
        // BFS from 0: [0, 1, 2, 3, 4]
        List<List<Integer>> adj3 = buildAdj(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}});
        System.out.println("TC3: " + sol.bfs(0, adj3, 5)); // [0, 1, 2, 3, 4]

        // TC4: Single node
        List<List<Integer>> adj4 = buildAdj(1, new int[][]{});
        System.out.println("TC4: " + sol.bfs(0, adj4, 1)); // [0]

        // TC5: BFS from non-zero start
        // 0-1-2, 2-3, start=2 → [2, 1, 3, 0]
        List<List<Integer>> adj5 = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        System.out.println("TC5: " + sol.bfs(2, adj5, 4)); // [2, 1, 3, 0]
    }
}
