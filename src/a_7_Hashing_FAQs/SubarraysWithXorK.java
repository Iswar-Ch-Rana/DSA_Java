package a_7_Hashing_FAQs;

import java.util.HashMap;
import java.util.Map;

public class SubarraysWithXorK {
    public static void main(String[] args) {
        SubarraysWithXorK obj = new SubarraysWithXorK();
        System.out.println(obj.subarraysWithXorK(new int[]{4, 2, 2, 6, 4}, 6));
        System.out.println(obj.subarraysWithXorK(new int[]{5, 6, 7, 8, 9}, 5));
        System.out.println(obj.subarraysWithXorK(new int[]{5, 2, 9}, 7));
    }

    public int subarraysWithXorK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // base case

        int count = 0;
        int prefixXor = 0;

        for (int num : nums) {
            prefixXor ^= num;  // cumulative XOR

            int required = prefixXor ^ k;

            if (map.containsKey(required)) {
                count += map.get(required);
            }

            map.put(prefixXor, map.getOrDefault(prefixXor, 0) + 1);
        }

        return count;
    }
}
