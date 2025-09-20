package a_5_FAQ_Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangleII {
    public static void main(String[] args) {

        PascalTriangleII obj = new PascalTriangleII();
        int[] ans = obj.pascalTriangleII(6);
        int[] ans1 = obj.pascalTriangleII1(6);
        System.out.println("Output 1 = " + Arrays.toString(ans));
        System.out.println("Output 2 = " + Arrays.toString(ans1));
    }

    /** Solution 2 TIME - O(N) SPACE - O(N) */
    public int[] pascalTriangleII1(int r) {
        int[] row = new int[r];
        row[0] = 1;

        for (int i = 1; i < r; i++) {
            row[i] = (int)((long)row[i-1] * (r - i) / i);
        }

        return row;
    }

    /** Solution 1 TIME - O(N^2) SPACE - O(N) */
    public int[] pascalTriangleII(int r) {
        List<Integer> ans = new ArrayList<>();

        int i = 1;
        while (i <= r) {
            ans.add(NcR(r-1, i-1));
            i++;
        }
        return ans.stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }

    public int NcR(int n, int r) {
        if (r > n - r) r = n - r;
        if (r == 1) return n;

        long res = 1;
        for (int i = 0; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }
        return (int) res;
    }
}
