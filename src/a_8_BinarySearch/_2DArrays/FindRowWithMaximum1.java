package a_8_BinarySearch._2DArrays;

/**
 * 🧩 Problem: Find the row with maximum number of 1s in a binary matrix where each row is sorted.
 * <p>
 * Return row index (0-indexed), or -1 if no 1s exist. If tie, return smallest index.
 * <p>
 * ⏱️ Time: O(N * log M) where N = rows, M = cols
 * <br>
 * Space: O(1)
 * <p>
 * 🐢 Brute Force:
 * <ul>
 *   <li>Count 1s in each row by linear traversal</li>
 *   <li>Time complexity: O(N * M)</li>
 * </ul>
 * <p>
 * ⚡ Optimized:
 * <ul>
 *   <li>For each row, use binary search to find lower bound (first occurrence) of 1</li>
 *   <li>Count of 1s = cols - lowerBoundIndex</li>
 *   <li>Track row with maximum count</li>
 * </ul>
 * <p>
 * 🧠 Key Concepts: Binary Search, Lower Bound, Sorted Row Optimization
 * <p>
 * 🎯 Interview Tips:
 * <ul>
 *   <li>Explain why binary search works: rows are sorted (all 0s before all 1s)</li>
 *   <li>Mention edge case: all 0s matrix returns -1</li>
 *   <li>Follow-up: Optimize to O(N + M) using staircase approach from top-right corner</li>
 * </ul>
 */
public class FindRowWithMaximum1 {

    public static void main(String[] args) {
        FindRowWithMaximum1 obj = new FindRowWithMaximum1();

        int[][] arr1 = new int[][]{{1, 1, 1}, {0, 0, 1}, {0, 0, 0}};
        int[][] arr2 = new int[][]{{0, 0}, {0, 0}};
        int[][] arr3 = new int[][]{{0, 0, 1}, {0, 1, 1}, {0, 1, 1}};

        System.out.println(obj.rowWithMax1s(arr1));
        System.out.println(obj.rowWithMax1s(arr2));
        System.out.println(obj.rowWithMax1s(arr3));
    }

    /**
     * Finds the row with maximum number of 1s using binary search optimization.
     * <p>
     * Approach 2: Binary Search on each row
     * <p>
     * Algorithm:
     * <ul>
     *   <li>Iterate through each row</li>
     *   <li>Use binary search to find the first occurrence of 1</li>
     *   <li>Calculate count of 1s: columns - lowerBoundIndex</li>
     *   <li>Track and return row with maximum count</li>
     * </ul>
     *
     * @param mat binary matrix with sorted rows
     * @return index of row with maximum 1s, or -1 if no 1s exist
     */
    public int rowWithMax1s(int[][] mat) {
        int answerIndex = -1, maxCount = 0;
        for (int i = 0; i < mat.length; i++) {
            int count = mat[i].length - getLowerBound(mat[i]);
            if (count > maxCount) {
                answerIndex = i;
                maxCount = count;
            }
        }
        return answerIndex;
    }

    /**
     * Finds the lower bound (first occurrence) of 1 in a sorted binary array.
     * <p>
     * Uses binary search to locate the leftmost position where 1 appears.
     * If no 1 exists, returns the array length.
     *
     * @param arr sorted binary array (0s followed by 1s)
     * @return index of first 1, or array length if no 1 exists
     */
    public int getLowerBound(int[] arr) {
        int low = 0, high = arr.length - 1;
        int lowerBound = arr.length;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == 1) {
                lowerBound = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return lowerBound;
    }

    /**
     * Finds the row with maximum number of 1s using brute force approach.
     * <p>
     * Approach 1: Linear count - iterates through entire matrix
     * <p>
     * Time Complexity: O(N * M)
     * <br>
     * Space Complexity: O(1)
     *
     * @param mat binary matrix with sorted rows
     * @return index of row with maximum 1s, or -1 if no 1s exist
     */
    public int rowWithMax1s1(int[][] mat) {
        int answerIndex = -1, maxCount = 0;
        for (int i = 0; i < mat.length; i++) {
            int count = 0;
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 1) count++;
            }
            if (count > maxCount) {
                answerIndex = i;
                maxCount = count;
            }
        }
        return answerIndex;
    }
}
