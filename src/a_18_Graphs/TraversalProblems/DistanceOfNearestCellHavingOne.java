package a_18_Graphs.TraversalProblems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Distance of Nearest Cell Having 1 — Multi-source BFS.
 * <p>
 * Idea: Seed BFS from ALL 1-cells simultaneously.
 * Each 0-cell gets distance = level at which BFS first reaches it.
 * <p>
 * Time  : O(R × C) — every cell enqueued/dequeued once.
 * Space : O(R × C) — dist + visited + queue.
 */
public class DistanceOfNearestCellHavingOne {

    /*
     * Mental Model:
     *
     *   Step 1: enqueue all 1-cells (distance = 0), mark visited
     *   Step 2: BFS — for each neighbor:
     *               if not visited → dist[nr][nc] = dist[r][c] + 1, enqueue
     *   Result: dist[r][c] = shortest distance from (r,c) to nearest 1
     *
     *   Key: multi-source BFS guarantees shortest distance in one pass.
     */

    // ─────────────────────────────────────────────────────────────
    // Multi-source BFS
    // ─────────────────────────────────────────────────────────────

    public int[][] nearest(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        int[][] dist = new int[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();

        // Step 1: seed all 1-cells as sources (distance = 0)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    queue.offer(new int[]{r, c});
                    visited[r][c] = true;
                }
            }
        }

        // Step 2: BFS — spread distance outward
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            spread(grid, visited, dist, queue, r - 1, c, dist[r][c] + 1); // up
            spread(grid, visited, dist, queue, r + 1, c, dist[r][c] + 1); // down
            spread(grid, visited, dist, queue, r, c - 1, dist[r][c] + 1); // left
            spread(grid, visited, dist, queue, r, c + 1, dist[r][c] + 1); // right
        }

        return dist;
    }

    private void spread(int[][] grid, boolean[][] visited, int[][] dist,
                        Queue<int[]> queue, int r, int c, int newDist) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return;
        if (visited[r][c]) return;
        visited[r][c] = true;
        dist[r][c] = newDist;
        queue.offer(new int[]{r, c});
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        DistanceOfNearestCellHavingOne obj = new DistanceOfNearestCellHavingOne();

        // TC1: standard grid
        // 0 0 0
        // 0 1 0
        // 0 0 0
        // expected:
        // 2 1 2
        // 1 0 1
        // 2 1 2
        int[][] grid1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println("TC1:");
        for (int[] row : obj.nearest(grid1)) System.out.println(Arrays.toString(row));

        // TC2: multiple 1s
        // 1 0 0
        // 0 0 0
        // 0 0 1
        // expected:
        // 0 1 2
        // 1 2 1
        // 2 1 0
        int[][] grid2 = {{1, 0, 0}, {0, 0, 0}, {0, 0, 1}};
        System.out.println("TC2:");
        for (int[] row : obj.nearest(grid2)) System.out.println(Arrays.toString(row));

        // TC3: all 0s except top-left corner
        // 1 0 0
        // 0 0 0
        // 0 0 0
        int[][] grid3 = {{1, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        System.out.println("TC3:");
        for (int[] row : obj.nearest(grid3)) System.out.println(Arrays.toString(row));

        // TC4: all 1s → entire dist matrix = 0
        int[][] grid4 = {{1, 1}, {1, 1}};
        System.out.println("TC4:");
        for (int[] row : obj.nearest(grid4)) System.out.println(Arrays.toString(row));

        // TC5: single 1 in corner of 1×4 row → farthest cell = 3
        int[][] grid5 = {{1, 0, 0, 0}};
        System.out.println("TC5: " + Arrays.toString(obj.nearest(grid5)[0])); // [0, 1, 2, 3]
    }
}
