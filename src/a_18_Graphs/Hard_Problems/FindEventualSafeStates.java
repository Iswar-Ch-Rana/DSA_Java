package a_18_Graphs.Hard_Problems;

import java.util.*;

/**
 * Find Eventual Safe States — return all nodes that eventually lead to a terminal node.
 * <p>
 * A node is SAFE if every path from it leads to a terminal node (no outgoing edges),
 * i.e., the node is NOT part of or leading into any cycle.
 * <p>
 * Two approaches:
 * DFS  : track inPath (current call stack). After full DFS, nodes with inPath=false are safe.
 * Nodes in a cycle never get inPath reset to false (we return early on cycle detection).
 * BFS  : reverse the graph edges, then run Kahn's algorithm.
 * Terminal nodes (out-degree 0 original = in-degree 0 reversed) are safe seeds.
 * Any node that can reach a terminal node in the original graph = safe.
 * <p>
 * Time  : O(V + E) — each node and edge processed at most once.
 * Space : O(V + E) — visited/inPath arrays + reverse adjacency list.
 */
public class FindEventualSafeStates {

    /*
     * Mental Model — DFS:
     *
     *   visited[node] = true  → fully processed
     *   inPath[node]  = true  → currently on the DFS call stack
     *
     *   if cycle detected → return true WITHOUT resetting inPath
     *   if no cycle      → inPath[node] = false before returning
     *
     *   After all DFS calls:
     *       inPath[i] = false  →  node i is safe  (backtracked cleanly)
     *       inPath[i] = true   →  node i is unsafe (stuck in or leads to cycle)
     *
     * Mental Model — BFS (reverse graph + Kahn's):
     *
     *   Reverse all edges: u→v becomes v→u
     *   In the reversed graph, terminal nodes (original out-degree 0) have in-degree 0
     *   Kahn's BFS from those seeds propagates "safe" backwards through the original graph
     *   All nodes processed by Kahn's = eventual safe states
     *   Result must be sorted (Kahn's order ≠ node-number order)
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS (visited + inPath)
    // ─────────────────────────────────────────────────────────────

    public int[] eventualSafeNodesDFS(int V, int[][] adj) {
        boolean[] visited = new boolean[V];
        boolean[] inPath = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i])
                dfs(i, adj, visited, inPath);
        }

        List<Integer> safe = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (!inPath[i]) safe.add(i);
        }
        return safe.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean dfs(int node, int[][] adj, boolean[] visited, boolean[] inPath) {
        visited[node] = true;
        inPath[node] = true;

        for (int neighbour : adj[node]) {
            if (inPath[neighbour])
                return true;                         // back-edge → cycle
            if (!visited[neighbour] && dfs(neighbour, adj, visited, inPath))
                return true;
        }

        inPath[node] = false; // safe — backtrack cleanly
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS (reverse graph + Kahn's)
    // ─────────────────────────────────────────────────────────────

    public int[] eventualSafeNodesBFS(int V, int[][] adj) {
        // Step 1: build reverse adjacency + original out-degree as in-degree for Kahn's
        List<List<Integer>> reverseAdj = new ArrayList<>();
        for (int i = 0; i < V; i++)
            reverseAdj.add(new ArrayList<>());

        int[] indegree = new int[V]; // in-degree in the REVERSE graph = out-degree in original
        for (int u = 0; u < V; u++) {
            for (int v : adj[u]) {
                reverseAdj.get(v).add(u);
                indegree[u]++;
            }
        }

        // Step 2: seed queue with nodes that have no outgoing edges in original (in-degree 0 in reverse)
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        // Step 3: Kahn's BFS on reversed graph — propagate "safe" backwards
        List<Integer> safe = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            safe.add(node);
            for (int neighbour : reverseAdj.get(node)) {
                if (--indegree[neighbour] == 0)
                    queue.offer(neighbour);
            }
        }

        return safe.stream().mapToInt(Integer::intValue).sorted().toArray();
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        FindEventualSafeStates obj = new FindEventualSafeStates();

        // TC1: original problem graph
        //   0 → {1,2},  1 → {2,3},  2 → {5},  3 → {0},  4 → {5},  5 → {},  6 → {}
        //   Cycles: 0→1→3→0
        //   Safe nodes: 2, 4, 5, 6
        int[][] g1 = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        System.out.println("TC1 DFS: " + Arrays.toString(obj.eventualSafeNodesDFS(7, g1)));  // [2,4,5,6]
        System.out.println("TC1 BFS: " + Arrays.toString(obj.eventualSafeNodesBFS(7, g1)));  // [2,4,5,6]

        // TC2: all nodes safe — pure DAG, no cycles
        //   0 → 1 → 2 → 3
        int[][] g2 = {{1}, {2}, {3}, {}};
        System.out.println("TC2 DFS: " + Arrays.toString(obj.eventualSafeNodesDFS(4, g2)));  // [0,1,2,3]
        System.out.println("TC2 BFS: " + Arrays.toString(obj.eventualSafeNodesBFS(4, g2)));  // [0,1,2,3]

        // TC3: all nodes unsafe — every node is in the cycle
        //   0 → 1 → 2 → 0
        int[][] g3 = {{1}, {2}, {0}};
        System.out.println("TC3 DFS: " + Arrays.toString(obj.eventualSafeNodesDFS(3, g3)));  // []
        System.out.println("TC3 BFS: " + Arrays.toString(obj.eventualSafeNodesBFS(3, g3)));  // []

        // TC4: node leads INTO a cycle but is not in it
        //   0 → 1 → 2 → 1  (cycle: 1↔2),  node 0 leads into it → unsafe
        //   3 → {} (terminal) → safe
        int[][] g4 = {{1}, {2}, {1}, {}};
        System.out.println("TC4 DFS: " + Arrays.toString(obj.eventualSafeNodesDFS(4, g4)));  // [3]
        System.out.println("TC4 BFS: " + Arrays.toString(obj.eventualSafeNodesBFS(4, g4)));  // [3]

        // TC5: self-loop — node 1 is unsafe; node 0 leads to it → also unsafe; node 2 is safe
        //   0 → 1,  1 → 1 (self-loop),  2 → {}
        int[][] g5 = {{1}, {1}, {}};
        System.out.println("TC5 DFS: " + Arrays.toString(obj.eventualSafeNodesDFS(3, g5)));  // [2]
        System.out.println("TC5 BFS: " + Arrays.toString(obj.eventualSafeNodesBFS(3, g5)));  // [2]
    }
}
