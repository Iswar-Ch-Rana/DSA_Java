package a_13_Sliding_Window_Two_Pointer.LongestAndSmallestWindowProblems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FruitIntoBaskets {
    public static void main(String[] args) {
        System.out.println(totalFruits2(new int[]{1, 2, 3, 2, 2}));
        System.out.println(totalFruits2(new int[]{1, 2, 3, 4, 5}));

        System.out.println(totalFruits1(new int[]{1, 2, 3, 2, 2}));
        System.out.println(totalFruits1(new int[]{1, 2, 3, 4, 5}));
    }

    public int totalFruits(int[] fruits) {
        int n = fruits.length;
        int maxLen = 0;
        HashMap<Integer, Integer> mpp = new HashMap<>();
        int left = 0, right = 0;

        while (right < n) {
            mpp.put(fruits[right], mpp.getOrDefault(fruits[right], 0) + 1);

            /* If number of different fruits exceeds
             2 shrink the window from the left */
            if (mpp.size() > 2) {
                mpp.put(fruits[left], mpp.get(fruits[left]) - 1);
                if (mpp.get(fruits[left]) == 0) {
                    mpp.remove(fruits[left]);
                }
                left++;
            }

            /* If number of different fruits
            is at most 2, update maxLen */
            if (mpp.size() <= 2) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            right++;
        }

        // Return the maximum fruit
        return maxLen;
    }

    public static int totalFruits2(int[] fruits) {
        // Length of the input array
        int n = fruits.length;

        /* Variable to store the
        maximum length of substring */
        int maxLen = 0;

        /* Map to track the count of each
        fruit type in the current window */
        HashMap<Integer, Integer> mpp = new HashMap<>();

        // Pointers for the sliding window approach
        int left = 0, right = 0;

        while (right < n) {
            mpp.put(fruits[right], mpp.getOrDefault(fruits[right], 0) + 1);

            /* If number of different fruits exceeds
             2 shrink the window from the left */
            if (mpp.size() > 2) {
                while (mpp.size() > 2) {
                    mpp.put(fruits[left], mpp.get(fruits[left]) - 1);
                    if (mpp.get(fruits[left]) == 0) {
                        mpp.remove(fruits[left]);
                    }
                    left++;
                }
            }

            /* If number of different fruits
            is at most 2, update maxLen */
            else {
                maxLen = Math.max(maxLen, right - left + 1);
            }
            right++;
        }

        // Return the maximum fruit
        return maxLen;
    }

    public static int totalFruits1(int[] fruits) {
        int maxLen = 0;
        for (int i = 0; i < fruits.length; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < fruits.length; j++) {
                set.add(fruits[j]);
                if (set.size() <= 2) {
                    maxLen = Math.max(maxLen, (j - i) + 1);
                } else {
                    break;
                }
            }
        }
        return maxLen;
    }
}
