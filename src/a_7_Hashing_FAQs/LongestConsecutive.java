package a_7_Hashing_FAQs;

import java.util.Arrays;
import java.util.HashSet;

public class LongestConsecutive {
    public static void main(String[] args) {
        int[] arr1 = {100, 4, 200, 1, 3, 2};
        int[] arr2 = {0, 3, 7, 2, 5, 8, 4, 6, 0, 1};
        int[] arr3 = {1, 9, 3, 10, 4, 20, 2};

        LongestConsecutive obj = new LongestConsecutive();

        System.out.println(obj.longestConsecutive1(arr1));
        System.out.println(obj.longestConsecutive1(arr2));
        System.out.println(obj.longestConsecutive1(arr3));


        System.out.println(obj.longestConsecutive(arr1));
        System.out.println(obj.longestConsecutive(arr2));
        System.out.println(obj.longestConsecutive(arr3));
    }

    // Solution - 2 TIME - O(N) SPACE - O(N)
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int maxCount = 1;
        for (int ele : set) {
            if (!set.contains(ele - 1)) {
                int currentNum = ele;
                int count = 1;
                while (set.contains(currentNum + 1)) {
                    count++;
                    currentNum++;
                }
                maxCount = Math.max(count, maxCount);
            }
        }
        return maxCount;
    }

    // Solution - 1 TIME - O(N log N) SPACE - O(1)
    public int longestConsecutive1(int[] nums) {
        if (nums.length == 0) return 0;

        Arrays.sort(nums);
        int maxCount = 1, count = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] + 1 == nums[i + 1]) {
                count++;
            } else if (nums[i] != nums[i + 1]) {
                // skip duplicates
                count = 1;
            }
            maxCount = Math.max(maxCount, count);
        }

        return maxCount;
    }
}
