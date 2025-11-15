package a_9_Recursion.FAQ_Hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NQueens {
    public static void main(String[] args) {
        NQueens obj = new NQueens();

        System.out.println(obj.solveNQueens(4));
        System.out.println(obj.solveNQueens(1));
    }

    public List<List<String>> solveNQueens(int n) {

        // Create board
        List<List<String>> board = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<String> row = new ArrayList<>(Collections.nCopies(n, "."));
            board.add(row);
        }

        List<List<String>> ans = new ArrayList<>();
        nQueens(board, 0, n, ans);
        return ans;
    }

    private void nQueens(List<List<String>> board, int row, int n, List<List<String>> ans) {
        if (row == n) {
            // convert board to answer format
            List<String> temp = new ArrayList<>();
            for (List<String> r : board) {
                StringBuilder sb = new StringBuilder();
                for (String ch : r) sb.append(ch);
                temp.add(sb.toString());
            }
            ans.add(temp);
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {

                // place queen
                board.get(row).set(col, "Q");

                nQueens(board, row + 1, n, ans);

                // backtrack
                board.get(row).set(col, ".");
            }
        }
    }

    private boolean isSafe(List<List<String>> board, int row, int col, int n) {

        // check column above
        for (int r = 0; r < row; r++) {
            if (board.get(r).get(col).equals("Q"))
                return false;
        }

        // up-left diagonal
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (board.get(r).get(c).equals("Q"))
                return false;
        }

        // up-right diagonal
        for (int r = row - 1, c = col + 1; r >= 0 && c < n; r--, c++) {
            if (board.get(r).get(c).equals("Q"))
                return false;
        }

        return true;
    }
}
