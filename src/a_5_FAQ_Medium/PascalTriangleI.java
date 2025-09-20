package a_5_FAQ_Medium;

public class PascalTriangleI {
    public static void main(String[] args) {

        PascalTriangleI obj = new PascalTriangleI();
        int ans = obj.pascalTriangleI(6, 2);
        System.out.println("Output = " + ans);
    }

    public int pascalTriangleI(int r, int c) {
        return NcR(r - 1, c - 1);
    }

    // Calculate nCr using iterative method avoiding overflow
    public int NcR(int n, int r) {
        // Choose the smaller value for lesser iterations
        if (r > n - r) r = n - r;
        // base case
        if (r == 1) return n;

        long res = 1;
        for (int i = 0; i < r; i++) {
            res = res * (n - i);
            res = res / (i + 1);
        }
        return (int) res;
    }
}
