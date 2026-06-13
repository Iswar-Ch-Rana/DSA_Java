package a_18_Graphs.TheoryAndTraversal;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Count Connected Components in an Undirected Graph.
 * <p>
 * Idea: iterate over all unvisited nodes; each unvisited node starts a new component.
 * Run DFS (or BFS) from it to mark all reachable nodes as visited.
 * <p>
 * Time  : O(V + E) — every vertex and edge visited once.
 * Space : O(V + E) — adjacency list + visited array + recursion/queue.
 */
public class ConnectedComponents {

    /*
     * Mental Model:
     *
     *   for each node 0..V-1:
     *       if not visited:
     *           components++
     *           DFS/BFS to mark entire component visited
     *
     *   return components
     */

    // ─────────────────────────────────────────────────────────────
    // Public API
    // ─────────────────────────────────────────────────────────────

    public int findNumberOfComponents(int v, List<List<Integer>> edges) {
        List<List<Integer>> adj = buildGraph(v, edges);
        return countComponentsDFS(v, adj);
    }

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS — recursive flood-fill per component
    // ─────────────────────────────────────────────────────────────

    int countComponentsDFS(int v, List<List<Integer>> adj) {
        boolean[] visited = new boolean[v];
        int components = 0;

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                components++;
                dfs(adj, i, visited); // mark entire component
            }
        }
        return components;
    }

    private void dfs(List<List<Integer>> adj, int node, boolean[] visited) {
        if (visited[node]) return;
        visited[node] = true;

        for (int neighbor : adj.get(node)) {
            dfs(adj, neighbor, visited);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS — iterative flood-fill per component
    // ─────────────────────────────────────────────────────────────

    int countComponentsBFS(int v, List<List<Integer>> adj) {
        boolean[] visited = new boolean[v];
        int components = 0;

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                components++;
                bfs(adj, i, visited); // mark entire component
            }
        }
        return components;
    }

    private void bfs(List<List<Integer>> adj, int start, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true; // mark before enqueue to prevent duplicates

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : adj.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Graph builder — undirected adjacency list
    // ─────────────────────────────────────────────────────────────

    private List<List<Integer>> buildGraph(int v, List<List<Integer>> edges) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < v; i++) adj.add(new ArrayList<>());

        for (List<Integer> edge : edges) {
            int u = edge.get(0), w = edge.get(1);
            adj.get(u).add(w); // undirected → both directions
            adj.get(w).add(u);
        }
        return adj;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        ConnectedComponents obj = new ConnectedComponents();

        // TC1: 2 components
        // 0-1-2   3-4
        // edges: (0,1),(1,2),(3,4)  → 2 components
        List<List<Integer>> edges1 = List.of(
                List.of(0, 1), List.of(1, 2), List.of(3, 4)
        );
        List<List<Integer>> adj1 = obj.buildGraph(5, edges1);
        System.out.println("TC1 DFS: " + obj.countComponentsDFS(5, adj1)); // 2
        System.out.println("TC1 BFS: " + obj.countComponentsBFS(5, adj1)); // 2

        // TC2: all connected (1 component)
        // 0-1-2-3-4
        List<List<Integer>> edges2 = List.of(
                List.of(0, 1), List.of(1, 2), List.of(2, 3), List.of(3, 4)
        );
        List<List<Integer>> adj2 = obj.buildGraph(5, edges2);
        System.out.println("TC2 DFS: " + obj.countComponentsDFS(5, adj2)); // 1
        System.out.println("TC2 BFS: " + obj.countComponentsBFS(5, adj2)); // 1

        // TC3: all isolated (5 components)
        List<List<Integer>> edges3 = List.of();
        List<List<Integer>> adj3 = obj.buildGraph(5, edges3);
        System.out.println("TC3 DFS: " + obj.countComponentsDFS(5, adj3)); // 5
        System.out.println("TC3 BFS: " + obj.countComponentsBFS(5, adj3)); // 5

        // TC4: single node, no edges (1 component)
        List<List<Integer>> adj4 = obj.buildGraph(1, List.of());
        System.out.println("TC4 DFS: " + obj.countComponentsDFS(1, adj4)); // 1
        System.out.println("TC4 BFS: " + obj.countComponentsBFS(1, adj4)); // 1

        // TC5: 3 components, mixed sizes
        // 0-1  2-3-4  5
        List<List<Integer>> edges5 = List.of(
                List.of(0, 1), List.of(2, 3), List.of(3, 4)
        );
        List<List<Integer>> adj5 = obj.buildGraph(6, edges5);
        System.out.println("TC5 DFS: " + obj.countComponentsDFS(6, adj5)); // 3
        System.out.println("TC5 BFS: " + obj.countComponentsBFS(6, adj5)); // 3
    }
}
