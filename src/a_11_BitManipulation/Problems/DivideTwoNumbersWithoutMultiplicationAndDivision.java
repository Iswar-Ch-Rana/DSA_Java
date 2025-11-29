package a_11_BitManipulation.Problems;

public class DivideTwoNumbersWithoutMultiplicationAndDivision {
    public static void main(String[] args) {
        System.out.println(divide(10, 3));   // 3
        System.out.println(divide(7, -3));   // -2
        System.out.println(divide(7, 2));    // 3
        System.out.println(divide(-2147483648, -1)); // 2147483647
    }

    public static int divide(int dividend, int divisor) {
        // Edge case: handle overflow when result exceeds int range
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;

        // Sign of result
        boolean isNegative = (dividend < 0) ^ (divisor < 0);

        // Convert to positive long to avoid overflow during abs()
        long dvd = Math.abs((long) dividend);
        long dvs = Math.abs((long) divisor);
        int ans = 0;

        // Bit manipulation division
        while (dvd >= dvs) {
            int pow = 0;

            // Find the highest shift such that (dvs << (pow+1)) still <= dvd
            while (dvd >= (dvs << (pow + 1))) {
                pow++;
            }

            // Add 2^pow to result (quotient)
            ans += (1 << pow);

            // Instead of using multiplication, use bit shifting!
            dvd -= (dvs << pow);
        }

        // Apply sign properly
        ans = isNegative ? -ans : ans;

        return ans;
    }
}
