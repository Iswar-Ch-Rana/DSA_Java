package a_11_BitManipulation.Problems;

public class SingleNumberI {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[]{1, 2, 2, 4, 3, 1, 4}));
        System.out.println(singleNumber(new int[]{5}));
        System.out.println(singleNumber(new int[]{1, 3, 10, 3, 5, 1, 5}));

    }

    public static int singleNumber(int[] nums) {
        //your code goes here
        int XOR = 0;
        for (int num : nums) {
            XOR ^= num;
        }
        return XOR;
    }
}
