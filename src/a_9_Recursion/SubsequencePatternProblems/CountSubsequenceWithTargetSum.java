package a_9_Recursion.SubsequencePatternProblems;

public class CountSubsequenceWithTargetSum {
    public static void main(String[] args) {
        CountSubsequenceWithTargetSum obj = new CountSubsequenceWithTargetSum();

        int[] nums1 = {4, 9, 2, 5, 1};
        System.out.println("Count: " + obj.countSubsequenceWithTargetSum(nums1, 10)); // 2

        int[] nums2 = {4, 2, 10, 5, 1, 3};
        System.out.println("Count: " + obj.countSubsequenceWithTargetSum(nums2, 5)); // 3

        int[] nums3 = {1, 10, 4, 5};
        System.out.println("Count: " + obj.countSubsequenceWithTargetSum(nums3, 16)); // 3
    }

    public int countSubsequenceWithTargetSum(int[] nums, int k) {
        return count(nums, 0, 0, k);
    }

    private int count(int[] nums, int index, int currentSum, int k) {
        // Base case
        if (index == nums.length) {
            return (currentSum == k) ? 1 : 0;
        }

        // Include current element
        int include = count(nums, index + 1, currentSum + nums[index], k);

        // Exclude current element
        int exclude = count(nums, index + 1, currentSum, k);

        return include + exclude;
    }
}
