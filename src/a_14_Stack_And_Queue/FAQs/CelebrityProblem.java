package a_14_Stack_And_Queue.FAQs;

public class CelebrityProblem {
    public static void main(String[] args) {
        System.out.println(celebrity(new int[][]{{0, 1, 1, 0}, {0, 0, 0, 0}, {1, 1, 0, 0}, {0, 1, 1, 0}}));
        System.out.println(celebrity(new int[][]{{0, 1}, {1, 0}}));
        System.out.println(celebrity(new int[][]{{0, 1, 0}, {0, 0, 0}, {0, 1, 0}}));
    }

    public static int celebrity(int[][] M) {
        int top = 0;
        int down = M.length - 1;

        while (top < down) {
            if (M[top][down] == 1) {
                top++;
            } else if (M[down][top] == 1) {
                down--;
            } else {
                top++;
                down--;
            }
        }

        if (top == down) {
            for (int i = 0; i < M.length; i++) {
                if (i == top) continue;
                if (M[top][i] == 1 || M[i][top] == 0) {
                    return -1;
                }
            }
            return top;
        }
        return -1;
    }
}
