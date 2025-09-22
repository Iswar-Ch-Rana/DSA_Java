package a_5_FAQ_Medium;

import java.util.*;

public class TwoSum {
    public static void main(String[] args) {
        TwoSum obj = new TwoSum();
        int[] arr = {1, 3, 5, -7, 6, -3};

        System.out.println("Solution 1 (Brute Force)  = " + Arrays.toString(obj.twoSum1(arr, 0)));
        System.out.println("Solution 2 (HashMap)      = " + Arrays.toString(obj.twoSum2(arr, 0)));
        System.out.println("Solution 3 (Sorting + 2P) = " + Arrays.toString(obj.twoSum3(arr, 0)));
    }

    // ✅ Solution 1 : Brute Force (O(N^2), O(1))
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j}; // directly return indices
                }
            }
        }
        return new int[]{-1, -1}; // not found
    }

    // ✅ Solution 2 : HashMap (O(N), O(N))
    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>(); // num -> index
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(diff)) {
                return new int[]{map.get(diff), i}; // return indices
            }
            map.put(nums[i], i); // store index for future
        }
        return new int[]{-1, -1};
    }

    // ✅ Solution 3 : Sorting + Two Pointers (O(N log N), O(N))
    // Trick: we need original indices, so store value + index pair
    public int[] twoSum3(int[] nums, int target) {
        int n = nums.length;
        int[][] arr = new int[n][2]; // store [value, index]
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr); // sort by value

        int start = 0, end = n - 1;
        while (start < end) {
            int sum = arr[start][0] + arr[end][0];
            if (sum == target) {
                return new int[]{arr[start][1], arr[end][1]}; // return original indices
            } else if (sum < target) {
                start++;
            } else {
                end--;
            }
        }
        return new int[]{-1, -1};
    }
}
