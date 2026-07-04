package a_18_Graphs.Hard_Problems;

import java.util.*;

/**
 * Course Schedule II — return the order to finish all courses, or [] if impossible.
 * <p>
 * prerequisites[i] = [a, b] means "take b before a" → directed edge b → a.
 * Valid order exists ⟺ the prerequisite graph is a DAG (no cycle).
 * This is Course Schedule I extended: instead of true/false, return the actual order.
 * <p>
 * Two approaches:
 * DFS  : post-order push to stack (same as topological sort DFS) + inPath cycle detection.
 * If cycle detected → return []. Otherwise pop stack = valid course order.
 * BFS  : Kahn's algorithm — collect courses as they're "taken".
 * If processed < N → cycle → return []. Otherwise the collection IS the order.
 * <p>
 * Time  : O(V + E) — each course and prerequisite processed at most once.
 * Space : O(V + E) — adjacency list + stack / queue + visited arrays.
 */
public class CourseScheduleII {

    /*
     * Mental Model:
     *
     *   Build directed graph: prerequisite b → course a  (for each [a, b])
     *
     *   DFS — topological sort with cycle guard:
     *       inPath[node] = true while exploring, false on clean backtrack
     *       push node to stack AFTER all neighbours explored (post-order)
     *       if inPath[neighbour] → cycle → abort with []
     *       pop stack → valid order
     *
     *   BFS — Kahn's:
     *       enqueue courses with in-degree 0 (no unmet prerequisites)
     *       each dequeued course is "taken" — decrement its dependents' in-degree
     *       collect dequeued order → valid course sequence
     *       if collected < N → some courses stuck in cycle → return []
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS (post-order stack + inPath cycle guard)
    // ─────────────────────────────────────────────────────────────

    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = buildAdj(numCourses, prerequisites);
        boolean[] visited = new boolean[numCourses];
        boolean[] inPath = new boolean[numCourses];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i] && hasCycle(i, adj, visited, inPath, stack)) return new int[0];
        }

        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) result[i] = stack.pop();
        return result;
    }

    private boolean hasCycle(int node, List<Integer>[] adj,
                             boolean[] visited, boolean[] inPath, Deque<Integer> stack) {
        visited[node] = true;
        inPath[node] = true;

        for (int neighbour : adj[node]) {
            if (inPath[neighbour]) return true;                           // back-edge → cycle
            if (!visited[neighbour] && hasCycle(neighbour, adj, visited, inPath, stack)) return true;
        }

        inPath[node] = false;
        stack.push(node); // post-order: all dependents already on stack
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS — Kahn's Algorithm
    // ─────────────────────────────────────────────────────────────

    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = buildAdj(numCourses, prerequisites);

        int[] indegree = new int[numCourses];
        for (int u = 0; u < numCourses; u++) {
            for (int v : adj[u]) indegree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int[] result = new int[numCourses];
        int idx = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result[idx++] = node;
            for (int neighbour : adj[node]) {
                if (--indegree[neighbour] == 0) queue.offer(neighbour);
            }
        }

        return idx == numCourses ? result : new int[0]; // cycle → some courses never processed
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
        CourseScheduleII obj = new CourseScheduleII();

        // TC1: linear chain — only one valid order
        //   prereqs: [1,0],[2,1],[3,2]  →  0→1→2→3
        System.out.println("TC1 DFS: " + Arrays.toString(obj.findOrderDFS(4, new int[][]{{1, 0}, {2, 1}, {3, 2}}))); // [0,1,2,3]
        System.out.println("TC1 BFS: " + Arrays.toString(obj.findOrderBFS(4, new int[][]{{1, 0}, {2, 1}, {3, 2}}))); // [0,1,2,3]

        // TC2: cycle exists — impossible
        //   prereqs: [0,1],[3,2],[1,3],[3,0]  →  cycle 0→3→1→0
        System.out.println("TC2 DFS: " + Arrays.toString(obj.findOrderDFS(4, new int[][]{{0, 1}, {3, 2}, {1, 3}, {3, 0}}))); // []
        System.out.println("TC2 BFS: " + Arrays.toString(obj.findOrderBFS(4, new int[][]{{0, 1}, {3, 2}, {1, 3}, {3, 0}}))); // []

        // TC3: two courses, one prerequisite
        //   prereqs: [1,0]  →  take 0 then 1
        System.out.println("TC3 DFS: " + Arrays.toString(obj.findOrderDFS(2, new int[][]{{1, 0}}))); // [0,1]
        System.out.println("TC3 BFS: " + Arrays.toString(obj.findOrderBFS(2, new int[][]{{1, 0}}))); // [0,1]

        // TC4: no prerequisites — any order valid; both should return all 3 courses
        System.out.println("TC4 DFS: " + Arrays.toString(obj.findOrderDFS(3, new int[][]{}))); // [0,1,2] or any permutation
        System.out.println("TC4 BFS: " + Arrays.toString(obj.findOrderBFS(3, new int[][]{}))); // [0,1,2] or any permutation

        // TC5: two independent chains — 0→1 and 2→3
        //   prereqs: [1,0],[3,2]
        //   valid orders: [0,2,1,3] or [0,1,2,3] or [2,0,3,1] etc.
        System.out.println("TC5 DFS: " + Arrays.toString(obj.findOrderDFS(4, new int[][]{{1, 0}, {3, 2}}))); // valid order
        System.out.println("TC5 BFS: " + Arrays.toString(obj.findOrderBFS(4, new int[][]{{1, 0}, {3, 2}}))); // valid order
    }
}
