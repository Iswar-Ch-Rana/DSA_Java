package a_5_FAQ_Medium;

import java.util.*;

public class LeadersInAnArray {
    public static void main(String[] args) {
        int[] arr = {0,0,0,0};
        System.out.println(leaders(arr));
    }

    /**
     * Solution - 2 BruteForce Time - O(n)  Space - O(n)
     */
    public static List<Integer> leaders(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int largest = nums[nums.length-1];
        ans.add(largest);
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > largest) {
                ans.add(nums[i]);
                largest = nums[i];
            }
        }
        Collections.reverse(ans);
        return ans;
    }

    /**
     * Solution - 1 BruteForce Time - O(n^2)  Space - O(n)
     */

    public static List<Integer> leaders1(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int largest = nums[i];
            boolean isLargest = true;
            for (int j = i + 1; j < nums.length; j++) {
                if (largest <= nums[j]) {
                    isLargest = false;
                    break;
                }
            }
            if (isLargest) {
                ans.add(largest);
            }
        }
        return ans;
    }
}
