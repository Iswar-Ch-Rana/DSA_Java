package a_13_Sliding_Window_Two_Pointer.LongestAndSmallestWindowProblems;

import java.util.HashMap;

public class LongestSubstringWithAtMostKDistinctCharacters {
    public static void main(String[] args) {
        System.out.println(kDistinctChar("aababbcaacc", 2));
        System.out.println(kDistinctChar("abcddefg", 3));
        System.out.println(kDistinctChar("abccab", 4));
        // Given a string s and an integer k.Find the length of the longest
        // substring with at most k distinct characters.
    }

    public static int kDistinctChar(String s, int k) {
        int left = 0, right = 0;
        int maxLen = 0;

        HashMap<Character, Integer> map = new HashMap<>();
        while (right < s.length()) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            if (map.size() > k) {
                char firstCh = s.charAt(left);
                map.put(firstCh, map.get(firstCh) - 1);
                if (map.get(firstCh) == 0) map.remove(firstCh);
                left++;
            }

            if (map.size() <= k) {
                maxLen = Math.max(right - left + 1, maxLen);
            }

            right++;
        }
        return maxLen;
    }
}
