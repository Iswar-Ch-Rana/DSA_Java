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
}
