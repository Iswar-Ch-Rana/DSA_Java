package a_9_Recursion.FAQ_Hard;

/**
 * 🧩 Problem: Determine if a word exists in a 2D board of characters.
 * <p>
 * Given an m x n grid of characters and a string word, return true if word exists
 * in the grid. The word can be constructed from letters of sequentially adjacent cells,
 * where adjacent cells are horizontally or vertically neighboring. The same letter cell
 * may not be used more than once in a single search path.
 * <p>
 * ⏱️ Time: O(M × N × 4^L) where M = rows, N = cols, L = word length
 * <br>
 * Space: O(L) for recursion stack depth + O(M × N) for visited array
 * <p>
 * 🐢 Brute Force:
 * <ul>
 *   <li>Try starting from every cell and explore all possible paths without pruning</li>
 *   <li>No early termination or first character matching optimization</li>
 *   <li>Same complexity but more unnecessary explorations</li>
 * </ul>
 * <p>
 * ⚡ Optimized (DFS + Backtracking with Visited Tracking):
 * <ul>
 *   <li>Iterate through all cells, starting DFS only if first character matches</li>
 *   <li>Use visited[][] array to mark cells in current path (prevents reuse)</li>
 *   <li>At each step, try all 4 directions (up, down, left, right)</li>
 *   <li>Base case success: When index == word.length, entire word matched</li>
 *   <li>Base case failure: Out of bounds, already visited, or character mismatch</li>
 *   <li>Backtrack: After exploring all directions, unmark visited[i][j] = false</li>
 *   <li>Early pruning: Only start DFS if board[i][j] == word.charAt(0)</li>
 * </ul>
 * <p>
 * 🧠 Key Concepts: DFS, Backtracking, Grid Traversal, Visited Tracking, Path Exploration
 * <p>
 * 🎯 Interview Tips:
 * <ul>
 *   <li>Explain why visited array is needed: prevents using same cell twice in one path</li>
 *   <li>Mention backtracking is crucial: must unmark cells to allow use in other paths</li>
 *   <li>Time complexity: 4^L because at each step we try 4 directions, up to L depth</li>
 *   <li>Optimization: Check first character before starting DFS reduces unnecessary calls</li>
 *   <li>Follow-up: Word Search II (multiple words using Trie), or modify in-place without visited array</li>
 * </ul>
 */
public class WordSearch {

    public static void main(String[] args) {
        WordSearch obj = new WordSearch();

        System.out.println(obj.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCCED"));  // true
        System.out.println(obj.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "SEE"));     // true
        System.out.println(obj.exist(new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}}, "ABCB"));    // false
    }

    /**
     * Checks if the given word exists in the 2D board using DFS and backtracking.
     * <p>
     * Tries every cell as a potential starting point. If the first character matches,
     * initiates a DFS search to find the complete word.
     *
     * @param board 2D grid of characters
     * @param word target word to search for
     * @return true if word exists in board, false otherwise
     */
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // Try each cell as a potential starting point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Optimization: Only start DFS if first character matches
                if (board[i][j] == word.charAt(0)) {
                    boolean[][] visited = new boolean[m][n];

                    if (backtrack(board, word, visited, i, j, 0))
                        return true;
                }
            }
        }
        return false;  // Word not found from any starting position
    }

    /**
     * Recursive DFS helper with backtracking to search for word in 4 directions.
     * <p>
     * Search Strategy:
     * <ul>
     *   <li>Base case (success): If index == word.length, entire word matched</li>
     *   <li>Base case (failure): Out of bounds, visited, or character mismatch</li>
     *   <li>Mark current cell as visited</li>
     *   <li>Recursively try all 4 directions: down, up, right, left</li>
     *   <li>If any direction succeeds, return true immediately</li>
     *   <li>Backtrack: Unmark visited[i][j] to allow use in other paths</li>
     * </ul>
     * <p>
     * Key insight: Backtracking is essential because a cell that's not part of
     * one valid path might be part of a different valid path starting elsewhere.
     *
     * @param board 2D character grid
     * @param word target word being searched
     * @param visited tracks cells used in current search path
     * @param i current row index
     * @param j current column index
     * @param index current position in word being matched
     * @return true if remaining word can be found from current position
     */
    private boolean backtrack(char[][] board, String word,
                              boolean[][] visited, int i, int j, int index) {

        // Base case (success): All characters matched
        if (index == word.length())
            return true;

        // Base case (failure): Out of bounds
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return false;

        // Base case (failure): Already visited or character mismatch
        if (visited[i][j] || board[i][j] != word.charAt(index))
            return false;

        // Mark current cell as visited in this path
        visited[i][j] = true;

        // Try all 4 directions: down, up, right, left
        if (backtrack(board, word, visited, i + 1, j, index + 1) ||      // Down
                backtrack(board, word, visited, i - 1, j, index + 1) ||  // Up
                backtrack(board, word, visited, i, j + 1, index + 1) ||  // Right
                backtrack(board, word, visited, i, j - 1, index + 1)) {  // Left
            return true;  // Word found in one of the directions
        }

        // BACKTRACKING: Unmark cell so it can be used in other paths
        visited[i][j] = false;
        return false;  // Word not found from current position
    }
}
