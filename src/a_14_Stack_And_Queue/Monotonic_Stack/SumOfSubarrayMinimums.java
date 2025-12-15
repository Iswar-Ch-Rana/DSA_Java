package a_14_Stack_And_Queue.Monotonic_Stack;

import java.util.Stack;

public class SumOfSubarrayMinimums {
    public static void main(String[] args) {
        System.out.println(sumSubarrayMins(new int[]{3, 1, 2, 5}));
        System.out.println(sumSubarrayMins(new int[]{2, 3, 1}));
        System.out.println(sumSubarrayMins(new int[]{11, 81, 94, 43, 3}));
    }

    public static int sumSubarrayMins(int[] arr) {
        int MOD = 1_000_000_007;
        int n = arr.length;

        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Previous Less Element (strictly less)
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Next Less Element (less or equal)
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = (ans + (long) arr[i] * left[i] * right[i]) % MOD;
        }

        return (int) ans;
    }
}
