package a_9_Recursion.SubsequencePatternProblems;

public class CheckIfThereExistsASubsequenceWithSumK {
    public static void main(String[] args) {
        CheckIfThereExistsASubsequenceWithSumK obj = new CheckIfThereExistsASubsequenceWithSumK();

        int[] nums1 = {1, 2, 3, 4, 5};
        int k1 = 8;
        System.out.println("Input: [1,2,3,4,5], k=8 → " + obj.checkSubsequenceSum(nums1, k1));

        int[] nums2 = {4, 3, 9, 2};
        int k2 = 10;
        System.out.println("Input: [4,3,9,2], k=10 → " + obj.checkSubsequenceSum(nums2, k2));

        int[] nums3 = {3, 1, 7, 5};
        int k3 = 6;
        System.out.println("Input: [3,1,7,5], k=6 → " + obj.checkSubsequenceSum(nums3, k3));
    }

    public boolean checkSubsequenceSum(int[] nums, int k) {
        return check(nums, 0, 0, k);
    }

    private boolean check(int[] nums, int index, int currentSum, int k) {
        // Base case — reached end of array
        if (index == nums.length) {
            return currentSum == k;
        }

        // Include current element
        boolean include = check(nums, index + 1, currentSum + nums[index], k);

        // Exclude current element
        boolean exclude = check(nums, index + 1, currentSum, k);

        // If any path gives true → return true
        return include || exclude;
    }
}
