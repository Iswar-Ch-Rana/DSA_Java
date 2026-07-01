package a_18_Graphs.Cycles;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Detect Cycle in Undirected Graph — check if any back-edge exists.
 * <p>
 * Idea:
 * During DFS/BFS, track the parent of each node.
 * If a visited neighbour is NOT the parent, a back-edge exists → cycle detected.
 * Run from every unvisited node to handle disconnected graphs.
 * <p>
 * Time  : O(V + E) — each node and edge processed at most once.
 * Space : O(V)     — visited array + recursion stack / queue.
 */
public class DetectCycleInUndirectedGraph {

    /*
     * Mental Model:
     *
     *   for each unvisited node (handles disconnected graph):
     *       DFS/BFS with parent tracking
     *
     *   for each neighbour of current node:
     *       neighbour == parent  → skip  (the edge we came from, not a cycle)
     *       neighbour is visited → cycle found  (back-edge)
     *       neighbour unvisited  → recurse / enqueue
     *
     *   Why parent check matters:
     *       undirected edges appear in BOTH directions in adj list
     *       without skipping parent, we'd always "detect" the edge we just used
     *
     *   Example cycle:  0 - 1 - 2 - 0
     *   Example no cycle: 0 - 1 - 2 - 3  (simple path, no back-edge)
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS
    // ─────────────────────────────────────────────────────────────

    public boolean isCycleDFS(int V, List<Integer>[] adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i] && dfs(i, -1, adj, visited)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfs(int node, int parent, List<Integer>[] adj, boolean[] visited) {
        visited[node] = true;

        for (int neighbour : adj[node]) {
            if (neighbour == parent) continue;       // edge we came from
            if (visited[neighbour]) return true;     // back-edge → cycle
            if (dfs(neighbour, node, adj, visited)) return true;
        }
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS
    // ─────────────────────────────────────────────────────────────

    public boolean isCycleBFS(int V, List<Integer>[] adj) {
        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i] && bfs(i, adj, visited)) {
                return true;
            }
        }
        return false;
    }

    // Queue stores {node, parent} pairs
    private boolean bfs(int start, List<Integer>[] adj, boolean[] visited) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{start, -1});
        visited[start] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0], parent = curr[1];

            for (int neighbour : adj[node]) {
                if (neighbour == parent) continue;       // edge we came from
                if (visited[neighbour]) return true;     // back-edge → cycle
                visited[neighbour] = true;
                queue.offer(new int[]{neighbour, node});
            }
        }
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // Shared helpers
    // ─────────────────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private static List<Integer>[] buildAdj(int V, int[][] edges) {
        List<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        return adj;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        DetectCycleInUndirectedGraph obj = new DetectCycleInUndirectedGraph();

        // TC1: simple triangle — cycle exists
        //   0 - 1
        //    \ /
        //     2
        List<Integer>[] g1a = buildAdj(3, new int[][]{{0, 1}, {1, 2}, {2, 0}});
        List<Integer>[] g1b = buildAdj(3, new int[][]{{0, 1}, {1, 2}, {2, 0}});
        System.out.println("TC1 DFS: " + obj.isCycleDFS(3, g1a)); // true
        System.out.println("TC1 BFS: " + obj.isCycleBFS(3, g1b)); // true

        // TC2: simple path — no cycle
        //   0 - 1 - 2 - 3
        List<Integer>[] g2a = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        List<Integer>[] g2b = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        System.out.println("TC2 DFS: " + obj.isCycleDFS(4, g2a)); // false
        System.out.println("TC2 BFS: " + obj.isCycleBFS(4, g2b)); // false

        // TC3: disconnected graph — one component has a cycle
        //   0 - 1    2 - 3
        //            |   |
        //            4 - 5  ← cycle in this component
        List<Integer>[] g3a = buildAdj(6, new int[][]{{0, 1}, {2, 3}, {3, 5}, {5, 4}, {4, 2}});
        List<Integer>[] g3b = buildAdj(6, new int[][]{{0, 1}, {2, 3}, {3, 5}, {5, 4}, {4, 2}});
        System.out.println("TC3 DFS: " + obj.isCycleDFS(6, g3a)); // true
        System.out.println("TC3 BFS: " + obj.isCycleBFS(6, g3b)); // true

        // TC4: single node, no edges — no cycle
        List<Integer>[] g4a = buildAdj(1, new int[][]{});
        List<Integer>[] g4b = buildAdj(1, new int[][]{});
        System.out.println("TC4 DFS: " + obj.isCycleDFS(1, g4a)); // false
        System.out.println("TC4 BFS: " + obj.isCycleBFS(1, g4b)); // false

        // TC5: two nodes with one edge — no cycle (parent skip prevents false positive)
        //   0 - 1
        List<Integer>[] g5a = buildAdj(2, new int[][]{{0, 1}});
        List<Integer>[] g5b = buildAdj(2, new int[][]{{0, 1}});
        System.out.println("TC5 DFS: " + obj.isCycleDFS(2, g5a)); // false
        System.out.println("TC5 BFS: " + obj.isCycleBFS(2, g5b)); // false
    }
}
