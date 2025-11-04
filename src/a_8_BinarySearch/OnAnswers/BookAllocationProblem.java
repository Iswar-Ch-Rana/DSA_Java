package a_8_BinarySearch.OnAnswers;

import java.util.Arrays;

public class BookAllocationProblem {
    public static void main(String[] args) {
        BookAllocationProblem obj = new BookAllocationProblem();
        System.out.println(obj.findPages(new int[]{12, 34, 67, 90}, 2));      // 113
        System.out.println(obj.findPages(new int[]{25, 46, 28, 49, 24}, 4));  // 71
        System.out.println(obj.findPages(new int[]{15, 17, 20}, 2));          // 32
    }

    public int findPages(int[] nums, int m) {
        if (m > nums.length) return -1;

        int start = Arrays.stream(nums).max().getAsInt();
        int end = Arrays.stream(nums).sum();
        int ans = -1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isPossibleSolution(nums, m, mid)) {
                ans = mid;
                end = mid - 1; // try smaller max load
            } else {
                start = mid + 1; // increase limit
            }
        }
        return ans;
    }

    private boolean isPossibleSolution(int[] arr, int m, int mid) {
        int studentCount = 1;
        int pageSum = 0;

        for (int pages : arr) {
            if (pageSum + pages <= mid) {
                pageSum += pages;
            } else {
                studentCount++;
                if (studentCount > m || pages > mid) {
                    return false;
                }
                pageSum = pages;
            }
        }
        return true;
    }
}
