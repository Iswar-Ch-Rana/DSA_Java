package a_8_BinarySearch._2DArrays;

import java.util.Arrays;

/**
 * 🧩 Problem: Find any peak element in a 2D matrix.
 * <p>
 * A peak element is strictly greater than all its adjacent neighbors
 * (left, right, top, and bottom). Multiple peaks may exist, return any one.
 * <p>
 * ⏱️ Time: O(N × log M) where N = rows, M = cols
 * <br>
 * Space: O(1)
 * <p>
 * 🐢 Brute Force:
 * <ul>
 *   <li>Check every element and compare with all 4 neighbors</li>
 *   <li>Return first element that's greater than all neighbors</li>
 *   <li>Time complexity: O(N × M)</li>
 * </ul>
 * <p>
 * ⚡ Optimized (Binary Search on Columns):
 * <ul>
 *   <li>Apply binary search on columns (left = 0, right = cols - 1)</li>
 *   <li>For each mid column, find the row with maximum element in that column</li>
 *   <li>Check if this max element is greater than its left and right neighbors</li>
 *   <li>If yes: It's a peak (automatically greater than top/bottom in same column)</li>
 *   <li>If no: Move toward the larger neighbor (eliminates half the columns)</li>
 * </ul>
 * <p>
 * 🧠 Key Concepts: Binary Search on Columns, Peak Finding, Greedy Direction Selection
 * <p>
 * 🎯 Interview Tips:
 * <ul>
 *   <li>Explain why finding max in column works: guarantees top/bottom condition satisfied</li>
 *   <li>Moving toward larger neighbor ensures we move toward a peak</li>
 *   <li>Similar to 1D peak finding but applied to column space</li>
 *   <li>Follow-up: Can also binary search on rows instead of columns (O(M × log N))</li>
 * </ul>
 */
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
     * Finds any peak element in a 2D matrix using binary search on columns.
     * <p>
     * Algorithm:
     * <ul>
     *   <li>Apply binary search on column indices (leftCol to rightCol)</li>
     *   <li>For each mid column, find the row index with maximum element</li>
     *   <li>This max element is guaranteed to be greater than top and bottom neighbors</li>
     *   <li>Check if it's also greater than left and right neighbors</li>
     *   <li>If yes: Return this position as peak</li>
     *   <li>If no: Move binary search toward the larger neighbor's direction</li>
     * </ul>
     *
     * @param matrix 2D matrix where adjacent cells are distinct
     * @return array [row, col] representing any peak element position
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
                return new int[]{maxRowIndex, midCol};  // Peak found
            }

            // Move toward the direction of the larger neighbor
            if (leftNeighbor > currentValue) {
                rightCol = midCol - 1;  // Search left half
            } else {
                leftCol = midCol + 1;   // Search right half
            }
        }

        return new int[]{-1, -1}; // No peak found (shouldn't happen per problem constraints)
    }

    /**
     * Finds the row index with the maximum element in a specific column.
     * <p>
     * This ensures the element is greater than all elements above and below it
     * in the same column, satisfying top/bottom peak conditions.
     *
     * @param matrix 2D matrix
     * @param totalRows number of rows in matrix
     * @param colIndex column index to search
     * @return row index containing the maximum element in the given column
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
