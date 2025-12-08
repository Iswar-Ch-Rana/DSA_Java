package a_13_Sliding_Window_Two_Pointer.LongestAndSmallestWindowProblems;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        System.out.println(characterReplacement("BAABAABBBAAA", 2));
        System.out.println(characterReplacement("AABABBA", 1));
        System.out.println(characterReplacement("ABCDEF", 1));
    }
    /*
    Given an integer k and a string s, any character in the string can be selected
    and changed to any other uppercase English character. This operation can
    be performed up to k times. After completing these steps, return the
    length of the longest substring that contains the same letter.
    */

    public static int characterReplacement(String s, int k) {
        int maxLen = 0;
        /* Variable to track the maximum frequency
        of any single character in the current window*/
        int maxFreq = 0;
        int left = 0, right = 0;
        // Hash array to count frequencies of characters
        int[] hash = new int[26];
        while (right < s.length()) {
            /* Update frequency of current
            character in the hash array*/
            hash[s.charAt(right) - 'A']++;

            // Update max frequency encountered
            maxFreq = Math.max(maxFreq, hash[s.charAt(right) - 'A']);

            // Check if current window is invalid
            if ((right - left + 1) - maxFreq > k) {
                /* Slide the left pointer to
                make the window valid again*/
                hash[s.charAt(left) - 'A']--;

                // Move left pointer forward
                left++;
            }

            /* Update maxLen with the length
            of the current valid substring*/
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }

        /* Return the maximum length of substring
        with at most k characters replaced*/
        return maxLen;
    }
}
