package a_8_BinarySearch.Fundamentals;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch obj = new BinarySearch();
        int[] arr1 = {-1, 0, 3, 5, 9, 12};
        int[] arr2 = {-1, 0, 3, 5, 9, 12};
        int[] arr3 = {-1, 0, 3, 5, 9, 12};

        System.out.println(obj.search(arr1, 9));
        System.out.println(obj.search(arr2, 2));
        System.out.println(obj.search(arr3, -1));
    }

    public int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == target) {
                return mid; // target found
            } else if (nums[mid] < target) {
                start = mid + 1; // move right
            } else {
                end = mid - 1; // move left
            }
        }
        return -1; // target not found
    }
}
