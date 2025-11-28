package a_11_BitManipulation.Problems;

import java.util.*;

public class SingleNumberIII {
    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 2};
        System.out.println(Arrays.toString(singleNumber(nums))); // Output: [3, 5]
        System.out.println(Arrays.toString(singleNumber1(nums))); // Output: [3, 5]

    }

    public static int[] singleNumber(int[] nums) {
        int xor = 0;

        // Step 1: XOR of all numbers -> gives a ^ b (unique numbers)
        for (int num : nums) {
            xor ^= num;
        }

        // Step 2: Get rightmost set bit
        int rightmostSetBit = xor & -xor;

        // Step 3: Divide numbers in two groups and find unique ones
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & rightmostSetBit) != 0) {
                num1 ^= num; // group where bit is set
            } else {
                num2 ^= num; // group where bit is not set
            }
        }

        // Step 4: Return in sorted order
        int[] result = new int[]{num1, num2};
        Arrays.sort(result);
        return result;
    }

    public static int[] singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new TreeMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>(2);
        for (Map.Entry<Integer,Integer> mp : map.entrySet()) {
            if (mp.getValue() == 1) {
                ans.add(mp.getKey());
            }
        }
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
}

