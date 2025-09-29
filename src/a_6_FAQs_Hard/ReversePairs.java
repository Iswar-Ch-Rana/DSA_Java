package a_6_FAQs_Hard;

public class ReversePairs {
    public static void main(String[] args) {
        ReversePairs obj = new ReversePairs();
        int[] nums1 = {6, 4, 1, 2, 7};
        int[] nums2 = {5, 4, 4, 3, 3};

        System.out.println("Output1 = " + obj.reversePairs(nums1)); // 3
        System.out.println("Output2 = " + obj.reversePairs(nums2)); // 0
    }

    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int low, int high) {
        if (low >= high) return 0;

        int mid = (low + high) / 2;
        int count = mergeSort(nums, low, mid) + mergeSort(nums, mid + 1, high);

        // count reverse pairs
        count += countPairs(nums, low, mid, high);

        // merge the two halves
        merge(nums, low, mid, high);

        return count;
    }

    private int countPairs(int[] nums, int low, int mid, int high) {
        int count = 0;
        int j = mid + 1;

        for (int i = low; i <= mid; i++) {
            while (j <= high && (long) nums[i] > 2L * nums[j]) {
                j++;
            }
            count += (j - (mid + 1));
        }
        return count;
    }

    private void merge(int[] nums, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int left = low, right = mid + 1, idx = 0;

        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                temp[idx++] = nums[left++];
            } else {
                temp[idx++] = nums[right++];
            }
        }
        while (left <= mid) temp[idx++] = nums[left++];
        while (right <= high) temp[idx++] = nums[right++];

        for (int i = 0; i < temp.length; i++) {
            nums[low + i] = temp[i];
        }
    }
}

