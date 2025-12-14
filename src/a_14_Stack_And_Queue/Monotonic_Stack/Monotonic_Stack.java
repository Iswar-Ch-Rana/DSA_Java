package a_14_Stack_And_Queue.Monotonic_Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Monotonic_Stack {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextLargerElement(new int[]{1, 3, 2, 4})));
        System.out.println(Arrays.toString(nextLargerElement(new int[]{6, 8, 0, 1, 3})));
        System.out.println(Arrays.toString(nextLargerElement(new int[]{1, 3, 2})));
    }

    public static int[] nextLargerElement(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = n - 1; i >= 0; i--) {
            int ele = arr[i];

            while (!stack.isEmpty() && stack.peek() <= ele) {
                stack.pop();
            }

            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(ele);
        }

        return ans;
    }
}
