package a_8_BinarySearch.LogicBuilding;

import java.util.Arrays;

public class GetFloorAndCeil {
    public static void main(String[] args) {
        int[] arr1 = {3, 4, 4, 7, 8, 10};
        int[] arr2 = {2, 4, 6, 8, 10, 12, 14};

        GetFloorAndCeil obj = new GetFloorAndCeil();
        System.out.println(Arrays.toString(obj.getFloorAndCeil(arr1, 5))); // [4, 7]
        System.out.println(Arrays.toString(obj.getFloorAndCeil(arr1, 8))); // [8, 8]
        System.out.println(Arrays.toString(obj.getFloorAndCeil(arr2, 1))); // [-1, 2]
    }

    public int[] getFloorAndCeil(int[] nums, int x) {
        int floor = -1, ceil = -1;
        int start = 0, end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] == x) {
                // Exact match means both floor and ceil are the same
                floor = nums[mid];
                ceil = nums[mid];
                break;
            } else if (nums[mid] < x) {
                // possible floor found
                floor = nums[mid];
                start = mid + 1;
            } else {
                // possible ceil found
                ceil = nums[mid];
                end = mid - 1;
            }
        }
        return new int[]{floor, ceil};
    }
}
