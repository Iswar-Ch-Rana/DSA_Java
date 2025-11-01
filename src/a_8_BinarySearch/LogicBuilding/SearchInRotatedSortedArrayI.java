package a_8_BinarySearch.LogicBuilding;

public class SearchInRotatedSortedArrayI {
    public static void main(String[] args) {
        SearchInRotatedSortedArrayI obj = new SearchInRotatedSortedArrayI();
        int[] arr = {4, 5, 6, 7, 0, 1, 2};

        System.out.println(obj.search(arr, 0)); // 4
        System.out.println(obj.search(arr, 3)); // -1
        System.out.println(obj.search(arr, 5)); // 1
    }

    public int search(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == k) {
                return mid;
            }

            // Left half is sorted
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= k && k < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // Right half is sorted
            else {
                if (nums[mid] < k && k <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
