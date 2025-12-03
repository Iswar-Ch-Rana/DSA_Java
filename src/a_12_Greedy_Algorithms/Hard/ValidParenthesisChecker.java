package a_12_Greedy_Algorithms.Hard;

public class ValidParenthesisChecker {
    public static void main(String[] args) {
        System.out.println(isValid("(*))"));
        System.out.println(isValid("*(()"));
        System.out.println(isValid("(**()))"));
    }

    public static boolean isValid(String s) {
        int min = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                min++;
                max++;
            } else if (c == ')') {
                min--;
                max--;
            } else {
                min--;
                max++;
            }
            if (min < 0) min = 0;
            if (max < 0) return false;
        }
        return min == 0;
    }

    public static boolean isValid1(String s) {
        return solve(s, 0, 0);
    }

    private static boolean solve(String s, int index, int count) {
        if (count < 0) return false;

        if (index == s.length()) return count == 0;

        // case 1 (
        if (s.charAt(index) == '(') {
            return solve(s, index + 1, count + 1);
        }
        //  case 2 )
        if (s.charAt(index) == ')') {
            return solve(s, index + 1, count - 1);
        }
        return solve(s, index + 1, count + 1) ||
                solve(s, index + 1, count - 1) ||
                solve(s, index + 1, count);
    }
}
