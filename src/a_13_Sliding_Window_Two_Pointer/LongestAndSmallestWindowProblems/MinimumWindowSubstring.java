package a_13_Sliding_Window_Two_Pointer.LongestAndSmallestWindowProblems;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(minWindow("a", "a"));
        System.out.println(minWindow("aAbBDdcC", "Bc"));
    }

    public static String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";

        int[] hash = new int[256];

        // Count characters needed
        for (char c : t.toCharArray()) {
            hash[c]++;
        }

        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int startIndex = -1;
        int required = t.length();

        for (int right = 0; right < s.length(); right++) {
            char rc = s.charAt(right);

            // If this character is needed
            if (hash[rc] > 0) {
                required--;
            }
            hash[rc]--;  // Always decrement

            // When we have all characters inside window
            while (required == 0) {
                // Try to update minimum window size
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    startIndex = left;
                }

                char lc = s.charAt(left);
                hash[lc]++;   // We're removing it from window

                // If this character becomes required again
                if (hash[lc] > 0) {
                    required++;
                }

                left++;
            }
        }

        return (startIndex == -1) ? "" : s.substring(startIndex, startIndex + minLength);
    }

    // O (N ^ 3)
    public static String minWindow1(String s, String t) {
        int n = s.length();
        int minLen = Integer.MAX_VALUE;
        String result = "";

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j <= n; j++) {
                String sub = s.substring(i, j);

                if (containsAllChars(sub, t)) {
                    if (sub.length() < minLen) {
                        minLen = sub.length();
                        result = sub;
                    }
                }
            }
        }
        return result;
    }

    private static boolean containsAllChars(String sub, String t) {
        int[] freq = new int[128];

        for (char c : sub.toCharArray()) {
            freq[c]++;
        }

        for (char c : t.toCharArray()) {
            if (freq[c] == 0) return false;
            freq[c]--;
        }

        return true;
    }
}
