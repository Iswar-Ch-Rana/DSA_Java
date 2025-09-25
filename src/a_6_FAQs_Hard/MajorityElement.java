package a_6_FAQs_Hard;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    public static void main(String[] args) {
        MajorityElement obj = new MajorityElement();
        int[] arr1 = {7, 0, 0, 1, 7, 7, 2, 7, 7};
        int[] arr2 = {1, 1, 1, 2, 1, 2};
        int[] arr3 = {-1, -1, -1, -1};
        System.out.println(obj.majorityElement1(arr1));
        System.out.println(obj.majorityElement1(arr2));
        System.out.println(obj.majorityElement1(arr3));

        System.out.println(obj.majorityElement2(arr1));
        System.out.println(obj.majorityElement2(arr2));
        System.out.println(obj.majorityElement2(arr3));
    }

    // Solution 2 TIME = O(N) SPACE = O(1)
    public int majorityElement2(int[] nums) {
        int element = nums[0];
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                count = 1;
                element = num;
            } else if (element == num) {
                count++;
            } else {
                count--;
            }
        }
        return element;
    }

    // Solution 1 TIME = O(N) SPACE = O(N)
    public int majorityElement1(int[] nums) {
        int size = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (var ele : nums) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }
        for (Map.Entry<Integer, Integer> mp : map.entrySet()) {
            if (mp.getValue() > size / 2) {
                return mp.getKey();
            }
        }
        return -1;
    }
}
