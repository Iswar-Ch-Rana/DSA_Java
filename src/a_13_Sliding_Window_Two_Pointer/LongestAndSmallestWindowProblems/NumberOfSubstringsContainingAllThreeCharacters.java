package a_13_Sliding_Window_Two_Pointer.LongestAndSmallestWindowProblems;

import java.util.Arrays;

public class NumberOfSubstringsContainingAllThreeCharacters {
    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("abcba"));
        System.out.println(numberOfSubstrings("ccabcc"));
        System.out.println(numberOfSubstrings("abccba"));
    }

    public static int numberOfSubstrings(String s) {
        int[] lastSeen = new int[3];
        Arrays.fill(lastSeen, -1);

        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            lastSeen[s.charAt(i) - 'a'] = i;
            count = count + (1 + Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2])));
        }
        return count;
    }

    public static int numberOfSubstrings1(String s) {
        int count = 0;
        int[] hash = new int[3];

        for (int i = 0; i < s.length(); i++) {
            hash[0] = hash[1] = hash[2] = -1;

            for (int j = i; j < s.length(); j++) {
                hash[(s.charAt(j) - 'a')] = 1;
                if (hash[0] + hash[1] + hash[2] == 3) count++;
            }
        }
        return count;
    }
}
