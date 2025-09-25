package a_6_FAQs_Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MajorityElementTwo {
    public static void main(String[] args) {
        MajorityElementTwo obj = new MajorityElementTwo();
        int[] arr1 = {1, 2, 1, 1, 3, 2};
        int[] arr2 = {1, 2, 1, 1, 3, 2, 2};
        int[] arr3 = {1, 2, 1, 1, 3, 2, 2, 3};
        System.out.println(obj.majorityElementTwo1(arr1));
        System.out.println(obj.majorityElementTwo1(arr2));
        System.out.println(obj.majorityElementTwo1(arr3));

        System.out.println(obj.majorityElementTwo2(arr1));
        System.out.println(obj.majorityElementTwo2(arr2));
        System.out.println(obj.majorityElementTwo2(arr3));
    }

    // Solution 2 TIME - O(N) SPACE - O(1)
    public List<Integer> majorityElementTwo2(int[] nums) {
    // Size of the array
        int n = nums.length;

        // Counts for elements el1 and el2
        int cnt1 = 0, cnt2 = 0;

        /*Initialize Element 1 and
        Element 2 with INT_MIN value*/
        int el1 = Integer.MIN_VALUE, el2 = Integer.MIN_VALUE;

        /*Find the potential candidates using
        Boyer Moore's Voting Algorithm*/
        for (int i = 0; i < n; i++) {
            if (cnt1 == 0 && el2 != nums[i]) {
                cnt1 = 1;
                // Initialize el1 as nums[i]
                el1 = nums[i];
            } else if (cnt2 == 0 && el1 != nums[i]) {
                cnt2 = 1;
                // Initialize el2 as nums[i]
                el2 = nums[i];
            } else if (nums[i] == el1) {
                // Increment count for el1
                cnt1++;
            } else if (nums[i] == el2) {
                // Increment count for el2
                cnt2++;
            } else {
                // Decrement count for el1
                cnt1--;
                // Decrement count for el2
                cnt2--;
            }
        }

        //Validate the candidates by counting occurrences in nums:
        //Reset counts for el1 and el2
        cnt1 = 0;
        cnt2 = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == el1) {
                // Count occurrences of el1
                cnt1++;
            }
            if (nums[i] == el2) {
                // Count occurrences of el2
                cnt2++;
            }
        }

        /* Determine the minimum count
        required for a majority element*/
        int mini = n / 3 + 1;

        // List of answers
        List<Integer> result = new ArrayList<>();

        /*Add elements to the result vector
        if they appear more than n/3 times*/
        if (cnt1 >= mini) {
            result.add(el1);
        }
        if (cnt2 >= mini && el1 != el2) {
            // Avoid adding duplicate if el1 == el2
            result.add(el2);
        }

        // Uncomment the following line if you want to sort the answer array
        // Collections.sort(result); // TC --> O(2*log2) ~ O(1);

        //return the majority elements
        return result;
    }

    // Solution 1 TIME - O(N) SPACE - O(N)
    public List<Integer> majorityElementTwo1(int[] nums) {
        int n = nums.length;

        List<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > n / 3) {
                ans.add(entry.getKey());
            }
        }
        return ans;
    }
}
