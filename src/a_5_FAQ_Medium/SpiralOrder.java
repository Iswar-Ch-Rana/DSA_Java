package a_5_FAQ_Medium;

import java.util.*;

public class SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        SpiralOrder obj = new SpiralOrder();
        List<Integer> ans = obj.spiralOrder(matrix);
        System.out.println("Output = " + ans);
    }

    /**
     * Solution - 1 Optimal TIME - O(M*N) SPACE - (M*N)
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList<>();

        int row = matrix.length;
        int col = matrix[0].length;

        int top = 0, bottom = row - 1;
        int left = 0, right = col - 1;

        int count = 0, total = row * col;

        while (count < total) {

            // Traverse Top Row
            for (int j = left; j <= right && count < total; j++) {
                ans.add(matrix[top][j]);
                count++;
            }
            top++;

            // Traverse Right Column
            for (int i = top; i <= bottom && count < total; i++) {
                ans.add(matrix[i][right]);
                count++;
            }
            right--;

            // Traverse Bottom Row
            for (int j = right; j >= left && count < total; j--) {
                ans.add(matrix[bottom][j]);
                count++;
            }
            bottom--;

            // Traverse Left Column
            for (int i = bottom; i >= top && count < total; i--) {
                ans.add(matrix[i][left]);
                count++;
            }
            left++;
        }
        return ans;
    }
}
