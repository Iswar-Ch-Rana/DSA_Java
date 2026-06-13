package a_18_Graphs.TraversalProblems;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * Flood Fill — paint all connected same-colour cells with a new colour.
 * <p>
 * Idea: From (sr, sc), DFS/BFS to all 4-directional neighbours that share
 * the original colour and repaint them with the new colour.
 * <p>
 * Key guard: if oldColor == newColor, return immediately (avoids infinite loop).
 * <p>
 * Time  : O(R × C) — every cell visited at most once.
 * Space : O(R × C) — recursion/queue stack.
 */
public class FloodFill {

    /*
     * Mental Model:
     *
     *   if oldColor == newColor → return (nothing to do)
     *
     *   DFS/BFS from (sr, sc):
     *       if out of bounds        → return
     *       if cell != oldColor     → return
     *       paint cell = newColor
     *       recurse 4 directions
     *
     *   No visited array needed — painting = marking visited.
     */

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    // ─────────────────────────────────────────────────────────────
    // Method 1: DFS — recursive flood-fill
    // ─────────────────────────────────────────────────────────────

    public int[][] floodFillDFS(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image; // guard: avoids infinite loop
        dfs(image, sr, sc, oldColor, newColor);
        return image;
    }

    private void dfs(int[][] image, int r, int c, int oldColor, int newColor) {
        if (r < 0 || r >= image.length) return;
        if (c < 0 || c >= image[0].length) return;
        if (image[r][c] != oldColor) return; // different colour or already painted

        image[r][c] = newColor; // paint = mark visited

        for (int[] dir : DIRECTIONS) {
            dfs(image, r + dir[0], c + dir[1], oldColor, newColor);
        }
    }

    // ─────────────────────────────────────────────────────────────
    // Method 2: BFS — iterative flood-fill
    // ─────────────────────────────────────────────────────────────

    public int[][] floodFillBFS(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{sr, sc});
        image[sr][sc] = newColor; // paint before enqueue — acts as visited mark

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            for (int[] dir : DIRECTIONS) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < image.length
                        && nc >= 0 && nc < image[0].length
                        && image[nr][nc] == oldColor) {
                    image[nr][nc] = newColor; // paint = mark visited
                    queue.offer(new int[]{nr, nc});
                }
            }
        }
        return image;
    }

    // ─────────────────────────────────────────────────────────────
    // Helper: deep copy image so each TC starts fresh
    // ─────────────────────────────────────────────────────────────

    private static int[][] copy(int[][] image) {
        return Arrays.stream(image).map(int[]::clone).toArray(int[][]::new);
    }

    private static void print(int[][] image) {
        for (int[] row : image) System.out.println(Arrays.toString(row));
        System.out.println();
    }

    // ─────────────────────────────────────────────────────────────
    // Main
    // ─────────────────────────────────────────────────────────────

    public static void main(String[] args) {
        FloodFill obj = new FloodFill();

        // TC1: standard fill — 1s connected to (1,1) become 2
        // [[1,1,1],[1,1,0],[1,0,1]]  sr=1,sc=1,color=2
        // expected: [[2,2,2],[2,2,0],[2,0,1]]
        int[][] img1 = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        System.out.println("TC1 DFS:");
        print(obj.floodFillDFS(copy(img1), 1, 1, 2));
        System.out.println("TC1 BFS:");
        print(obj.floodFillBFS(copy(img1), 1, 1, 2));

        // TC2: oldColor == newColor → no change
        // [[0,0,0],[0,0,0]]  sr=0,sc=0,color=0
        // expected: [[0,0,0],[0,0,0]]
        int[][] img2 = {{0, 0, 0}, {0, 0, 0}};
        System.out.println("TC2 DFS:");
        print(obj.floodFillDFS(copy(img2), 0, 0, 0));
        System.out.println("TC2 BFS:");
        print(obj.floodFillBFS(copy(img2), 0, 0, 0));

        // TC3: single cell
        // [[5]]  sr=0,sc=0,color=3  → [[3]]
        int[][] img3 = {{5}};
        System.out.println("TC3 DFS:");
        print(obj.floodFillDFS(copy(img3), 0, 0, 3));
        System.out.println("TC3 BFS:");
        print(obj.floodFillBFS(copy(img3), 0, 0, 3));

        // TC4: start on isolated cell — only that cell changes
        // [[1,0,1],[0,1,0],[1,0,1]]  sr=0,sc=0,color=2  → only (0,0) becomes 2
        int[][] img4 = {{1, 0, 1}, {0, 1, 0}, {1, 0, 1}};
        System.out.println("TC4 DFS:");
        print(obj.floodFillDFS(copy(img4), 0, 0, 2));
        System.out.println("TC4 BFS:");
        print(obj.floodFillBFS(copy(img4), 0, 0, 2));

        // TC5: entire grid same colour → all cells painted
        // [[1,1],[1,1]]  sr=0,sc=0,color=9  → [[9,9],[9,9]]
        int[][] img5 = {{1, 1}, {1, 1}};
        System.out.println("TC5 DFS:");
        print(obj.floodFillDFS(copy(img5), 0, 0, 9));
        System.out.println("TC5 BFS:");
        print(obj.floodFillBFS(copy(img5), 0, 0, 9));
    }
}
