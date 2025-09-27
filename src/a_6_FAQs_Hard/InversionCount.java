package a_5_FAQ_Medium;

public class InversionCount {

    public static void main(String[] args) {
        InversionCount obj = new InversionCount();
        int[] nums1 = {2, 3, 7, 1, 3, 5};
        int[] nums2 = {-10, -5, 6, 11, 15, 17};

        System.out.println("Inversions in nums1: " + obj.numberOfInversions(nums1));
        System.out.println("Inversions in nums2: " + obj.numberOfInversions(nums2));
    }

    // Main function to count inversions
    public long numberOfInversions(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    // Modified merge sort
    private long mergeSort(int[] nums, int left, int right) {
        long count = 0;
        if (left < right) {
            int mid = (left + right) / 2;

            // Count inversions in left half
            count += mergeSort(nums, left, mid);

            // Count inversions in right half
            count += mergeSort(nums, mid + 1, right);

            // Count inversions while merging
            count += merge(nums, left, mid, right);
        }
        return count;
    }

    // Merge step that counts inversions
    private long merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        long count = 0;

        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
                // All remaining elements in left half are greater than nums[j-1]
                count += (mid - i + 1);
            }
        }

        // Copy remaining elements
        while (i <= mid) temp[k++] = nums[i++];
        while (j <= right) temp[k++] = nums[j++];

        // Copy back to original array
        for (i = left, k = 0; i <= right; i++, k++) {
            nums[i] = temp[k];
        }

        return count;
    }
}
