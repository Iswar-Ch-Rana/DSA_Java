package a_8_BinarySearch.OnAnswers;

import java.util.Arrays;

public class MinimumDaysToMakeMBouquets {
    public static void main(String[] args) {
        MinimumDaysToMakeMBouquets obj = new MinimumDaysToMakeMBouquets();

        System.out.println(obj.roseGarden(8, new int[]{7, 7, 7, 7, 13, 11, 12, 7}, 2, 3));
        System.out.println(obj.roseGarden(8, new int[]{1, 10, 3, 10, 2}, 3, 2));
        System.out.println(obj.roseGarden(8, new int[]{1, 10, 3, 10, 2}, 3, 1));
    }

    public int roseGarden(int n, int[] nums, int m, int k) {
        int startDay = 0;
        int endDays = Arrays.stream(nums).max().getAsInt();

        int minDays = -1;

        while (startDay <= endDays) {
            int mid = startDay + (endDays - startDay) / 2;
            if (canMakeBouquets(nums, mid, k) >= m) {
                minDays = mid;
                endDays = mid - 1;
            } else {
                startDay = mid + 1;
            }
        }
        return minDays;
    }

    int canMakeBouquets(int[] arr, int mid, int k) {
        int adjacentCount = 0;
        int noOfBouquets = 0;
        for (int days : arr) {
            if (days <= mid) {
                adjacentCount++;
            } else {
                adjacentCount = 0;
            }

            if (adjacentCount == k) {
                noOfBouquets++;
                adjacentCount = 0;
            }
        }
        return noOfBouquets;
    }
}
