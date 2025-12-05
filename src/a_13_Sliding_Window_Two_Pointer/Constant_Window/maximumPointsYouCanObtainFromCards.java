package a_13_Sliding_Window_Two_Pointer.Constant_Window;

public class maximumPointsYouCanObtainFromCards {
    public static void main(String[] args) {
        System.out.println(maxScore(new int[]{1, 2, 3, 4, 5, 6}, 3));
        System.out.println(maxScore(new int[]{5, 4, 1, 8, 7, 1, 3}, 3));
        System.out.println(maxScore(new int[]{9, 10, 1, 2, 3, 5}, 5));
    }

    public static int maxScore(int[] cards, int k) {
        int n = cards.length;

        // Take first k from left initially
        int leftSum = 0;
        for (int i = 0; i < k; i++) {
            leftSum += cards[i];
        }

        int max = leftSum;
        int rightSum = 0;

        int leftIndex = k - 1;
        int rightIndex = n - 1;

        // Try taking i from right, (k-i) from left
        for (int i = 1; i <= k; i++) {

            // Remove one card from left
            leftSum -= cards[leftIndex--];

            // Add one card from right
            rightSum += cards[rightIndex--];

            max = Math.max(max, leftSum + rightSum);
        }

        return max;
    }
}
