package a_13_Sliding_Window_Two_Pointer.LongestAndSmallestWindowProblems;

import java.util.Arrays;

public class LongestNonRepeatingSubstring {
    public static void main(String[] args) {
        System.out.println(longestNonRepeatingSubstring("abcddabac"));
    }

    // Solution 2
    public static int longestNonRepeatingSubstring(String s) {
        int maxCount = 0;
        int left = 0, right = 0;
        int[] hash = new int[256];
        Arrays.fill(hash, -1);
        while (right < s.length()) {
            char ch = s.charAt(right);
            /* If current character s.charAt(r)
               is already in the substring */
            if (hash[ch] >= left) {
                /* Move left pointer to the right
                   of the last occurrence of s.charAt(r) */
                left = Math.max(hash[ch] + 1, left);
            }
            // Calculate the current substring length
            int len = right - left + 1;

            // Update maximum length found so far
            maxCount = Math.max(len, maxCount);

            /* Store the index of the current
               character in the hash table */
            hash[ch] = right;

            // Move right pointer to next position
            right++;
        }
        return maxCount;
    }

    // Solution 1
    public static int longestNonRepeatingSubstring1(String s) {
        int maxCount = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] hash = new int[256];
            for (int j = i; j < s.length(); j++) {
                if (hash[s.charAt(j)] == 1) {
                    break;
                }
                int len = j - i + 1;

                hash[s.charAt(j)] = 1;

                maxCount = Math.max(maxCount, len);
            }
        }
        return maxCount % 100000007;
    }
}
