package a_9_Recursion.FAQ_Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterCombinationsOfAPhoneNumber {
    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNumber obj = new LetterCombinationsOfAPhoneNumber();

        System.out.println(obj.letterCombinations("34"));
        System.out.println(obj.letterCombinations("3"));
        System.out.println(obj.letterCombinations("8"));
    }

    public List<String> letterCombinations(String digits) {

        // Mapping digit -> possible characters
        HashMap<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> ans = new ArrayList<>();

        // If empty input, return empty result
        if (digits.length() == 0) return ans;

        // Start recursive generation
        solve(digits, map, 0, new StringBuilder(), ans);
        return ans;
    }

    private void solve(String digits, HashMap<Character, String> map,
                       int index, StringBuilder temp, List<String> ans) {

        // When we have formed a full combination (one letter per digit)
        if (index == digits.length()) {
            ans.add(temp.toString());
            return;
        }

        // Get the possible letters for the current digit
        String letters = map.get(digits.charAt(index));

        // Try each letter one-by-one
        for (int i = 0; i < letters.length(); i++) {

            // Choose the letter
            temp.append(letters.charAt(i));

            // Recurse for the next digit
            solve(digits, map, index + 1, temp, ans);

            // Backtrack (remove last added char)
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
