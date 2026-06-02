package a_6_FAQs_Hard;

public class MaxProduct {
    public static void main(String[] args) {
        MaxProduct sol = new MaxProduct();
        int[] nums1 = {4, 5, 3, 7, 1, 2};
        int[] nums2 = {-5, 0, -2};

        System.out.println(sol.maxProduct(nums1)); // 840
        System.out.println(sol.maxProduct(nums2)); // 0
    }

    public int maxProduct(int[] nums) {
        int n = nums.length;

        // Initialize
        int maxProd = nums[0];
        int minProd = nums[0];
        int result = nums[0];

        for (int i = 1; i < n; i++) {
            int curr = nums[i];

            // Store old maxProd before updating
            int tempMax = maxProd;

            // Update max and min
            maxProd = Math.max(curr, Math.max(curr * maxProd, curr * minProd));
            minProd = Math.min(curr, Math.min(curr * tempMax, curr * minProd));

            // Update result
            result = Math.max(result, maxProd);
        }

        return result;
    }


    public int findMaxProduct(int[] arr) {
        int n = arr.length;

        // Edge Case: If there's only one element, it's the maximum product by default
        if (n == 1) {
            return arr[0];
        }

        final int MOD = 1_000_000_007;
        int negativeClosestToZero = Integer.MIN_VALUE;
        int positives = 0, negatives = 0, zeros = 0;

        // Step 1: Count classifications and track the largest negative value
        for (int num : arr) {
            if (num > 0) {
                positives++;
            } else if (num < 0) {
                negatives++;
                if (num > negativeClosestToZero) {
                    negativeClosestToZero = num;
                }
            } else {
                zeros++;
            }
        }

        // Edge Case: All elements are zeros
        if (zeros == n) return 0;

        // Edge Case: Exactly one negative, no positives, and the rest are zeros
        // Dropping the single negative leaves us with a maximum subset product of 0
        if (negatives == 1 && positives == 0 && zeros > 0) return 0;

        long prod = 1;
        boolean skippedMaxNeg = false;

        // Step 2: Compute the rolling product with a continuous modulo
        for (int num : arr) {
            if (num == 0) {
                continue;
            }

            // If the negative count is odd, skip the one closest to zero exactly once
            if (num < 0 && negatives % 2 == 1 && num == negativeClosestToZero && !skippedMaxNeg) {
                skippedMaxNeg = true;
                continue;
            }

            // Multiply safely and apply modulo at each step to prevent overflow
            prod = (prod * num) % MOD;
        }

        return (int) prod;
    }
}
