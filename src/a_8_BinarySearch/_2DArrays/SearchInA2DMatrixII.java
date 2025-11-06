package a_8_BinarySearch._2DArrays;

/**
 * 🧩 Problem: Search for a target value in a 2D matrix where rows and columns are sorted.
 * <p>
 * Matrix properties: Each row is sorted in ascending order (left to right),
 * and each column is sorted in ascending order (top to bottom).
 * <p>
 * ⏱️ Time: O(M + N) where M = rows, N = cols
 * <br>
 * Space: O(1)
 * <p>
 * 🐢 Brute Force:
 * <ul>
 *   <li>Check every element in the matrix using nested loops</li>
 *   <li>Time complexity: O(M × N)</li>
 * </ul>
 * <p>
 * ⚡ Optimized (Staircase Search):
 * <ul>
 *   <li>Start from top-right corner (row = 0, col = cols - 1)</li>
 *   <li>If current element == target: Found, return true</li>
 *   <li>If current element > target: Move left (col--), eliminate entire column</li>
 *   <li>If current element < target: Move down (row++), eliminate entire row</li>
 *   <li>Each step eliminates either a row or column</li>
 * </ul>
 * <p>
 * 🧠 Key Concepts: Staircase Algorithm, Binary Search Space Reduction, Elimination Strategy
 * <p>
 * 🎯 Interview Tips:
 * <ul>
 *   <li>Explain why top-right (or bottom-left) corner is chosen: provides decisive movement</li>
 *   <li>Top-right: smaller elements are left, larger elements are down</li>
 *   <li>Alternative: Start from bottom-left corner with similar logic</li>
 *   <li>Follow-up: Compare with Matrix I (fully sorted) which uses binary search O(log(M×N))</li>
 * </ul>
 */
public class SearchInA2DMatrixII {

    public static void main(String[] args) {
        SearchInA2DMatrixII obj = new SearchInA2DMatrixII();

        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        System.out.println(obj.searchMatrix(matrix, 5));   // true
        System.out.println(obj.searchMatrix(matrix, 20));  // false
        System.out.println(obj.searchMatrix(matrix, 1));   // true
    }

    /**
     * Searches for a target value in a row-wise and column-wise sorted matrix
     * using the staircase search algorithm.
     * <p>
     * Algorithm:
     * <ul>
     *   <li>Start from top-right corner of matrix (row = 0, col = cols - 1)</li>
     *   <li>Compare current element with target</li>
     *   <li>If equal: Return true (target found)</li>
     *   <li>If current > target: Move left (eliminate column with larger elements)</li>
     *   <li>If current < target: Move down (eliminate row with smaller elements)</li>
     *   <li>Continue until target found or boundaries exceeded</li>
     * </ul>
     *
     * @param matrix 2D matrix sorted row-wise and column-wise
     * @param target value to search for
     * @return true if target exists in matrix, false otherwise
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0, col = cols - 1;  // Start from top-right corner

        while (row < rows && col >= 0) {
            if (matrix[row][col] == target)
                return true;  // Target found
            else if (matrix[row][col] > target)
                col--;  // Move left (eliminate column)
            else
                row++;  // Move down (eliminate row)
        }

        return false;  // Target not found
    }
}
