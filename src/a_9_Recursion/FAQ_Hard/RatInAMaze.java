package a_9_Recursion.FAQ_Hard;

import java.util.ArrayList;
import java.util.List;

public class RatInAMaze {
    public static void main(String[] args) {
        RatInAMaze obj = new RatInAMaze();

        System.out.println(obj.findPath(new int[][]{{1, 0, 0, 0}, {1, 1, 0, 1}, {1, 1, 0, 0}, {0, 1, 1, 1}}));
        System.out.println(obj.findPath(new int[][]{{1, 0}, {1, 0}}));
        System.out.println(obj.findPath(new int[][]{{1, 0, 0}, {1, 1, 0}, {0, 1, 1}}));
    }

    public List<String> findPath(int[][] grid) {
        List<String> ans = new ArrayList<>();
        backtrack(grid, 0, 0, ans, new StringBuilder());
        return ans;
    }

    private void backtrack(int[][] grid, int row, int col, List<String> ans, StringBuilder path) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Base case invalid
        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] != 1)
            return;

        // Destination reached
        if (row == rows - 1 && col == cols - 1) {
            ans.add(path.toString());
            return;
        }

        // Mark visited
        grid[row][col] = -1;

        // Down (D)
        path.append('D');
        backtrack(grid, row + 1, col, ans, path);
        path.deleteCharAt(path.length() - 1);

        // Left (L)
        path.append('L');
        backtrack(grid, row, col - 1, ans, path);
        path.deleteCharAt(path.length() - 1);

        // Right (R)
        path.append('R');
        backtrack(grid, row, col + 1, ans, path);
        path.deleteCharAt(path.length() - 1);

        // Up (U)
        path.append('U');
        backtrack(grid, row - 1, col, ans, path);
        path.deleteCharAt(path.length() - 1);

        // Unmark
        grid[row][col] = 1;
    }
}
