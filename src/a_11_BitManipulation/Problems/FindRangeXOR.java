package a_11_BitManipulation.Problems;

public class FindRangeXOR {
    public static void main(String[] args) {
        System.out.println(findRangeXOR(3, 5));
        System.out.println(findRangeXOR(1, 3));
        System.out.println(findRangeXOR(4, 10));
    }

    /*            ans(XOR)
     * N % 4 == 1  = 1
     * N % 4 == 2  = N+1
     * N % 4 == 3  = 0
     * N % 4 == 0  = N
     * */
    public static int findRangeXOR(int l, int r) {
        // mode of 0 to n
        int ansR = xorOf0toN(r);
        int ansL = xorOf0toN(l - 1);
        return ansL ^ ansR;
    }

    public static int xorOf0toN(int n) {
        int mod = n % 4;
        return switch (mod) {
            case 1 -> 1;
            case 2 -> n + 1;
            case 3 -> 0;
            default -> n;
        };

    }

    public static int findRangeXOR1(int l, int r) {
        int xorValue = 0;
        for (int i = l; i <= r; i++) {
            xorValue ^= i;
        }
        return xorValue;
    }
}
