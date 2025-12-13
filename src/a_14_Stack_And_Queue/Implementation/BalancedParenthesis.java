package a_14_Stack_And_Queue.Implementation;

import java.util.Stack;

public class BalancedParenthesis {
    public static void main(String[] args) {
        System.out.println(isValid("()[{}()]"));
        System.out.println(isValid("[("));
        System.out.println(isValid("{[()]}"));
    }

    public static boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {

            // If opening bracket → push
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                // If closing bracket → stack must NOT be empty
                if (stack.isEmpty()) return false;

                char top = stack.peek();

                // Check if matching pair
                if ((ch == ')' && top == '(') ||
                        (ch == '}' && top == '{') ||
                        (ch == ']' && top == '[')) {

                    stack.pop();
                } else {
                    return false; // MISMATCHED bracket
                }
            }
        }
        // If stack empty → valid
        return stack.isEmpty();
    }
}
