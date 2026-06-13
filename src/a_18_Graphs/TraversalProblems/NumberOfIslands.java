package a_18_Graphs.TraversalProblems;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Number of Islands — 2D Grid Connected Components.
 * <p>
 * Two variants:
 * 4-directional: up / down / left / right          (standard)
 * 8-directional: + 4 diagonals (NE,NW,SE,SW)       (diagonal islands also connect)
 * <p>
 * Idea: For every unvisited '1' cell, increment island count and flood-fill
 * all connected '1' cells as visited.
 * <p>
 * Time  : O(R × C) — every cell visited at most once.
 * Space : O(R × C) — visited array + recursion/queue stack.
 */
public class NumberOfIslands {

    /*
     * Mental Model:
     *
     *   for each cell (r, c):
     *       if not visited and grid[r][c] == '1':
     *           islands++
     *           DFS to mark all connected '1' cells visited
     *
     *   DFS base cases (check at TOP of each call):
     *       out of bounds   → return
     *       already visited → return
     *       water ('0')     → return
     *
     *   4-dir calls: up / down / left / right
     *   8-dir calls: above 4 + NW / NE / SW / SE diagonals
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS 4-directional (standard)
    // ─────────────────────────────────────────────────────────────

    public int numIslandsDFS(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!visited[r][c] && grid[r][c] == '1') {
                    islands++;
                    dfs4(grid, visited, r, c);
                }
            }
        }
        return islands;
    }

    private void dfs4(char[][] grid, boolean[][] visited, int r, int c) {
        // base cases — check EVERYTHING here so call sites stay clean
        if (r < 0 || r >= grid.length) return;
        if (c < 0 || c >= grid[0].length) return;
        if (visited[r][c] || grid[r][c] == '0') return;

        visited[r][c] = true;

        dfs4(grid, visited, r - 1, c);     // up
        dfs4(grid, visited, r + 1, c);     // down
        dfs4(grid, visited, r, c - 1);     // left
        dfs4(grid, visited, r, c + 1);     // right
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: DFS 8-directional (includes diagonals)
    // ─────────────────────────────────────────────────────────────

    public int numIslandsDFS8(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!visited[r][c] && grid[r][c] == '1') {
                    islands++;
                    dfs8(grid, visited, r, c);
                }
            }
        }
        return islands;
    }

    private void dfs8(char[][] grid, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= grid.length) return;
        if (c < 0 || c >= grid[0].length) return;
        if (visited[r][c] || grid[r][c] == '0') return;

        visited[r][c] = true;

        dfs8(grid, visited, r - 1, c);     // up
        dfs8(grid, visited, r + 1, c);     // down
        dfs8(grid, visited, r, c - 1);     // left
        dfs8(grid, visited, r, c + 1);     // right
        dfs8(grid, visited, r - 1, c - 1); // NW diagonal
        dfs8(grid, visited, r - 1, c + 1); // NE diagonal
        dfs8(grid, visited, r + 1, c - 1); // SW diagonal
        dfs8(grid, visited, r + 1, c + 1); // SE diagonal
    }

    // ─────────────────────────────────────────────────────────────
    // Method 3: BFS 4-directional
    // ─────────────────────────────────────────────────────────────

    public int numIslandsBFS(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int islands = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (!visited[r][c] && grid[r][c] == '1') {
                    islands++;
                    bfs(grid, visited, r, c);
                }
            }
        }
        return islands;
    }

    private void bfs(char[][] grid, boolean[][] visited, int startR, int startC) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true; // mark before enqueue — prevents duplicates

        int[][] dirs = {
                {-1, 0}, {1, 0}, {0, -1}, {0, 1} // up, down, left, right
        };

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < grid.length && nc >= 0 && nc < grid[0].length
                        && !visited[nr][nc] && grid[nr][nc] == '1') {
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        NumberOfIslands obj = new NumberOfIslands();

        // TC1: 3 islands (4-dir)
        // 1 1 1 0 1
        // 1 0 0 0 0
        // 1 1 1 0 1
        // 0 0 0 1 1
        char[][] grid1 = {
                {'1', '1', '1', '0', '1'},
                {'1', '0', '0', '0', '0'},
                {'1', '1', '1', '0', '1'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("TC1 DFS4: " + obj.numIslandsDFS(grid1));  // 3
        System.out.println("TC1 BFS:  " + obj.numIslandsBFS(grid1));  // 3

        // TC2: 1 island (fully connected)
        char[][] grid2 = {{'1', '1'}, {'1', '1'}};
        System.out.println("TC2 DFS4: " + obj.numIslandsDFS(grid2));  // 1

        // TC3: 0 islands (all water)
        char[][] grid3 = {{'0', '0'}, {'0', '0'}};
        System.out.println("TC3 DFS4: " + obj.numIslandsDFS(grid3));  // 0

        // TC4: single cell
        char[][] grid4 = {{'1'}};
        System.out.println("TC4 DFS4: " + obj.numIslandsDFS(grid4));  // 1

        // TC5: diagonal '1's
        // 1 0 1
        // 0 1 0
        // 1 0 1
        // 4-dir → 5 islands (diagonals NOT connected)
        // 8-dir → 1 island  (diagonals ARE connected)
        char[][] grid5a = {{'1', '0', '1'}, {'0', '1', '0'}, {'1', '0', '1'}};
        char[][] grid5b = {{'1', '0', '1'}, {'0', '1', '0'}, {'1', '0', '1'}};
        System.out.println("TC5 DFS4: " + obj.numIslandsDFS(grid5a)); // 5
        System.out.println("TC5 DFS8: " + obj.numIslandsDFS8(grid5b)); // 1  ← key diff
    }
}
