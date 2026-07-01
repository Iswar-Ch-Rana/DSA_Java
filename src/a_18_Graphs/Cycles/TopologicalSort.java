package a_18_Graphs.Cycles;

import java.util.*;

/**
 * Topological Sort — linear ordering of vertices such that for every directed edge u→v, u comes before v.
 * <p>
 * Only valid for a DAG (Directed Acyclic Graph).
 * <p>
 * Two approaches:
 * DFS  : push each node to a stack AFTER all its neighbours are visited (post-order).
 * Pop the stack to get the topological order.
 * BFS  : Kahn's algorithm — repeatedly remove nodes with in-degree 0 (no unresolved dependencies).
 * <p>
 * Time  : O(V + E) — each node and edge processed at most once.
 * Space : O(V)     — visited array + stack / queue + in-degree array.
 */
public class TopologicalSort {

    /*
     * Mental Model — DFS (post-order stack):
     *
     *   for each unvisited node:
     *       recurse into all unvisited neighbours first
     *       push current node ONLY AFTER all neighbours are done
     *   pop stack → left-to-right = topological order
     *
     *   Why post-order?  A node is pushed only when every node it points to
     *   is already on the stack → they will pop out before it.
     *
     * Mental Model — BFS / Kahn's:
     *
     *   compute in-degree for every node
     *   enqueue all nodes with in-degree 0 (no dependencies)
     *   while queue not empty:
     *       dequeue node → add to result
     *       for each neighbour: decrement in-degree; if 0 → enqueue
     *
     *   Why in-degree 0?  A node with no remaining in-edges has all its
     *   prerequisites already placed → safe to output it next.
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS (post-order stack)
    // ─────────────────────────────────────────────────────────────

    public int[] topologicalSortDFS(int V, List<Integer>[] adj) {
        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) dfs(i, adj, visited, stack);
        }

        int[] result = new int[V];
        for (int i = 0; i < V; i++) result[i] = stack.pop();
        return result;
    }

    private void dfs(int node, List<Integer>[] adj, boolean[] visited, Deque<Integer> stack) {
        visited[node] = true;
        for (int neighbour : adj[node]) {
            if (!visited[neighbour]) dfs(neighbour, adj, visited, stack);
        }
        stack.push(node); // push AFTER all neighbours → post-order
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS — Kahn's Algorithm (in-degree)
    // ─────────────────────────────────────────────────────────────

    public int[] topologicalSortBFS(int V, List<Integer>[] adj) {
        int[] indegree = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : adj[u]) indegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int[] result = new int[V];
        int idx = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result[idx++] = node;
            for (int neighbour : adj[node]) {
                if (--indegree[neighbour] == 0) queue.offer(neighbour);
            }
        }
        return result;
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
        TopologicalSort obj = new TopologicalSort();

        // TC1: classic DAG — from original problem
        //   5 → 0, 5 → 2
        //   4 → 0, 4 → 1
        //   2 → 3
        //   3 → 1
        // Valid orders include: [5,4,2,3,1,0] or [4,5,2,3,1,0] etc.
        List<Integer>[] g1a = buildAdj(6, new int[][]{{5, 0}, {5, 2}, {4, 0}, {4, 1}, {2, 3}, {3, 1}});
        List<Integer>[] g1b = buildAdj(6, new int[][]{{5, 0}, {5, 2}, {4, 0}, {4, 1}, {2, 3}, {3, 1}});
        System.out.println("TC1 DFS: " + Arrays.toString(obj.topologicalSortDFS(6, g1a)));
        System.out.println("TC1 BFS: " + Arrays.toString(obj.topologicalSortBFS(6, g1b)));

        // TC2: linear chain — only one valid order
        //   0 → 1 → 2 → 3   →   [0, 1, 2, 3]
        List<Integer>[] g2a = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        List<Integer>[] g2b = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        System.out.println("TC2 DFS: " + Arrays.toString(obj.topologicalSortDFS(4, g2a))); // [0,1,2,3]
        System.out.println("TC2 BFS: " + Arrays.toString(obj.topologicalSortBFS(4, g2b))); // [0,1,2,3]

        // TC3: diamond DAG — multiple valid orders
        //       0
        //      / \
        //     1   2
        //      \ /
        //       3
        List<Integer>[] g3a = buildAdj(4, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}});
        List<Integer>[] g3b = buildAdj(4, new int[][]{{0, 1}, {0, 2}, {1, 3}, {2, 3}});
        System.out.println("TC3 DFS: " + Arrays.toString(obj.topologicalSortDFS(4, g3a))); // 0 before 3
        System.out.println("TC3 BFS: " + Arrays.toString(obj.topologicalSortBFS(4, g3b))); // 0 before 3

        // TC4: single node, no edges
        List<Integer>[] g4a = buildAdj(1, new int[][]{});
        List<Integer>[] g4b = buildAdj(1, new int[][]{});
        System.out.println("TC4 DFS: " + Arrays.toString(obj.topologicalSortDFS(1, g4a))); // [0]
        System.out.println("TC4 BFS: " + Arrays.toString(obj.topologicalSortBFS(1, g4b))); // [0]

        // TC5: disconnected DAG — two independent chains
        //   0 → 1    2 → 3
        // Both chains must appear in order; relative order between chains is flexible
        List<Integer>[] g5a = buildAdj(4, new int[][]{{0, 1}, {2, 3}});
        List<Integer>[] g5b = buildAdj(4, new int[][]{{0, 1}, {2, 3}});
        System.out.println("TC5 DFS: " + Arrays.toString(obj.topologicalSortDFS(4, g5a))); // 0 before 1, 2 before 3
        System.out.println("TC5 BFS: " + Arrays.toString(obj.topologicalSortBFS(4, g5b))); // 0 before 1, 2 before 3
    }
}
