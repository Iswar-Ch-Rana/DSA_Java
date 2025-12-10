package a_13_Sliding_Window_Two_Pointer.LongestAndSmallestWindowProblems;

public class CountNumberOfNiceSubarrays {
    public static void main(String[] args) {
        System.out.println(numberOfOddSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
        System.out.println(numberOfOddSubarrays(new int[]{4, 8, 2}, 1));
        System.out.println(numberOfOddSubarrays(new int[]{41, 3, 5}, 2));

    }

    public static int numberOfOddSubarrays(int[] nums, int k) {
        return solve(nums, k) - solve(nums, k - 1);
    }

    public static int solve(int[] nums, int k) {
        if (k < 0) return 0;
        int oddCount = 0, count = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] % 2 == 1) oddCount++;

            while (oddCount > k) {
                if (nums[left] % 2 == 1) oddCount--;
                left++;
            }

            count = count + (right - left + 1);
            right++;
        }
        return count;
    }
}
