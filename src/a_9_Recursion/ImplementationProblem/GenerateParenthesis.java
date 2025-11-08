package a_9_Recursion.ImplementationProblem;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
    public static void main(String[] args) {
        GenerateParenthesis obj = new GenerateParenthesis();

        System.out.println(obj.generateParenthesis(1));
        System.out.println(obj.generateParenthesis(2));
        System.out.println(obj.generateParenthesis(3));
        System.out.println(obj.generateParenthesis(4));
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        solve(new StringBuilder(), 0, 0, n, ans);
        return ans;
    }

    public void solve(StringBuilder s, int open, int close, int n, List<String> ans) {
        if (s.length() == n * 2) {
            ans.add(s.toString());
            return;
        }

        // Add '(' if we can
        if (open < n) {
            s.append('(');
            solve(s, open + 1, close, n, ans);
            s.deleteCharAt(s.length() - 1);
        }

        // Add ')' if valid to close
        if (close < open) {
            s.append(')');
            solve(s, open, close + 1, n, ans);
            s.deleteCharAt(s.length() - 1);
        }
    }
}
