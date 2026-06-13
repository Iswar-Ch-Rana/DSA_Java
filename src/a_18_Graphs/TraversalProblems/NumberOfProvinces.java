package a_18_Graphs.TraversalProblems;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Number of Provinces (Connected Components in Adjacency Matrix).
 * <p>
 * Identical to "Count Connected Components" but input is an adjacency matrix
 * instead of an adjacency list. adj[i][j] == 1 means i and j are directly connected.
 * <p>
 * Idea: For each unvisited node, increment province count and DFS/BFS to mark
 * all nodes in the same province as visited.
 * <p>
 * Time  : O(V²) — adjacency matrix requires scanning entire row per node.
 * Space : O(V)  — visited array + recursion/queue stack.
 */
public class NumberOfProvinces {

    /*
     * Mental Model:
     *
     *   for each node 0..V-1:
     *       if not visited:
     *           provinces++
     *           DFS/BFS to mark all connected nodes visited
     *
     *   return provinces
     *
     *   Key diff vs adjacency list: inner loop scans entire row (O(V)) not just neighbors.
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS — recursive flood-fill
    // ─────────────────────────────────────────────────────────────

    public int numProvincesDFS(int[][] adj) {
        int v = adj.length;
        boolean[] visited = new boolean[v];
        int provinces = 0;

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                provinces++;
                dfs(adj, i, visited);
            }
        }
        return provinces;
    }

    private void dfs(int[][] adj, int node, boolean[] visited) {
        visited[node] = true;

        for (int i = 0; i < adj.length; i++) {
            if (adj[node][i] == 1 && !visited[i]) { // connected and not yet visited
                dfs(adj, i, visited);
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS — iterative flood-fill
    // ─────────────────────────────────────────────────────────────

    public int numProvincesBFS(int[][] adj) {
        int v = adj.length;
        boolean[] visited = new boolean[v];
        int provinces = 0;

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                provinces++;
                bfs(adj, i, visited);
            }
        }
        return provinces;
    }

    private void bfs(int[][] adj, int start, boolean[] visited) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start] = true; // mark before enqueue — prevents duplicate entries

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 0; i < adj.length; i++) {
                if (adj[node][i] == 1 && !visited[i]) {
                    visited[i] = true;
                    queue.offer(i);
                }
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        NumberOfProvinces obj = new NumberOfProvinces();

        // TC1: 2 provinces
        // 0-1   2 (isolated)
        // adj = [[1,1,0],[1,1,0],[0,0,1]]
        int[][] adj1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println("TC1 DFS: " + obj.numProvincesDFS(adj1)); // 2
        System.out.println("TC1 BFS: " + obj.numProvincesBFS(adj1)); // 2

        // TC2: 3 provinces (all isolated)
        // adj = [[1,0,0],[0,1,0],[0,0,1]]
        int[][] adj2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println("TC2 DFS: " + obj.numProvincesDFS(adj2)); // 3
        System.out.println("TC2 BFS: " + obj.numProvincesBFS(adj2)); // 3

        // TC3: 1 province (all connected)
        // adj = [[1,1,1],[1,1,1],[1,1,1]]
        int[][] adj3 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        System.out.println("TC3 DFS: " + obj.numProvincesDFS(adj3)); // 1
        System.out.println("TC3 BFS: " + obj.numProvincesBFS(adj3)); // 1

        // TC4: single node
        int[][] adj4 = {{1}};
        System.out.println("TC4 DFS: " + obj.numProvincesDFS(adj4)); // 1
        System.out.println("TC4 BFS: " + obj.numProvincesBFS(adj4)); // 1

        // TC5: 2 provinces — chain 0-1-2 and isolated 3
        // adj[i][i] = 1 (self-loop by definition)
        int[][] adj5 = {
                {1, 1, 0, 0},
                {1, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 1}
        };
        System.out.println("TC5 DFS: " + obj.numProvincesDFS(adj5)); // 2
        System.out.println("TC5 BFS: " + obj.numProvincesBFS(adj5)); // 2
    }
}
