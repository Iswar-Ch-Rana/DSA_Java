package a_8_BinarySearch.LogicBuilding;

import java.util.ArrayList;
import java.util.Arrays;

public class FindOutHowManyTimesTheArrayIsRotated {
    public static void main(String[] args) {
        FindOutHowManyTimesTheArrayIsRotated obj = new FindOutHowManyTimesTheArrayIsRotated();
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 0, 1, 2, 3)); // 4
        ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(3, 4, 5, 1, 2)); // 3
        ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(4, 5, 1, 2)); // 2

        System.out.println(obj.findKRotation(arr));
        System.out.println(obj.findKRotation(arr1));
        System.out.println(obj.findKRotation(arr2));
    }

    public int findKRotation(ArrayList<Integer> nums) {
        int low = 0, high = nums.size() - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums.get(mid) > nums.get(high)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
