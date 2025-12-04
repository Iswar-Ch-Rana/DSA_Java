package a_13_Sliding_Window_Two_Pointer.Therory;

public class SlidingWindowSum {
    public static void slidingWindowSum(int[] arr, int k) {
        int n = arr.length;
        int sum = 0;

        // Calculate the sum of the initial window
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }

        // Print the sum of the first window
        System.out.println("Sum of window 1: " + sum);

        // Slide the window across the array
        for (int i = k; i < n; i++) {
            // Update the sum
            sum = sum - arr[i - k] + arr[i];
            System.out.println("Sum of window " + (i - k + 2) + ": " + sum);
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 6, 4, 8, 5};
        int k = 3;
        slidingWindowSum(arr, k);
    }
}
