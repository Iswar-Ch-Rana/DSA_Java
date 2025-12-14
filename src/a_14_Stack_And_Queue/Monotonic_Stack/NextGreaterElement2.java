package a_14_Stack_And_Queue.Monotonic_Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextGreaterElement2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{3, 10, 4, 2, 1, 2, 6, 1, 7, 2, 9})));
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{5, 7, 1, 7, 6, 0})));
        System.out.println(Arrays.toString(nextGreaterElements(new int[]{1, 2, 3, 4, 5})));
    }

    public static int[] nextGreaterElements(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!deque.isEmpty() && deque.peek() <= arr[i % n]) {
                deque.pop();
            }

            if (i < n) {
                ans[i] = deque.isEmpty() ? -1 : deque.peek();
            }
            deque.push(arr[i % n]);
        }
        return ans;
    }
}
