package a_14_Stack_And_Queue.FAQs;

public class TrappingRainwater {
    public static void main(String[] args) {
        System.out.println(trap(new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}));
    }

    public static int trap(int[] height) {
        int l = 0, r = height.length - 1;
        int leftMax = 0, rightMax = 0, total = 0;

        while (l < r) {
            if (height[l] <= height[r]) {
                if (leftMax > height[l]) {
                    total += leftMax - height[l];
                } else {
                    leftMax = height[l];
                }
                l++;
            } else {
                if (rightMax > height[r]) {
                    total += rightMax - height[r];
                } else {
                    rightMax = height[r];
                }
                r--;
            }
        }
        return total;
    }
}
