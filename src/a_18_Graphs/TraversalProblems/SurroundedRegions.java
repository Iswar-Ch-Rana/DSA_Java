package a_18_Graphs.TraversalProblems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Surrounded Regions — capture all 'O' regions fully surrounded by 'X'.
 * <p>
 * Idea:
 * Step 1: Any 'O' connected to a boundary 'O' can never be captured — mark it safe ('#').
 * Step 2: Flood-fill (DFS/BFS) from every boundary 'O', marking reachable 'O's as '#'.
 * Step 3: Sweep the board — remaining 'O' → 'X' (captured), '#' → 'O' (restore safe).
 * <p>
 * Time  : O(R × C) — boundary scan O(R+C), fill + restore O(R×C).
 * Space : O(R × C) — recursion stack (DFS, risk of overflow on large grids) or queue (BFS).
 */
public class SurroundedRegions {

    /*
     * Mental Model:
     *
     *   flood-fill from every boundary 'O' → mark reachable 'O' as '#'
     *
     *   then single pass:
     *       'O'  → 'X'   (surrounded, capture it)
     *       '#'  → 'O'   (safe, restore it)
     *       'X'  → 'X'   (unchanged)
     *
     *   DFS base cases:
     *       out of bounds  → return
     *       not 'O'        → return (already '#' or is 'X')
     */

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS
    // ─────────────────────────────────────────────────────────────

    public char[][] fillDFS(char[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        // Step 1 & 2: scan only the 4 edges — O(R+C) instead of O(R×C)
        for (int r = 0; r < rows; r++) {
            if (mat[r][0] == 'O') dfs(mat, r, 0, rows, cols);
            if (mat[r][cols - 1] == 'O') dfs(mat, r, cols - 1, rows, cols);
        }
        for (int c = 0; c < cols; c++) {
            if (mat[0][c] == 'O') dfs(mat, 0, c, rows, cols);
            if (mat[rows - 1][c] == 'O') dfs(mat, rows - 1, c, rows, cols);
        }

        // Step 3: capture surrounded 'O', restore safe '#'
        restoreBoard(mat, rows, cols);
        return mat;
    }

    private void dfs(char[][] mat, int r, int c, int rows, int cols) {
        if (r < 0 || r >= rows) return;
        if (c < 0 || c >= cols) return;
        if (mat[r][c] != 'O') return;

        mat[r][c] = '#';

        dfs(mat, r - 1, c, rows, cols); // up
        dfs(mat, r + 1, c, rows, cols); // down
        dfs(mat, r, c - 1, rows, cols); // left
        dfs(mat, r, c + 1, rows, cols); // right
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS
    // ─────────────────────────────────────────────────────────────

    public char[][] fillBFS(char[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        // Step 1 & 2: scan only the 4 edges — O(R+C) instead of O(R×C)
        for (int r = 0; r < rows; r++) {
            if (mat[r][0] == 'O') bfs(mat, r, 0, rows, cols);
            if (mat[r][cols - 1] == 'O') bfs(mat, r, cols - 1, rows, cols);
        }
        for (int c = 0; c < cols; c++) {
            if (mat[0][c] == 'O') bfs(mat, 0, c, rows, cols);
            if (mat[rows - 1][c] == 'O') bfs(mat, rows - 1, c, rows, cols);
        }

        // Step 3: capture surrounded 'O', restore safe '#'
        restoreBoard(mat, rows, cols);
        return mat;
    }

    private void bfs(char[][] mat, int startR, int startC, int rows, int cols) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startR, startC});
        mat[startR][startC] = '#';

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];
            enqueue(mat, queue, r - 1, c, rows, cols); // up
            enqueue(mat, queue, r + 1, c, rows, cols); // down
            enqueue(mat, queue, r, c - 1, rows, cols); // left
            enqueue(mat, queue, r, c + 1, rows, cols); // right
        }
    }

    private void enqueue(char[][] mat, Queue<int[]> queue, int r, int c, int rows, int cols) {
        if (r >= 0 && r < rows && c >= 0 && c < cols && mat[r][c] == 'O') {
            mat[r][c] = '#';
            queue.offer(new int[]{r, c});
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Shared helpers
    // ─────────────────────────────────────────────────────────────

    private void restoreBoard(char[][] mat, int rows, int cols) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 'O') mat[r][c] = 'X';
                else if (mat[r][c] == '#') mat[r][c] = 'O';
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        SurroundedRegions obj = new SurroundedRegions();

        // TC1: standard — interior O's captured, boundary-connected O survives
        // X X X X        X X X X
        // X O O X   →   X X X X
        // X X O X        X X X X
        // X O X X        X O X X   ← bottom O touches boundary
        char[][] g1a = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        char[][] g1b = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        System.out.println("TC1 DFS: " + Arrays.deepToString(obj.fillDFS(g1a)));
        System.out.println("TC1 BFS: " + Arrays.deepToString(obj.fillBFS(g1b)));

        // TC2: all O's on boundary — nothing captured
        // O O
        // O O
        char[][] g2a = {{'O', 'O'}, {'O', 'O'}};
        char[][] g2b = {{'O', 'O'}, {'O', 'O'}};
        System.out.println("TC2 DFS: " + Arrays.deepToString(obj.fillDFS(g2a)));
        System.out.println("TC2 BFS: " + Arrays.deepToString(obj.fillBFS(g2b)));

        // TC3: no O's — board unchanged
        char[][] g3a = {{'X', 'X'}, {'X', 'X'}};
        char[][] g3b = {{'X', 'X'}, {'X', 'X'}};
        System.out.println("TC3 DFS: " + Arrays.deepToString(obj.fillDFS(g3a)));
        System.out.println("TC3 BFS: " + Arrays.deepToString(obj.fillBFS(g3b)));

        // TC4: single cell O surrounded — captured
        // X X X
        // X O X
        // X X X
        char[][] g4a = {{'X', 'X', 'X'}, {'X', 'O', 'X'}, {'X', 'X', 'X'}};
        char[][] g4b = {{'X', 'X', 'X'}, {'X', 'O', 'X'}, {'X', 'X', 'X'}};
        System.out.println("TC4 DFS: " + Arrays.deepToString(obj.fillDFS(g4a)));
        System.out.println("TC4 BFS: " + Arrays.deepToString(obj.fillBFS(g4b)));

        // TC5: O chain reaching boundary — entire chain survives
        // X O X
        // X O X
        // X O X
        char[][] g5a = {{'X', 'O', 'X'}, {'X', 'O', 'X'}, {'X', 'O', 'X'}};
        char[][] g5b = {{'X', 'O', 'X'}, {'X', 'O', 'X'}, {'X', 'O', 'X'}};
        System.out.println("TC5 DFS: " + Arrays.deepToString(obj.fillDFS(g5a)));
        System.out.println("TC5 BFS: " + Arrays.deepToString(obj.fillBFS(g5b)));
    }
}
