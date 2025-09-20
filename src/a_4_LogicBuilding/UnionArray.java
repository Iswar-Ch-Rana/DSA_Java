package a_4_LogicBuilding;

import java.util.*;

public class UnionArray {
    public static void main(String[] args) {
        int[] nums1 = {3, 4, 6, 7, 9, 9};
        int[] nums2 = {1, 5, 7, 8, 8};
        int[] result = optimalUnionArray(nums1, nums2);
        System.out.println("output: " + Arrays.toString(result));
    }

    // Solution - 1 BruteForce
    public static int[] unionArrayUsingSet(int[] nums1, int[] nums2) {
        Set<Integer> uniqueElements = new TreeSet<>();
        for (int num : nums1) {
            uniqueElements.add(num);
        }
        for (int num : nums2) {
            uniqueElements.add(num);
        }
        return uniqueElements.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    // Solution 2 - Better
    public static int[] unionArray(int[] nums1, int[] nums2) {
        ArrayList<Integer> answer = new ArrayList<>();
        int i = 0, j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                addIfNotDuplicate(answer, nums1[i]);
                i++;
            } else if (nums1[i] > nums2[j]) {
                addIfNotDuplicate(answer, nums2[j]);
                j++;
            } else {
                addIfNotDuplicate(answer, nums1[i]);
                int temp = nums1[i];
                while (i < nums1.length && nums1[i] == temp) i++;
                while (j < nums2.length && nums2[j] == temp) j++;
            }
        }

        // copy rest
        while (i < nums1.length) {
            addIfNotDuplicate(answer, nums1[i]);
            i++;
        }
        while (j < nums2.length) {
            addIfNotDuplicate(answer, nums2[j]);
            j++;
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // helper to avoid duplicate insertions
    private static void addIfNotDuplicate(ArrayList<Integer> list, int num) {
        if (list.isEmpty() || list.get(list.size() - 1) != num) {
            list.add(num);
        }
    }

    // Solution 3 Optimal

    private static int[] optimalUnionArray(int[] nums1, int[] nums2) {
        List<Integer> UnionList = new ArrayList<>();
        int i = 0, j = 0;
        int n = nums1.length;
        int m = nums2.length;

        while (i < n && j < m) {
            // Case 1 and 2
            if (nums1[i] <= nums2[j]) {
                if (UnionList.isEmpty() || UnionList.get(UnionList.size() - 1) != nums1[i]) {
                    UnionList.add(nums1[i]);
                }
                i++;
            }
            // Case 3
            else {
                if (UnionList.isEmpty() || UnionList.get(UnionList.size() - 1) != nums2[j]) {
                    UnionList.add(nums2[j]);
                }
                j++;
            }
        }

        // Add remaining elements of nums1, if any
        while (i < n) {
            if (UnionList.isEmpty() || UnionList.get(UnionList.size() - 1) != nums1[i]) {
                UnionList.add(nums1[i]);
            }
            i++;
        }

        // Add remaining elements of nums2, if any
        while (j < m) {
            if (UnionList.isEmpty() || UnionList.get(UnionList.size() - 1) != nums2[j]) {
                UnionList.add(nums2[j]);
            }
            j++;
        }

        return UnionList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

}
