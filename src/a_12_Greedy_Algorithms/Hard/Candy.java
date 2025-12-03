package a_12_Greedy_Algorithms.Hard;

public class Candy {
    public static void main(String[] args) {
        System.out.println(candy(new int[]{1, 0, 5}));
        System.out.println(candy(new int[]{1, 2, 2}));
        System.out.println(candy(new int[]{1, 2, 1, 4, 5}));
    }

    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        // Step 1: left → right (ensure right-child has more candies if rating is higher)
        candies[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i-1]) {
                candies[i] = candies[i-1] + 1;
            } else {
                candies[i] = 1;
            }
        }

        // Step 2: right → left (ensure left-child has more candies if rating is higher)
        int sum = candies[n-1];
        for (int i = n-2; i >= 0; i--) {
            if (ratings[i] > ratings[i+1]) {
                candies[i] = Math.max(candies[i], candies[i+1] + 1);
            }
            sum += candies[i];
        }

        return sum;
    }
}
