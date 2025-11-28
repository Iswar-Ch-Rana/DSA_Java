package a_11_BitManipulation.Problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SingleNumberII {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{2, 2, 2, 3}));
        System.out.println(singleNumber(new int[]{1, 0, 3, 0, 1, 1, 3, 3, 10, 0}));
        System.out.println(singleNumber(new int[]{}));
    }

    public static int singleNumber(int[] nums) {
        // 'ones' stores bits of elements appearing exactly once
        // 'twos' stores bits of elements appearing exactly twice
        int ones = 0, twos = 0;

        // Traverse all numbers
        for (int num : nums) {

            // Step 1: XOR current number with 'ones'
            //         But only for bits not set in 'twos' (to avoid adding a twice-occurring number)
            ones = (ones ^ num) & ~twos;

            // Step 2: XOR current number with 'twos'
            //         But only for bits not present in updated 'ones'
            //         Because if bit is now in 'ones', it means it appeared once, so it shouldn't go in 'twos'
            twos = (twos ^ num) & ~ones;
        }

        // At the end, 'ones' will hold the bits of the number that appeared only once.
        return ones;
    }


    public static int singleNumber3(int[] nums) {
        if (nums.length == 0) return 0;
        // Variable to store size of array
        int n = nums.length;

        // Sorting the array
        Arrays.sort(nums);

        // Traversing the array
        for (int i = 1; i < nums.length; i += 3) {
            /* Checking the elements
            in the bucket */
            if (nums[i] != nums[i - 1]) {
                // Return the single number
                return nums[i - 1];
            }
        }

        /* If not found till now, then
        the last number will be single */
        return nums[n - 1];
    }

    public static int singleNumber2(int[] nums) {
        int ans = 0;
        for (int bitIndex = 0; bitIndex < 32; bitIndex++) {
            int count = 0;
            for (int num : nums) {
                if ((num & (1 << bitIndex)) != 0) {
                    count++;
                }
            }
            if (count % 3 != 0) {
                ans |= (1 << bitIndex);
            }
        }
        return ans;
    }

    // Solution 1 TIME - O(N) SPACE - O(N)
    public static int singleNumber1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> mp : map.entrySet()) {
            if (mp.getValue() == 1) {
                return mp.getKey();
            }
        }
        return -1;
    }
}
