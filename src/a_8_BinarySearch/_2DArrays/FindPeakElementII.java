package a_8_BinarySearch._2DArrays;

import java.util.Arrays;

public class FindPeakElementII {
    public static void main(String[] args) {
        FindPeakElementII obj = new FindPeakElementII();

        int[][] matrix = {
                {10, 12, 14, 11, 8, 6},
                {9, 13, 15, 20, 10, 5},
                {7, 16, 18, 22, 12, 4},
                {5, 14, 21, 23, 13, 3},
                {1, 8, 9, 10, 7, 2}
        };

        System.out.println(Arrays.toString(obj.findPeakGrid(matrix)));
    }

    /**
     * Finds any peak element in a 2D matrix.
     * A peak is an element greater than its top, bottom, left, and right neighbors.
     * Time Complexity: O(n * log m)
     * Space Complexity: O(1)
     */
    public int[] findPeakGrid(int[][] matrix) {
        int totalRows = matrix.length;
        int totalCols = matrix[0].length;
        int leftCol = 0, rightCol = totalCols - 1;

        while (leftCol <= rightCol) {
            int midCol = leftCol + (rightCol - leftCol) / 2;

            // Find the row index of the maximum element in this column
            int maxRowIndex = getMaxRowIndex(matrix, totalRows, midCol);
            int currentValue = matrix[maxRowIndex][midCol];

            // Left and right neighbors (handle boundaries)
            int leftNeighbor = midCol - 1 >= 0 ? matrix[maxRowIndex][midCol - 1] : -1;
            int rightNeighbor = midCol + 1 < totalCols ? matrix[maxRowIndex][midCol + 1] : -1;

            // Check if current element is greater than both neighbors
            if (currentValue > leftNeighbor && currentValue > rightNeighbor) {
                return new int[]{maxRowIndex, midCol};
            }

            // Move toward the direction of the larger neighbor
            if (leftNeighbor > currentValue) {
                rightCol = midCol - 1;
            } else {
                leftCol = midCol + 1;
            }
        }

        return new int[]{-1, -1}; // No peak found (shouldn’t happen per problem constraints)
    }

    /**
     * Helper function to find the row index with the maximum element in a specific column.
     */
    private int getMaxRowIndex(int[][] matrix, int totalRows, int colIndex) {
        int maxRow = 0;
        for (int row = 1; row < totalRows; row++) {
            if (matrix[row][colIndex] > matrix[maxRow][colIndex]) {
                maxRow = row;
            }
        }
        return maxRow;
    }
}
