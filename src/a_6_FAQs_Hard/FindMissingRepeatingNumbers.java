package a_6_FAQs_Hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindMissingRepeatingNumbers {
    public static void main(String[] args) {
        FindMissingRepeatingNumbers obj = new FindMissingRepeatingNumbers();
        int[] arr1 = {3, 5, 4, 1, 1};
        int[] arr2 = {1, 2, 3, 6, 7, 5, 7};
        int[] arr3 = {6, 5, 7, 1, 8, 6, 4, 3, 2};
        System.out.println(Arrays.toString(obj.findMissingRepeatingNumbers1(arr1)));
        System.out.println(Arrays.toString(obj.findMissingRepeatingNumbers1(arr2)));
        System.out.println(Arrays.toString(obj.findMissingRepeatingNumbers1(arr3)));
        System.out.println();
        System.out.println(Arrays.toString(obj.findMissingRepeatingNumbers2(arr1)));
        System.out.println(Arrays.toString(obj.findMissingRepeatingNumbers2(arr2)));
        System.out.println(Arrays.toString(obj.findMissingRepeatingNumbers2(arr3)));
    }

    // Solution 2 TIME - O(2N) SPACE - O(N)
    public int[] findMissingRepeatingNumbers2(int[] nums) {
        // Size of the array
        long n = nums.length;

        // Sum of first n natural numbers
        long SN = (n * (n + 1)) / 2;

        // Sum of squares of first n natural numbers
        long S2N = (n * (n + 1) * (2 * n + 1)) / 6;

        /* Calculate actual sum (S) and sum
           of squares (S2) of array elements */
        long S = 0, S2 = 0;
        for (int i = 0; i < n; i++) {
            S += nums[i];
            S2 += (long) nums[i] * (long) nums[i];
        }

        // Compute the difference values
        long val1 = S - SN;

        // S2 - S2n = X^2 - Y^2
        long val2 = S2 - S2N;

        // Calculate X + Y using X + Y = (X^2 - Y^2) / (X - Y)
        val2 = val2 / val1;

        /* Calculate X and Y from X + Y and X - Y
           X = ((X + Y) + (X - Y)) / 2
           Y = X - (X - Y) */
        long x = (val1 + val2) / 2;
        long y = x - val1;

        // Return the results as [repeating, missing]
        return new int[]{(int) x, (int) y};
    }


    // Solution 1 TIME - O(2N) SPACE - O(N)
    public int[] findMissingRepeatingNumbers1(int[] nums) {
        int[] result = new int[2];
        long actualSum = 0;
        int n = nums.length;

        HashMap<Integer, Integer> count = new HashMap<>();

        for (int num : nums) {
            count.put(num, count.getOrDefault(num, 0) + 1);
            actualSum += num;
        }

        // Iterate the map and get which count is 2 is the repeated number

        for (Map.Entry<Integer, Integer> mp : count.entrySet()) {
            if (mp.getValue().equals(2)) {
                result[0] = mp.getKey();
                break;
            }
        }
        int expectedSum = (n * (n + 1)) / 2;
        result[1] = (int) (expectedSum - (actualSum - result[0]));
        return result;
    }
}
