package a_18_Graphs.Cycles;

import java.util.*;

/**
 * Bipartite Graph — check if the graph can be 2-coloured with no two adjacent nodes sharing a colour.
 * <p>
 * Idea:
 * Assign colour 0 to the start node, then colour every neighbour with the opposite colour (1 - current).
 * If we ever reach a neighbour that already has the same colour as the current node → not bipartite.
 * Run from every unvisited node to handle disconnected graphs.
 * <p>
 * Equivalent to: the graph contains no odd-length cycle.
 * <p>
 * Time  : O(V + E) — each node and edge processed at most once.
 * Space : O(V)     — colour array + recursion stack / queue.
 */
public class BipartiteGraph {

    /*
     * Mental Model:
     *
     *   color[] = { -1 = uncoloured, 0 = red, 1 = blue }
     *
     *   for each uncoloured node (handles disconnected graph):
     *       assign colour 0, then DFS/BFS
     *
     *   for each neighbour of current node:
     *       uncoloured   → assign opposite colour, recurse/enqueue
     *       same colour  → conflict → NOT bipartite
     *       diff colour  → fine, already correctly coloured
     *
     *   Example bipartite (even cycle):
     *       0 - 1 - 2 - 3 - 0   →   0:red, 1:blue, 2:red, 3:blue  ✓
     *
     *   Example NOT bipartite (odd cycle):
     *       0 - 1 - 2 - 0       →   0:red, 1:blue, 2:red … 2→0 same colour ✗
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS
    // ─────────────────────────────────────────────────────────────

    public boolean isBipartiteDFS(int V, List<Integer>[] adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                color[i] = 0;
                if (!dfs(i, color, adj)) return false;
            }
        }
        return true;
    }

    private boolean dfs(int node, int[] color, List<Integer>[] adj) {
        for (int neighbour : adj[node]) {
            if (color[neighbour] == -1) {
                color[neighbour] = 1 - color[node];
                if (!dfs(neighbour, color, adj)) return false;
            } else if (color[neighbour] == color[node]) {
                return false;  // same colour → conflict
            }
        }
        return true;
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS
    // ─────────────────────────────────────────────────────────────

    public boolean isBipartiteBFS(int V, List<Integer>[] adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1 && !bfs(i, color, adj)) return false;
        }
        return true;
    }

    private boolean bfs(int start, int[] color, List<Integer>[] adj) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        color[start] = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbour : adj[node]) {
                if (color[neighbour] == -1) {
                    color[neighbour] = 1 - color[node];
                    queue.offer(neighbour);
                } else if (color[neighbour] == color[node]) {
                    return false;  // same colour → conflict
                }
            }
        }
        return true;
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
        BipartiteGraph obj = new BipartiteGraph();

        // TC1: even cycle (square) — bipartite
        //   0 - 1
        //   |   |
        //   3 - 2
        List<Integer>[] g1a = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}});
        List<Integer>[] g1b = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}});
        System.out.println("TC1 DFS: " + obj.isBipartiteDFS(4, g1a)); // true
        System.out.println("TC1 BFS: " + obj.isBipartiteBFS(4, g1b)); // true

        // TC2: odd cycle (triangle) — NOT bipartite
        //   0 - 1
        //    \ /
        //     2
        List<Integer>[] g2a = buildAdj(3, new int[][]{{0, 1}, {1, 2}, {2, 0}});
        List<Integer>[] g2b = buildAdj(3, new int[][]{{0, 1}, {1, 2}, {2, 0}});
        System.out.println("TC2 DFS: " + obj.isBipartiteDFS(3, g2a)); // false
        System.out.println("TC2 BFS: " + obj.isBipartiteBFS(3, g2b)); // false

        // TC3: disconnected — one component is NOT bipartite
        //   0 - 1   (bipartite)    2 - 3 - 4 - 2  (triangle, not bipartite)
        List<Integer>[] g3a = buildAdj(5, new int[][]{{0, 1}, {2, 3}, {3, 4}, {4, 2}});
        List<Integer>[] g3b = buildAdj(5, new int[][]{{0, 1}, {2, 3}, {3, 4}, {4, 2}});
        System.out.println("TC3 DFS: " + obj.isBipartiteDFS(5, g3a)); // false
        System.out.println("TC3 BFS: " + obj.isBipartiteBFS(5, g3b)); // false

        // TC4: simple path (no cycle) — always bipartite
        //   0 - 1 - 2 - 3
        List<Integer>[] g4a = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        List<Integer>[] g4b = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        System.out.println("TC4 DFS: " + obj.isBipartiteDFS(4, g4a)); // true
        System.out.println("TC4 BFS: " + obj.isBipartiteBFS(4, g4b)); // true

        // TC5: single node, no edges — bipartite (trivially)
        List<Integer>[] g5a = buildAdj(1, new int[][]{});
        List<Integer>[] g5b = buildAdj(1, new int[][]{});
        System.out.println("TC5 DFS: " + obj.isBipartiteDFS(1, g5a)); // true
        System.out.println("TC5 BFS: " + obj.isBipartiteBFS(1, g5b)); // true
    }
}
