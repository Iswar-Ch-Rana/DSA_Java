package a_1_BasicString;

import java.util.*;

public class frequencySort {
    public static void main(String[] args) {
        String str = "ababa";
        List<Character> ans = frequencySort1(str);
        System.out.println("ans -> " + ans);
    }

    public static List<Character> frequencySort1(String s) {
        // Solution 1 by Me
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // get all the keys
        List<Character> ans = new ArrayList<>(map.keySet());

        // now do frequency sort using comparator
        ans.sort((a, b) -> {
            // a's freq and b's freq if equal then a b
            int freqCompare = map.get(b).compareTo(map.get(a));
            if (freqCompare == 0) {
                return Character.compare(a, b);
            }
            return freqCompare;
        });
        return ans;
    }

}
