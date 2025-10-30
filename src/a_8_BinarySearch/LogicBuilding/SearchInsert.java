package a_8_BinarySearch.LogicBuilding;

public class SearchInsert {
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};

        SearchInsert obj = new SearchInsert();
        System.out.println(obj.searchInsert(arr, 5));
        System.out.println(obj.searchInsert(arr, 2));
        System.out.println(obj.searchInsert(arr, 7));
    }

    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        int ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return (ans == -1) ? nums.length : ans;
    }
}
