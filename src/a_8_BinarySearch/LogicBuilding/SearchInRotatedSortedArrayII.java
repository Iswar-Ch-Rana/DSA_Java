package a_8_BinarySearch.LogicBuilding;

public class SearchInRotatedSortedArrayII {
    public static void main(String[] args) {
        SearchInRotatedSortedArrayII obj = new SearchInRotatedSortedArrayII();
        int[] arr = {7, 8, 1, 2, 3, 3, 3, 4, 5, 6};
        int[] arr2 = {3, 3, 3, 3, 1, 2, 3}; // this case need to be handled

        System.out.println(obj.searchInARotatedSortedArrayII(arr, 3)); // true
        System.out.println(obj.searchInARotatedSortedArrayII(arr, 10)); // false
        System.out.println(obj.searchInARotatedSortedArrayII(arr, 7)); // true
        System.out.println(obj.searchInARotatedSortedArrayII(arr2, 2)); // true
    }

    public boolean searchInARotatedSortedArrayII(int[] nums, int k) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] == k) {
                return true;
            }
            /**
             * 🚨 Important:
             * This section ensures that when both sides are equal due to duplicates,
             * we still move forward and avoid infinite loops.
             * */

            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
                continue;
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
        return false;
    }
}
