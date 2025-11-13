package a_9_Recursion.FAQ_Hard;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {
    public static void main(String[] args) {
        PalindromePartitioning obj = new PalindromePartitioning();

        System.out.println(obj.partition("aabaa"));
        System.out.println(obj.partition("baa"));
        System.out.println(obj.partition("ab"));
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        solve(s, new ArrayList<>(), result);
        return result;
    }

    private void solve(String remaining, List<String> current, List<List<String>> result) {
        // ✅ Base case
        if (remaining.isEmpty()) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Try every possible partition
        for (int i = 0; i < remaining.length(); i++) {
            String prefix = remaining.substring(0, i + 1);

            // ✅ Only proceed if prefix is palindrome
            if (isPalindrome(prefix)) {
                current.add(prefix); // choose
                solve(remaining.substring(i + 1), current, result); // explore
                current.remove(current.size() - 1); // unchoose (backtrack)
            }
        }
    }

    // ✅ Helper function to check palindrome
    private boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--))
                return false;
        }
        return true;
    }
}
