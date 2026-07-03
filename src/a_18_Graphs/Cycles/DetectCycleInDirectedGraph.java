package a_18_Graphs.Cycles;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Detect Cycle in Directed Graph — check if any back-edge exists in a directed graph.
 * <p>
 * Two approaches:
 * DFS  : track two states — permanently visited and currently in the DFS path (inPath).
 * If a neighbour is already inPath → back-edge → cycle.
 * BFS  : Kahn's algorithm — if topological sort can't process all V nodes,
 * some nodes were stuck in a cycle (their in-degree never reached 0).
 * <p>
 * Time  : O(V + E) — each node and edge processed at most once.
 * Space : O(V)     — visited/inPath arrays + recursion stack / queue.
 */
public class DetectCycleInDirectedGraph {

    /*
     * Mental Model — DFS:
     *
     *   visited[node] = true  →  node is fully processed (no need to revisit)
     *   inPath[node]  = true  →  node is on the current DFS call stack
     *
     *   for each neighbour:
     *       inPath[neighbour]    → back-edge in directed graph → CYCLE
     *       !visited[neighbour]  → recurse deeper
     *       visited && !inPath   → already explored, no cycle from there → skip
     *
     *   inPath[node] = false  AFTER all neighbours explored (backtrack)
     *
     *   Why two arrays?  In undirected graphs, any visited neighbour = cycle.
     *   In directed graphs, a visited neighbour is fine if it's NOT in the current
     *   path — it just means we already explored that branch cleanly.
     *
     * Mental Model — BFS (Kahn's):
     *
     *   compute in-degree for every node
     *   process nodes with in-degree 0 (BFS), counting processed nodes
     *   if processed < V → remaining nodes form a cycle (in-degree never reached 0)
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS (visited + inPath)
    // ─────────────────────────────────────────────────────────────

    public boolean isCyclicDFS(int V, List<Integer>[] adj) {
        boolean[] visited = new boolean[V];
        boolean[] inPath = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i] && dfs(i, adj, visited, inPath)) return true;
        }
        return false;
    }

    private boolean dfs(int node, List<Integer>[] adj, boolean[] visited, boolean[] inPath) {
        visited[node] = true;
        inPath[node] = true;

        for (int neighbour : adj[node]) {
            if (inPath[neighbour]) return true;                        // back-edge → cycle
            if (!visited[neighbour] && dfs(neighbour, adj, visited, inPath)) return true;
        }

        inPath[node] = false; // backtrack — node leaves the current path
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS — Kahn's Algorithm (in-degree count)
    // ─────────────────────────────────────────────────────────────

    public boolean isCyclicBFS(int V, List<Integer>[] adj) {
        int[] indegree = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : adj[u]) indegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int processed = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            processed++;
            for (int neighbour : adj[node]) {
                if (--indegree[neighbour] == 0) queue.offer(neighbour);
            }
        }

        return processed < V; // unprocessed nodes are stuck in a cycle
    }

    // ─────────────────────────────────────────────────────────────
    // Shared helpers
    // ─────────────────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private static List<Integer>[] buildAdj(int V, int[][] edges) {
        List<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) adj[e[0]].add(e[1]); // directed: u → v only
        return adj;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        DetectCycleInDirectedGraph obj = new DetectCycleInDirectedGraph();

        // TC1: simple directed cycle
        //   0 → 1 → 2 → 0
        List<Integer>[] g1a = buildAdj(3, new int[][]{{0, 1}, {1, 2}, {2, 0}});
        List<Integer>[] g1b = buildAdj(3, new int[][]{{0, 1}, {1, 2}, {2, 0}});
        System.out.println("TC1 DFS: " + obj.isCyclicDFS(3, g1a)); // true
        System.out.println("TC1 BFS: " + obj.isCyclicBFS(3, g1b)); // true

        // TC2: DAG — no cycle
        //   0 → 1 → 2 → 3
        List<Integer>[] g2a = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        List<Integer>[] g2b = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        System.out.println("TC2 DFS: " + obj.isCyclicDFS(4, g2a)); // false
        System.out.println("TC2 BFS: " + obj.isCyclicBFS(4, g2b)); // false

        // TC3: visited-but-not-inPath — must NOT be a false positive
        //   0 → 1 → 3
        //   0 → 2 → 3   ← node 3 visited via two paths; not a cycle
        List<Integer>[] g3a = buildAdj(4, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}});
        List<Integer>[] g3b = buildAdj(4, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}});
        System.out.println("TC3 DFS: " + obj.isCyclicDFS(4, g3a)); // false
        System.out.println("TC3 BFS: " + obj.isCyclicBFS(4, g3b)); // false

        // TC4: disconnected — one component has a cycle
        //   0 → 1   (no cycle)    2 → 3 → 4 → 2  (cycle)
        List<Integer>[] g4a = buildAdj(5, new int[][]{{0, 1}, {2, 3}, {3, 4}, {4, 2}});
        List<Integer>[] g4b = buildAdj(5, new int[][]{{0, 1}, {2, 3}, {3, 4}, {4, 2}});
        System.out.println("TC4 DFS: " + obj.isCyclicDFS(5, g4a)); // true
        System.out.println("TC4 BFS: " + obj.isCyclicBFS(5, g4b)); // true

        // TC5: self-loop — cycle of length 1
        //   0 → 0
        List<Integer>[] g5a = buildAdj(2, new int[][]{{0, 0}});
        List<Integer>[] g5b = buildAdj(2, new int[][]{{0, 0}});
        System.out.println("TC5 DFS: " + obj.isCyclicDFS(2, g5a)); // true
        System.out.println("TC5 BFS: " + obj.isCyclicBFS(2, g5b)); // true
    }
}
