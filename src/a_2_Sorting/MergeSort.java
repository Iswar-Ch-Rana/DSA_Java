package a_2_Sorting;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {9,8,7,6,5,4,3,2,1};
        System.out.println(Arrays.toString(mergeSort(arr)));

        int[] arr1 = {1,2,3,4,6,7};
        int[] arr2 = {2,5,6};
        System.out.println(Arrays.toString(merge(arr1, 6 , arr2, 3)));
    }

    public static int[] mergeSort(int[] nums) {
        int start = 0;
        return nums;
    }


    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        ArrayList<Integer> list = new ArrayList<>(m+n);
        int mStart = 0;
        int nStart = 0;
        while (mStart < m && nStart < n) {
            if (nums1[mStart] < nums2[nStart]) {
                list.add(nums1[mStart++]);
            } else {
                list.add(nums2[nStart++]);
            }
        }
        while (mStart < m){
            list.add(nums1[mStart++]);
        }
        while (nStart < n) {
            list.add(nums2[nStart++]);
        }
        nums1 = list.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        return nums1;
    }
}
