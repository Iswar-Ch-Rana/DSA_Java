package a_4_LogicBuilding;

public class MissingNumber {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 6};
        int missingNum = missingNumber(nums);
        System.out.println("Missing Number : " + missingNum);
    }

    public static int missingNumber(int[] nums) {
        double size = nums.length;
        double sumOfNumber = (size * ((size + 1) / 2));
        for (int num : nums) {
            sumOfNumber -= num;
        }
        return (int) sumOfNumber;
    }
}
