package a_18_Graphs.TraversalProblems;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Number of Enclaves — count land cells that cannot reach the boundary.
 * <p>
 * Idea:
 * Step 1: DFS/BFS from every boundary land cell — mark all reachable land as visited.
 * Step 2: Count remaining unvisited land cells — those are the enclaves.
 * <p>
 * Time  : O(R × C) — each cell visited at most once.
 * Space : O(R × C) — visited array + recursion/queue stack.
 */
public class NumberOfEnclaves {

    /*
     * Mental Model:
     *
     *   Step 1 — flood-fill from all boundary '1' cells (mark them visited)
     *   Step 2 — count unvisited '1' cells = enclaves (can't reach boundary)
     *
     *   DFS base cases:
     *       out of bounds   → return
     *       already visited → return
     *       water (0)       → return
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS
    // ─────────────────────────────────────────────────────────────

    public int numberOfEnclavesDFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // Step 1: flood-fill from every boundary land cell.
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (isBoundary(r, c, rows, cols) && grid[r][c] == 1) {
                    dfs(grid, visited, r, c);
                }
            }
        }

        // Step 2: count remaining unvisited land cells
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) count++;
            }
        }
        return count;
    }

    private void dfs(int[][] grid, boolean[][] visited, int r, int c) {
        if (r < 0 || r >= grid.length) return;
        if (c < 0 || c >= grid[0].length) return;
        if (visited[r][c] || grid[r][c] == 0) return;

        visited[r][c] = true;

        dfs(grid, visited, r - 1, c); // up
        dfs(grid, visited, r + 1, c); // down
        dfs(grid, visited, r, c - 1); // left
        dfs(grid, visited, r, c + 1); // right
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS
    // ─────────────────────────────────────────────────────────────

    public int numberOfEnclavesBFS(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        // Step 1: BFS from every boundary land cell
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (isBoundary(r, c, rows, cols) && grid[r][c] == 1 && !visited[r][c]) {
                    bfs(grid, visited, r, c);
                }
            }
        }

        // Step 2: count remaining unvisited land cells
        int count = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1 && !visited[r][c]) count++;
            }
        }
        return count;
    }

    private void bfs(int[][] grid, boolean[][] visited, int startR, int startC) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];
            enqueue(grid, visited, queue, r - 1, c); // up
            enqueue(grid, visited, queue, r + 1, c); // down
            enqueue(grid, visited, queue, r, c - 1); // left
            enqueue(grid, visited, queue, r, c + 1); // right
        }
    }

    private void enqueue(int[][] grid, boolean[][] visited, Queue<int[]> queue, int r, int c) {
        if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length
                && !visited[r][c] && grid[r][c] == 1) {
            visited[r][c] = true;
            queue.offer(new int[]{r, c});
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Shared helpers
    // ─────────────────────────────────────────────────────────────

    private boolean isBoundary(int r, int c, int rows, int cols) {
        return r == 0 || r == rows - 1 || c == 0 || c == cols - 1;
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        NumberOfEnclaves obj = new NumberOfEnclaves();

        // TC1: standard case — 3 enclaves
        // 0 0 0 0
        // 1 0 1 0
        // 0 1 1 0
        // 0 0 0 0
        int[][] grid1a = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        int[][] grid1b = {{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        System.out.println("TC1 DFS: " + obj.numberOfEnclavesDFS(grid1a)); // 3
        System.out.println("TC1 BFS: " + obj.numberOfEnclavesBFS(grid1b)); // 3

        // TC2: all boundary land — no enclaves
        // 1 1 1
        // 1 0 1
        // 1 1 1
        int[][] grid2a = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] grid2b = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        System.out.println("TC2 DFS: " + obj.numberOfEnclavesDFS(grid2a)); // 0
        System.out.println("TC2 BFS: " + obj.numberOfEnclavesBFS(grid2b)); // 0

        // TC3: all water — 0 enclaves
        int[][] grid3a = {{0, 0}, {0, 0}};
        int[][] grid3b = {{0, 0}, {0, 0}};
        System.out.println("TC3 DFS: " + obj.numberOfEnclavesDFS(grid3a)); // 0
        System.out.println("TC3 BFS: " + obj.numberOfEnclavesBFS(grid3b)); // 0

        // TC4: interior land connected to boundary — 0 enclaves
        // 0 1 0
        // 1 1 0
        // 0 0 0
        int[][] grid4a = {{0, 1, 0}, {1, 1, 0}, {0, 0, 0}};
        int[][] grid4b = {{0, 1, 0}, {1, 1, 0}, {0, 0, 0}};
        System.out.println("TC4 DFS: " + obj.numberOfEnclavesDFS(grid4a)); // 0
        System.out.println("TC4 BFS: " + obj.numberOfEnclavesBFS(grid4b)); // 0

        // TC5: interior land fully surrounded — 1 enclave
        // 0 0 0 0
        // 0 1 0 0
        // 0 0 0 0
        int[][] grid5a = {{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
        int[][] grid5b = {{0, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 0, 0}};
        System.out.println("TC5 DFS: " + obj.numberOfEnclavesDFS(grid5a)); // 1
        System.out.println("TC5 BFS: " + obj.numberOfEnclavesBFS(grid5b)); // 1
    }
}
