package a_9_Recursion.ImplementationProblem;

public class MyPow {
    public static void main(String[] args) {
        MyPow obj = new MyPow();
        System.out.println(obj.myPow(2.00, 10));
        System.out.println(obj.myPow(2.00, -2));
        System.out.println(obj.myPow(2.50, 2));
        System.out.println(obj.myPow(2, 1000));
    }

    public double myPow(double x, int n) {
        return solve(x, n);
    }

    public double solve(double x, long n) {
        if (n == 0) return 1;
        if (n < 0) return solve(1 / x, -n);
        if (n % 2 == 0) return solve(x * x, n / 2);
        else return x * solve(x * x, (n - 1) / 2);
    }

    // Solution 1 Normal Solution
    public double myPow1(double x, int n) {
        long tempN = n < 0 ? n * -1 : n;
        double ans = 1;
        while (tempN-- != 0) {
            ans = ans * x;
        }
        if (n < 0) {
            return 1 / ans;
        }
        return ans;
    }
}
