package a_8_BinarySearch._2DArrays;

/**
 * 🧩 Problem: Search for a target value in a 2D matrix with sorted rows and columns.
 * <p>
 * Matrix properties: Each row is sorted left to right, and first element of each row
 * is greater than last element of previous row (entire matrix is sorted if flattened).
 * <p>
 * ⏱️ Time: O(log(M × N)) where M = rows, N = cols
 * <br>
 * Space: O(1)
 * <p>
 * 🐢 Brute Force:
 * <ul>
 *   <li>Check every element in the matrix sequentially</li>
 *   <li>Time complexity: O(M × N)</li>
 * </ul>
 * <p>
 * ⚡ Optimized:
 * <ul>
 *   <li>Treat the 2D matrix as a virtual flattened 1D sorted array</li>
 *   <li>Apply binary search using index mapping: row = mid / cols, col = mid % cols</li>
 *   <li>Total elements = rows × cols, search from 0 to (rows × cols - 1)</li>
 * </ul>
 * <p>
 * 🧠 Key Concepts: Binary Search, 2D to 1D Index Mapping, Virtual Array
 * <p>
 * 🎯 Interview Tips:
 * <ul>
 *   <li>Explain index conversion: mid/cols gives row, mid%cols gives column</li>
 *   <li>Mention this works because matrix is fully sorted when read row-by-row</li>
 *   <li>Follow-up: Search in matrix where rows/cols sorted but not fully sorted (staircase approach)</li>
 * </ul>
 */
public class SearchInA2DMatrix {

    public static void main(String[] args) {
        SearchInA2DMatrix obj = new SearchInA2DMatrix();

        int[][] arr1 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        int[][] arr2 = {{1, 2, 4}, {6, 7, 8}, {9, 10, 34}};
        int[][] arr3 = {{1, 2, 4}, {6, 7, 8}, {9, 10, 34}};

        System.out.println(obj.searchMatrix(arr1, 8));   // true
        System.out.println(obj.searchMatrix(arr2, 78));  // false
        System.out.println(obj.searchMatrix(arr3, 7));   // true
    }

    /**
     * Searches for a target value in a fully sorted 2D matrix using binary search.
     * <p>
     * Algorithm:
     * <ul>
     *   <li>Treat matrix as virtual 1D array with size rows × cols</li>
     *   <li>Apply binary search from index 0 to (rows × cols - 1)</li>
     *   <li>Convert 1D index to 2D coordinates: row = mid/cols, col = mid%cols</li>
     *   <li>Compare matrix[row][col] with target and adjust search range</li>
     * </ul>
     *
     * @param matrix fully sorted 2D matrix (sorted row-wise and column-wise)
     * @param target value to search for
     * @return true if target exists in matrix, false otherwise
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int low = 0, high = rows * cols - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int x = mid / cols;  // Row index
            int y = mid % cols;  // Column index

            if (matrix[x][y] == target)
                return true;
            else if (matrix[x][y] < target)
                low = mid + 1;
            else
                high = mid - 1;
        }

        return false;
    }
}
