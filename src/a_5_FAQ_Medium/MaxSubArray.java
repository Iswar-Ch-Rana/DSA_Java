package a_5_FAQ_Medium;

public class MaxSubArray {
    public static void main(String[] args) {
        MaxSubArray obj = new MaxSubArray();
        int[] arr1 = {2, 3, 5, -2, 7, -4};
        int[] arr2 = {-2, -3, -7, -2, -10, -4};
        int[] arr3 = {-1, 2, 3, -1, 2, -6, 5};

        System.out.println(obj.maxSubArray(arr1));
        System.out.println(obj.maxSubArray(arr2));
        System.out.println(obj.maxSubArray(arr3));

    }

    public int maxSubArray(int[] nums) {
        // Start with the first element
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;

        // Traverse from 2nd element
        for (int num : nums) {
            currentSum += num;

            if (maxSum < currentSum) {
                maxSum = currentSum;
            }

            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return maxSum;
    }
}
