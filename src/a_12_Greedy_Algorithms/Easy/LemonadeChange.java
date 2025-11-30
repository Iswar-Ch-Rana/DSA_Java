package a_12_Greedy_Algorithms.Easy;

public class LemonadeChange {
    public static void main(String[] args) {
        System.out.println(lemonadeChange(new int[]{5, 5, 10, 5, 20}));
        System.out.println(lemonadeChange(new int[]{5, 5, 10, 10, 20}));
        System.out.println(lemonadeChange(new int[]{5, 5, 10, 20}));
    }

    public static boolean lemonadeChange(int[] bills) {
        int countFive = 0; // Number of $5 bills we possess
        int countTen = 0;  // Number of $10 bills we possess

        for (int bill : bills) {
            if (bill == 5) {
                // 1. Received a $5 bill
                countFive++;

            } else if (bill == 10) {
                // 2. Received a $10 bill, need to give $5 change
                if (countFive < 1) {
                    return false;
                }
                countFive--;
                countTen++;

            } else { // bill == 20
                // 3. Received a $20 bill, need to give $15 change.

                // Priority 1: Use one $10 bill and one $5 bill (10 + 5 = 15)
                if (countTen >= 1 && countFive >= 1) {
                    countTen--; // Use one $10
                    countFive--; // Use one $5

                    // No need to increment countTwenty since $20 bills are not used for change

                    // Priority 2: Use three $5 bills (5 + 5 + 5 = 15)
                } else if (countFive >= 3) {
                    countFive -= 3;

                } else {
                    // Cannot make $15 change with available bills
                    return false;
                }
            }
        }
        return true;
    }
}
