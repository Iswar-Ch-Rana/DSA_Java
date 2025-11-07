package a_8_BinarySearch._2DArrays;

public class MedianOfRowWiseSortedMatrix {
    public static void main(String[] args) {
        MedianOfRowWiseSortedMatrix obj = new MedianOfRowWiseSortedMatrix();

        int[][] matrix1 = {
                {1, 4, 9},
                {2, 5, 6},
                {3, 7, 8}
        };

        int[][] matrix2 = {
                {1, 3, 8},
                {2, 3, 4},
                {1, 2, 5}
        };

        System.out.println("Median: " + obj.findMedian(matrix1)); // 5
        System.out.println("Median: " + obj.findMedian(matrix2)); // 3
    }

    /**
     * Finds the median of a row-wise sorted matrix.
     * Time Complexity: O(rows * log(cols) * log(max - min))
     * Space Complexity: O(1)
     */
    public int findMedian(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Step 1: Find the global min and max elements in the matrix
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        for (int[] row : matrix) {
            minVal = Math.min(minVal, row[0]);
            maxVal = Math.max(maxVal, row[cols - 1]);
        }

        // Step 2: Binary search on the value range
        int desiredCount = (rows * cols + 1) / 2; // median position
        while (minVal < maxVal) {
            int mid = minVal + (maxVal - minVal) / 2;
            int count = countLessThanOrEqual(matrix, mid);

            if (count < desiredCount)
                minVal = mid + 1; // too few elements ≤ mid, increase range
            else
                maxVal = mid; // enough elements, shrink range from right
        }

        return minVal; // when low == high, we've found the median
    }

    /**
     * Counts how many elements in the matrix are <= target.
     * Uses binary search in each sorted row.
     */
    private int countLessThanOrEqual(int[][] matrix, int target) {
        int count = 0;
        for (int[] row : matrix) {
            count += upperBound(row, target);
        }
        return count;
    }

    /**
     * Finds index of first element greater than target (upper bound).
     * Number of elements <= target = index of upper bound.
     */
    private int upperBound(int[] row, int target) {
        int low = 0, high = row.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (row[mid] <= target)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }
}
