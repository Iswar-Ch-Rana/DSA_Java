package a_14_Stack_And_Queue.FAQs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StockSpanProblem {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(stockSpan(new int[]{120, 100, 60, 80, 90, 110, 115}, 7)));
        System.out.println(Arrays.toString(stockSpan(new int[]{15, 13, 12, 14, 16, 20}, 6)));
        System.out.println(Arrays.toString(stockSpan(new int[]{30, 20, 25, 28, 27, 29, 35, 40}, 8)));
    }

    public static int[] stockSpan(int[] arr, int n) {
        List<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {

            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                list.add(i + 1);
            } else {
                list.add(i - stack.peek());
            }

            stack.push(i);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}
