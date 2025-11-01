package a_8_BinarySearch.LogicBuilding;

import java.util.ArrayList;
import java.util.List;

public class FindMinimumInRotatedSortedArray {
    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray obj = new FindMinimumInRotatedSortedArray();
        ArrayList<Integer> arrayList = new ArrayList<>(List.of(4, 5, 6, 7, 0, 1, 2, 3));
        ArrayList<Integer> arrayList1 = new ArrayList<>(List.of(3, 4, 5, 1, 2));
        ArrayList<Integer> arrayList2 = new ArrayList<>(List.of(4, 5, 6, 7, -7, 1, 2, 3));

        System.out.println(obj.findMin(arrayList));
        System.out.println(obj.findMin(arrayList1));
        System.out.println(obj.findMin(arrayList2));
    }

    public int findMin(ArrayList<Integer> arr) {
        int low = 0, high = arr.size() - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // 🔹 if mid-element is greater than the last element,
            // then minimum is in the right half
            if (arr.get(mid) > arr.get(high)) {
                low = mid + 1;
            } else {
                // 🔹 otherwise, the minimum is at mid or in left half
                high = mid;
            }
        }

        // when loop breaks, both low and high point to min
        return arr.get(low);
    }
}
