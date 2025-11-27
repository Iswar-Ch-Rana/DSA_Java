package a_11_BitManipulation.Problems;

public class MinBitsFlip {
    public static void main(String[] args) {
        System.out.println(minBitsFlip(10, 7));
        System.out.println(minBitsFlip(3, 4));
        System.out.println(minBitsFlip(1, 7));
    }

    public static int minBitsFlip(int start, int goal) {
        //your code goes here
        int XOR = start ^ goal;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((XOR & 1) == 1)
                count++;

            XOR = XOR >> 1;
        }
        return count;
    }
}
