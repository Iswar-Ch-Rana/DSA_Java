package a_8_BinarySearch.Fundamentals;

public class LowerBound {
    public static void main(String[] args) {
        LowerBound obj = new LowerBound();
        int[] arr1 = {1, 2, 2, 3};
        int[] arr2 = {3, 5, 8, 15, 19};
        int[] arr3 = {3, 5, 8, 15, 19};
        int[] arr4 = {-58210, 52968, 57654, 84387};

        System.out.println(obj.lowerBound(arr1, 2));
        System.out.println(obj.lowerBound(arr2, 9));
        System.out.println(obj.lowerBound(arr3, 3));
        System.out.println(obj.lowerBound(arr4, 89401));
    }

    public int lowerBound(int[] nums, int x) {
        int start = 0;
        int end = nums.length - 1;
        int ans = -1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] >= x) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return (ans == -1) ? nums.length : ans;
    }
}
