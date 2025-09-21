package a_5_FAQ_Medium;

import java.util.Arrays;

public class RotateMatrix {
    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RotateMatrix result = new RotateMatrix();
        result.rotateMatrix(arr);
        for (int[] ar : arr) {
            System.out.println(Arrays.toString(ar));
        }
    }

    /**
     * Original
     * <p>
     * 1 2 3
     * <p>
     * 4 5 6
     * <p>
     * 7 8 9
     */
    public void rotateMatrix(int[][] matrix) {
        int n = matrix.length;
        transpose(matrix, n);
        reverseColumns(matrix, n);
    }

    /**
     * Transpose
     * <p>
     * 1 4 7
     * <p>
     * 2 5 8
     * <p>
     * 3 6 9
     */
    public void transpose(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * Reverse each row
     * <p>
     * 7 4 1
     * <p>
     * 8 5 2
     * <p>
     * 9 6 3
     */

    public void reverseColumns(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            int[] tempRow = matrix[i];
            int left = 0, right = n - 1;
            while (left < right) {
                int temp = tempRow[left];
                tempRow[left] = tempRow[right];
                tempRow[right] = temp;
                left++;
                right--;
            }
            matrix[i] = tempRow;
        }
    }
}
