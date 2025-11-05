package a_8_BinarySearch.FAQs;

import java.util.Arrays;

public class MedianOf2SortedArrays {
    public static void main(String[] args) {
        MedianOf2SortedArrays obj = new MedianOf2SortedArrays();
        int[] arr1 = {2, 4, 6}, arr2 = {1, 3, 5};
        int[] arr3 = {2, 4, 6}, arr4 = {1, 3};
        int[] arr5 = {2, 4, 6}, arr6 = {5, 10, 30, 40};
        System.out.println("Median -> " + obj.median(arr1, arr2));
        System.out.println("Median -> " + obj.median(arr3, arr4));
        System.out.println("Median -> " + obj.median(arr5, arr6));
    }

    // Approach-1 TIME - O(m+n) SPACE - O(m+n)
    public double median(int[] arr1, int[] arr2) {
        int size1 = arr1.length, size2 = arr2.length;
        // merge two sorted arrays
        int[] mergedArray = mergeTwoSortedArrays(arr1, size1, arr2, size2);
        // find the median
        System.out.println(Arrays.toString(mergedArray));
        if ((size1 + size2) % 2 == 1) {
            return mergedArray[(size1 + size2) / 2];
        } else {
            int med1 = (size1 + size2) / 2;
            int med2 = ((size1 + size2) / 2) - 1;
            return (mergedArray[med1] + mergedArray[med2]) / 2.0;
        }
    }

    int[] mergeTwoSortedArrays(int[] arr1, int size1, int[] arr2, int size2) {
        int it1 = 0, it2 = 0;
        int[] result = new int[size1 + size2];
        int resIt = 0;
        while (it1 < size1 && it2 < size2) {
            if (arr1[it1] < arr2[it2]) {
                result[resIt++] = arr1[it1++];
            } else {
                result[resIt++] = arr2[it2++];
            }
        }
        // if arr1 left with element then copy
        while (it1 < size1) {
            result[resIt++] = arr1[it1++];
        }
        // if arr2 left with element then copy
        while (it2 < size2) {
            result[resIt++] = arr2[it2++];
        }
        return result;
    }
}
