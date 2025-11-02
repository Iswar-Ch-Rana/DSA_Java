package a_8_BinarySearch.OnAnswers;

public class FindSquareRootOfANumber {
    public static void main(String[] args) {
        FindSquareRootOfANumber obj = new FindSquareRootOfANumber();
        System.out.println(obj.floorSqrt(36)); // 6
        System.out.println(obj.floorSqrt(28)); // 5
        System.out.println(obj.floorSqrt(50)); // 7
        System.out.println(obj.floorSqrt(0)); // 0
    }

    public int floorSqrt(int n) {
        if (n == 0 || n == 1) return n;  // handle edge cases

        int low = 1, high = n / 2;
        int ans = 1; // stores the best possible sqrt found so far

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long midSqr = (long) mid * mid; // prevent overflow

            if (midSqr == n) {
                return mid; // perfect square
            } else if (midSqr < n) {
                ans = mid;  // mid is a valid floor candidate
                low = mid + 1; // search right for a possibly larger sqrt
            } else {
                high = mid - 1; // search left
            }
        }
        return ans;
    }
}
