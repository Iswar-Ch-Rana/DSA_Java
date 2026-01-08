package a_14_Stack_And_Queue.FAQs;

import java.util.Stack;

public class LargestRectangleInAHistogram {
    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(largestRectangleArea(new int[]{3, 5, 1, 7, 5, 9}));
        System.out.println(largestRectangleArea(new int[]{2, 4}));
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>();
        int maxArea = 0;

        for (int i = 0; i < heights.length; i++) {
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                int idx = st.pop();  // index, NOT height
                int prevSmaller = st.isEmpty() ? -1 : st.peek();
                int width = i - prevSmaller - 1;
                maxArea = Math.max(maxArea, heights[idx] * width);
            }
            st.push(i);
        }

        while (!st.isEmpty()) {
            int idx = st.pop();
            int prevSmaller = st.isEmpty() ? -1 : st.peek();
            int width = heights.length - prevSmaller - 1;
            maxArea = Math.max(maxArea, heights[idx] * width);
        }

        return maxArea;
    }
}
