package a_18_Graphs.TheoryAndTraversal;

import java.util.ArrayList;
import java.util.List;

/**
 * DFS (Depth-First Search) on an undirected graph.
 * <p>
 * Idea: Go as deep as possible along one path before backtracking.
 * Uses recursion (implicit stack) or an explicit stack.
 * <p>
 * Time  : O(V + E) — each vertex and edge processed once.
 * Space : O(V)     — visited array + recursion stack.
 */
public class DFSTraversal {

    /*
     * Mental Model:
     *
     *   dfs(node):
     *       if visited → return
     *       mark visited, add to result
     *       for each neighbor:
     *           if not visited → dfs(neighbor)
     */

    // ─────────────────────────────────────────────────────────────
    // DFS — recursive
    // ─────────────────────────────────────────────────────────────
    public List<Integer> dfs(int start, List<List<Integer>> adj, int n) {
        boolean[] visited = new boolean[n];
        List<Integer> result = new ArrayList<>();
        dfsHelper(start, adj, visited, result);
        return result;
    }

    private void dfsHelper(int node, List<List<Integer>> adj, boolean[] visited, List<Integer> result) {
        visited[node] = true;
        result.add(node);                               // visit node on entry

        for (int neighbor : adj.get(node)) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, adj, visited, result);
            }
        }
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
        DFSTraversal sol = new DFSTraversal();

        // TC1: Standard connected graph
        // 0 - 1 - 2
        //     |
        //     3
        // DFS from 0: [0, 1, 2, 3]
        List<List<Integer>> adj1 = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {1, 3}});
        System.out.println("TC1: " + sol.dfs(0, adj1, 4)); // [0, 1, 2, 3]

        // TC2: Linear chain 0-1-2-3-4
        // DFS from 0: [0, 1, 2, 3, 4]
        List<List<Integer>> adj2 = buildAdj(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}});
        System.out.println("TC2: " + sol.dfs(0, adj2, 5)); // [0, 1, 2, 3, 4]

        // TC3: Star graph — center 0, leaves 1,2,3,4
        // DFS from 0: [0, 1, 2, 3, 4]
        List<List<Integer>> adj3 = buildAdj(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {0, 4}});
        System.out.println("TC3: " + sol.dfs(0, adj3, 5)); // [0, 1, 2, 3, 4]

        // TC4: Single node
        List<List<Integer>> adj4 = buildAdj(1, new int[][]{});
        System.out.println("TC4: " + sol.dfs(0, adj4, 1)); // [0]

        // TC5: DFS from non-zero start
        // 0-1-2-3, start=2 → [2, 1, 0, 3]
        List<List<Integer>> adj5 = buildAdj(4, new int[][]{{0, 1}, {1, 2}, {2, 3}});
        System.out.println("TC5: " + sol.dfs(2, adj5, 4)); // [2, 1, 0, 3]
    }
}

