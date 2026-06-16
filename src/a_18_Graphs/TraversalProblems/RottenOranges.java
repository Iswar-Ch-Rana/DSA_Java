package a_18_Graphs.TraversalProblems;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Rotten Oranges — Multi-source BFS to find minimum time to rot all fresh oranges.
 * <p>
 * Idea:
 * Step 1: Enqueue ALL rotten oranges at once (multi-source BFS start), count fresh.
 * Step 2: BFS level by level — each level = 1 minute; rot adjacent fresh oranges.
 * Step 3: If fresh > 0 after BFS → unreachable fresh exists → return -1.
 * <p>
 * Time  : O(R × C) — every cell processed at most once.
 * Space : O(R × C) — queue holds all rotten cells in worst case.
 */
public class RottenOranges {

    /*
     * Mental Model:
     *
     *   enqueue all '2' cells upfront  (multi-source BFS)
     *   count all '1' cells as fresh
     *
     *   while queue not empty AND fresh > 0:
     *       process one full level (= 1 minute)
     *       for each rotten cell → rot fresh neighbours → enqueue them
     *       minutes++
     *
     *   return fresh == 0 ? minutes : -1
     *
     *   Key: minutes increments AFTER each level, so no off-by-one
     */

    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        int fresh = 0;
        int minutes = 0;

        // Step 1: seed queue with all rotten oranges, count fresh
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) queue.offer(new int[]{r, c});
                else if (grid[r][c] == 1) fresh++;
            }
        }

        // Step 2: BFS — each iteration = 1 minute
        while (!queue.isEmpty() && fresh > 0) {
            int levelSize = queue.size();

            while (levelSize-- > 0) {
                int[] cell = queue.poll();
                int r = cell[0], c = cell[1];

                // up
                if (r - 1 >= 0 && grid[r - 1][c] == 1) {
                    grid[r - 1][c] = 2;
                    fresh--;
                    queue.offer(new int[]{r - 1, c});
                }
                // down
                if (r + 1 < rows && grid[r + 1][c] == 1) {
                    grid[r + 1][c] = 2;
                    fresh--;
                    queue.offer(new int[]{r + 1, c});
                }
                // left
                if (c - 1 >= 0 && grid[r][c - 1] == 1) {
                    grid[r][c - 1] = 2;
                    fresh--;
                    queue.offer(new int[]{r, c - 1});
                }
                // right
                if (c + 1 < cols && grid[r][c + 1] == 1) {
                    grid[r][c + 1] = 2;
                    fresh--;
                    queue.offer(new int[]{r, c + 1});
                }
            }

            minutes++;
        }

        // Step 3: leftover fresh = isolated → impossible
        return fresh == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        RottenOranges obj = new RottenOranges();

        // TC1: standard — 4 minutes
        // 2 1 1
        // 1 1 0
        // 0 1 1
        System.out.println("TC1: " + obj.orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}})); // 4

        // TC2: isolated fresh — impossible (-1)
        // 2 1 1
        // 0 1 1
        // 1 0 1   ← bottom-left 1 is isolated
        System.out.println("TC2: " + obj.orangesRotting(new int[][]{{2, 1, 1}, {0, 1, 1}, {1, 0, 1}})); // -1

        // TC3: no fresh oranges — 0 minutes
        // 0 2
        System.out.println("TC3: " + obj.orangesRotting(new int[][]{{0, 2}})); // 0

        // TC4: all fresh, no rotten — impossible (-1)
        System.out.println("TC4: " + obj.orangesRotting(new int[][]{{1, 1}, {1, 1}})); // -1

        // TC5: multi-source BFS — 2 rotten spread simultaneously
        // 2 1 1 2
        // expects 1 minute (both spread inward at same time)
        System.out.println("TC5: " + obj.orangesRotting(new int[][]{{2, 1, 1, 2}})); // 1
    }
}
