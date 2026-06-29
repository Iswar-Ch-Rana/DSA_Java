package a_18_Graphs.TraversalProblems;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Number of Distinct Islands — count islands with unique shapes.
 * <p>
 * Idea:
 * Step 1: Scan the grid; for each unvisited land cell, flood-fill the whole island.
 * Step 2: During fill, record each cell's offset from the island's anchor (first cell found).
 * Step 3: Encode offsets as a string fingerprint — same shape → same string → HashSet deduplicates.
 * <p>
 * Key insight: DFS/BFS always visits in a fixed direction order, so two islands with the
 * same shape always produce the same offset sequence → identical fingerprint.
 * <p>
 * Time  : O(R × C) — each cell visited at most once.
 * Space : O(R × C) — visited array + shape strings stored in the set.
 */
public class NumberOfDistinctIslands {

    /*
     * Mental Model:
     *
     *   anchor = first cell of island (row-major scan, so always top-left cell)
     *   for every cell visited during fill:
     *       record (row - anchorRow, col - anchorCol) as "dr,dc"
     *   join all offsets with "|" → one string per island
     *
     *   "1 0 0"         "0 0 1"
     *   "1 0 0"    vs   "0 0 1"   ← different position, same shape → same fingerprint
     *
     *   HashSet size at the end = number of distinct shapes
     *
     *   DFS base cases:
     *       out of bounds   → return
     *       already visited → return
     *       water (0)       → return
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS
    // ─────────────────────────────────────────────────────────────

    public int countDistinctIslandsDFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Set<String> shapes = new HashSet<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    StringBuilder shape = new StringBuilder();
                    dfs(grid, visited, r, c, r, c, shape);
                    shapes.add(shape.toString());
                }
            }
        }
        return shapes.size();
    }

    private void dfs(int[][] grid, boolean[][] visited,
                     int r, int c, int anchorR, int anchorC,
                     StringBuilder shape) {
        if (r < 0 || r >= grid.length) return;
        if (c < 0 || c >= grid[0].length) return;
        if (visited[r][c] || grid[r][c] == 0) return;

        visited[r][c] = true;
        shape.append(r - anchorR).append(',').append(c - anchorC).append('|');

        dfs(grid, visited, r - 1, c, anchorR, anchorC, shape); // up
        dfs(grid, visited, r + 1, c, anchorR, anchorC, shape); // down
        dfs(grid, visited, r, c - 1, anchorR, anchorC, shape); // left
        dfs(grid, visited, r, c + 1, anchorR, anchorC, shape); // right
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS
    // ─────────────────────────────────────────────────────────────

    public int countDistinctIslandsBFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        Set<String> shapes = new HashSet<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) {
                    shapes.add(bfs(grid, visited, r, c));
                }
            }
        }
        return shapes.size();
    }

    private String bfs(int[][] grid, boolean[][] visited, int startR, int startC) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        StringBuilder shape = new StringBuilder();

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];
            shape.append(r - startR).append(',').append(c - startC).append('|');

            enqueue(grid, visited, queue, r - 1, c); // up
            enqueue(grid, visited, queue, r + 1, c); // down
            enqueue(grid, visited, queue, r, c - 1); // left
            enqueue(grid, visited, queue, r, c + 1); // right
        }
        return shape.toString();
    }

    private void enqueue(int[][] grid, boolean[][] visited, Queue<int[]> queue, int r, int c) {
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length
                && !visited[r][c] && grid[r][c] == 1) {
            visited[r][c] = true;
            queue.offer(new int[]{r, c});
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        NumberOfDistinctIslands obj = new NumberOfDistinctIslands();

        // TC1: 2 distinct shapes — L-shape and vertical bar
        // 1 1 0 0
        // 1 0 0 1
        // 0 0 0 1
        // 0 0 0 1
        int[][] g1a = {{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        int[][] g1b = {{1, 1, 0, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        System.out.println("TC1 DFS: " + obj.countDistinctIslandsDFS(g1a)); // 2
        System.out.println("TC1 BFS: " + obj.countDistinctIslandsBFS(g1b)); // 2

        // TC2: same shape, different positions → 1 distinct island
        // 1 0 1
        // 1 0 1
        int[][] g2a = {{1, 0, 1}, {1, 0, 1}};
        int[][] g2b = {{1, 0, 1}, {1, 0, 1}};
        System.out.println("TC2 DFS: " + obj.countDistinctIslandsDFS(g2a)); // 1
        System.out.println("TC2 BFS: " + obj.countDistinctIslandsBFS(g2b)); // 1

        // TC3: 3 different shapes
        // 1 1 0 1
        // 1 0 0 1
        // 0 0 1 0
        int[][] g3a = {{1, 1, 0, 1}, {1, 0, 0, 1}, {0, 0, 1, 0}};
        int[][] g3b = {{1, 1, 0, 1}, {1, 0, 0, 1}, {0, 0, 1, 0}};
        System.out.println("TC3 DFS: " + obj.countDistinctIslandsDFS(g3a)); // 3
        System.out.println("TC3 BFS: " + obj.countDistinctIslandsBFS(g3b)); // 3

        // TC4: all isolated single cells → 1 distinct shape
        // 1 0 1
        // 0 0 0
        // 1 0 1
        int[][] g4a = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        int[][] g4b = {{1, 0, 1}, {0, 0, 0}, {1, 0, 1}};
        System.out.println("TC4 DFS: " + obj.countDistinctIslandsDFS(g4a)); // 1
        System.out.println("TC4 BFS: " + obj.countDistinctIslandsBFS(g4b)); // 1

        // TC5: no land — 0 islands
        int[][] g5a = {{0, 0}, {0, 0}};
        int[][] g5b = {{0, 0}, {0, 0}};
        System.out.println("TC5 DFS: " + obj.countDistinctIslandsDFS(g5a)); // 0
        System.out.println("TC5 BFS: " + obj.countDistinctIslandsBFS(g5b)); // 0
    }
}
