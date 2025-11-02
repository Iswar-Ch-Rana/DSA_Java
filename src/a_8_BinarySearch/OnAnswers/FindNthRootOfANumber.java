package a_8_BinarySearch.OnAnswers;

public class FindNthRootOfANumber {
    public static void main(String[] args) {
        FindNthRootOfANumber obj = new FindNthRootOfANumber();
        System.out.println(obj.NthRoot(3, 27));  // 3
        System.out.println(obj.NthRoot(4, 69));  // -1
        System.out.println(obj.NthRoot(4, 81));  // 3
        System.out.println(obj.NthRoot(1, 14));  // 14
    }

    // Optimal Approach — O(log M * log N)
    public int NthRoot(int n, int m) {
        if (m == 0 || m == 1) return m; // base cases

        int low = 1, high = m;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            long midPow = power(mid, n, m); // avoid overflow

            if (midPow == m) {
                return mid;
            } else if (midPow > m || midPow == -1) {
                // -1 means overflow occurred (mid^n exceeded m)
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // Efficient power function with overflow guard
    private long power(int base, int exp, int limit) {
        long result = 1;
        for (int i = 1; i <= exp; i++) {
            result *= base;
            if (result > limit) return -1; // overflow or no need to continue
        }
        return result;
    }

    // Brute Force — O(M * log N)
    public int NthRoot1(int n, int m) {
        for (int i = 1; i <= m; i++) {
            long power = power(i, n, m);
            if (power == m)
                return i;
            if (power == -1)
                break;
        }
        return -1;
    }
}
