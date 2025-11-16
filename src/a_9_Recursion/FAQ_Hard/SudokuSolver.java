package a_9_Recursion.FAQ_Hard;

public class SudokuSolver {

    public static void main(String[] args) {
        SudokuSolver obj = new SudokuSolver();

        char[][] board1 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        obj.solveSudoku(board1);

        for (char[] row : board1) {
            for (char c : row) System.out.print(c + " ");
            System.out.println();
        }
    }

    // Start solving from cell (0,0)
    public void solveSudoku(char[][] board) {
        solve(board, 0, 0);
    }

    private boolean solve(char[][] board, int row, int col) {

        // If row reaches 9, entire board is solved
        if (row == 9)
            return true;

        // Compute next cell’s location
        int nextRow = row;
        int nextCol = col + 1;

        // If we reached end of the current row, move to next row
        if (col == 8) {
            nextRow = row + 1;
            nextCol = 0;
        }

        // If current cell is already filled, just move to next cell
        if (board[row][col] != '.')
            return solve(board, nextRow, nextCol);

        // Try placing digits 1 → 9
        for (char digit = '1'; digit <= '9'; digit++) {

            // Check if digit can be placed safely
            if (isSafe(board, row, col, digit)) {

                // Place the digit
                board[row][col] = digit;

                // Move to next cell
                if (solve(board, nextRow, nextCol))
                    return true;

                // Backtrack: undo the placement
                board[row][col] = '.';
            }
        }

        // No number fits here → backtrack
        return false;
    }


    private boolean isSafe(char[][] board, int row, int col, char num) {

        // Check entire row
        for (int j = 0; j < 9; j++)
            if (board[row][j] == num)
                return false;

        // Check entire column
        for (int i = 0; i < 9; i++)
            if (board[i][col] == num)
                return false;

        // Identify starting point of the 3×3 box
        int boxRowStart = (row / 3) * 3;
        int boxColStart = (col / 3) * 3;

        // Check inside the 3×3 sub-grid
        for (int r = boxRowStart; r < boxRowStart + 3; r++) {
            for (int c = boxColStart; c < boxColStart + 3; c++) {
                if (board[r][c] == num)
                    return false;
            }
        }

        // Safe to place number
        return true;
    }
}
