package a_4_LogicBuilding;

import java.util.*;

public class InterSectionInSortedArray {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2, 3, 5};
        int[] nums2 = {1, 2, 2, 7};
        int[] result = intersectionArray(nums1, nums2);
        System.out.println("output 1: " + Arrays.toString(result));

        int[] nums3 = {1, 2, 2, 3, 5};
        int[] nums4 = {1, 2, 2, 7};
        int[] result1 = intersection(nums3, nums4);
        System.out.println("output 2: " + Arrays.toString(result1));
    }

    public static int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> res = new HashSet<>();

        for (int j : nums1) {
            set.add(j);
        }

        for (int i : nums2) {
            if (set.contains(i)) res.add(i);
        }

        int[] arr = new int[res.size()];
        int jtr = 0;
        for (int itr : res)
            arr[jtr++] = itr;

        return arr;
    }

    public static int[] intersectionArray(int[] nums1, int[] nums2) {
        int i = 0;
        int j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            }
        }
        return list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
}
