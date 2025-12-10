package a_13_Sliding_Window_Two_Pointer.LongestAndSmallestWindowProblems;

public class BinarySubarraysWithSum {
    public static void main(String[] args) {
        System.out.println(numSubarraysWithSum(new int[]{1, 1, 0, 1, 0, 0, 1}, 3));
        System.out.println(numSubarraysWithSum(new int[]{0, 0, 0, 0, 1}, 0));
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        return solve(nums, goal) - solve(nums, goal - 1);
    }

    public static int solve(int[] nums, int goal) {
        if (goal < 0) return 0;
        int count = 0, sum = 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            sum += nums[right];
            while (sum > goal) {
                sum = sum - nums[left];
                left++;
            }
            count = count + (right - left + 1);
            right++;
        }
        return count;
    }

}
