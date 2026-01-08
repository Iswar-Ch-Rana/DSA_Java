package a_14_Stack_And_Queue.FAQs;

import java.util.Stack;

public class MaximumRectangles {
    public static void main(String[] args) {
        System.out.println(maximalAreaOfSubMatrixOfAll1(new int[][]{{1, 0, 1, 0, 0}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}, {1, 0, 0, 1, 0}}));
        System.out.println(maximalAreaOfSubMatrixOfAll1(new int[][]{{1}}));
        System.out.println(maximalAreaOfSubMatrixOfAll1(new int[][]{{1, 0, 1, 0, 0}, {1, 0, 1, 1, 1}}));
    }

    public static int maximalAreaOfSubMatrixOfAll1(int[][] matrix) {
        int maxArea = 0;
        int[] heights = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = matrix[i][j] == 1 ? heights[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
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
