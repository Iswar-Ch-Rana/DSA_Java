package a_8_BinarySearch.LogicBuilding;

import java.util.Arrays;

public class SearchRange {
    public static void main(String[] args) {
        SearchRange obj = new SearchRange();
        int[] arr = {5, 7, 7, 8, 8, 10};

        System.out.println(Arrays.toString(obj.searchRange(arr, 8)));
        System.out.println(Arrays.toString(obj.searchRange(arr, 6)));
        System.out.println(Arrays.toString(obj.searchRange(arr, 5)));
    }

    public int[] searchRange(int[] nums, int target) {
        int starting = -1, ending = -1;
        starting = search(nums, target, true);
        ending = search(nums, target, false);
        return new int[]{starting, ending};
    }

    public int search(int[] arr, int taget, boolean findFirst) {
        int start = 0, end = arr.length - 1;
        int index = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == taget) {
                index = mid;
                if (findFirst) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else if (arr[mid] < taget) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return index;
    }
}
