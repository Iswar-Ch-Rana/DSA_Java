package a_18_Graphs.Hard_Problems;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Course Schedule I — can all courses be finished given prerequisites?
 * <p>
 * prerequisites[i] = [a, b] means "take b before a" → directed edge b → a.
 * All courses can be finished ⟺ the prerequisite graph has no cycle (it's a DAG).
 * <p>
 * Two approaches:
 * DFS  : detect a cycle using visited + inPath arrays. Cycle → cannot finish.
 * BFS  : Kahn's algorithm — if topological sort processes all N courses, no cycle exists.
 * <p>
 * Time  : O(V + E) — each course and prerequisite processed at most once.
 * Space : O(V + E) — adjacency list + visited/inPath or in-degree array.
 */
public class CourseScheduleI {

    /*
     * Mental Model:
     *
     *   Build directed graph: prerequisite b → course a  (for each [a, b])
     *
     *   DFS — cycle detection with inPath:
     *       inPath[node] = true while exploring, false on clean backtrack
     *       if neighbour is inPath → cycle found → return false
     *
     *   BFS — Kahn's:
     *       compute in-degree for every node
     *       enqueue nodes with in-degree 0 (no unmet prerequisites)
     *       each time a course is "taken": decrement its dependents' in-degree
     *       if all N courses processed → no cycle → can finish
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS (cycle detection via inPath)
    // ─────────────────────────────────────────────────────────────

    public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = buildAdj(numCourses, prerequisites);
        boolean[] visited = new boolean[numCourses];
        boolean[] inPath = new boolean[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && hasCycleDFS(i, adj, visited, inPath)) return false;
        }
        return true;
    }

    private boolean hasCycleDFS(int node, List<Integer>[] adj, boolean[] visited, boolean[] inPath) {
        visited[node] = true;
        inPath[node] = true;

        for (int neighbour : adj[node]) {
            if (inPath[neighbour]) return true;                          // back-edge → cycle
            if (!visited[neighbour] && hasCycleDFS(neighbour, adj, visited, inPath)) return true;
        }

        inPath[node] = false; // backtrack
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS — Kahn's Algorithm
    // ─────────────────────────────────────────────────────────────

    public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = buildAdj(numCourses, prerequisites);

        int[] indegree = new int[numCourses];
        for (int u = 0; u < numCourses; u++) {
            for (int v : adj[u]) indegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
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

        return processed == numCourses; // all courses taken → no cycle
    }

    // ─────────────────────────────────────────────────────────────
    // Shared helpers
    // ─────────────────────────────────────────────────────────────

    @SuppressWarnings("unchecked")
    private static List<Integer>[] buildAdj(int V, int[][] prerequisites) {
        List<Integer>[] adj = new ArrayList[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        for (int[] pre : prerequisites) adj[pre[1]].add(pre[0]); // b → a
        return adj;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        CourseScheduleI obj = new CourseScheduleI();

        // TC1: cycle exists — cannot finish
        //   prereqs: [0,1],[3,2],[1,3],[3,0]  →  edges 1→0, 2→3, 3→1, 0→3
        //   cycle: 0→3→1→0
        System.out.println("TC1 DFS: " + obj.canFinishDFS(4, new int[][]{{0, 1}, {3, 2}, {1, 3}, {3, 0}})); // false
        System.out.println("TC1 BFS: " + obj.canFinishBFS(4, new int[][]{{0, 1}, {3, 2}, {1, 3}, {3, 0}})); // false

        // TC2: no cycle — linear chain, can finish
        //   prereqs: [1,0],[2,1],[3,2]  →  edges 0→1→2→3
        System.out.println("TC2 DFS: " + obj.canFinishDFS(4, new int[][]{{1, 0}, {2, 1}, {3, 2}})); // true
        System.out.println("TC2 BFS: " + obj.canFinishBFS(4, new int[][]{{1, 0}, {2, 1}, {3, 2}})); // true

        // TC3: two courses, one prerequisite — can finish
        //   prereqs: [1,0]  →  take 0 first, then 1
        System.out.println("TC3 DFS: " + obj.canFinishDFS(2, new int[][]{{1, 0}})); // true
        System.out.println("TC3 BFS: " + obj.canFinishBFS(2, new int[][]{{1, 0}})); // true

        // TC4: two courses, circular dependency — cannot finish
        //   prereqs: [1,0],[0,1]  →  cycle 0↔1
        System.out.println("TC4 DFS: " + obj.canFinishDFS(2, new int[][]{{1, 0}, {0, 1}})); // false
        System.out.println("TC4 BFS: " + obj.canFinishBFS(2, new int[][]{{1, 0}, {0, 1}})); // false

        // TC5: no prerequisites — all courses independent, can always finish
        System.out.println("TC5 DFS: " + obj.canFinishDFS(3, new int[][]{})); // true
        System.out.println("TC5 BFS: " + obj.canFinishBFS(3, new int[][]{})); // true
    }
}
