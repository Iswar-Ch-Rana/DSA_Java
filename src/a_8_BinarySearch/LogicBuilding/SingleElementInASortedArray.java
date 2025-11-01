package a_8_BinarySearch.LogicBuilding;

public class SingleElementInASortedArray {
    public static void main(String[] args) {
        SingleElementInASortedArray obj = new SingleElementInASortedArray();
        int[] arr1 = {1, 1, 2, 2, 3, 3, 4, 5, 5, 6, 6};
        int[] arr2 = {1, 1, 3, 5, 5};
        int[] arr3 = {1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7};

        System.out.println(obj.singleNonDuplicate(arr1)); // 4
        System.out.println(obj.singleNonDuplicate(arr2)); // 3
        System.out.println(obj.singleNonDuplicate(arr3)); // 7
    }

    /**
     * Finds the single element that appears only once using a modified binary search.
     *
     * <h2>Algorithm Logic:</h2>
     * <ol>
     * <li>Initialize two pointers, {@code low = 0} and {@code high = nums.length - 1}.</li>
     * <li>Loop while {@code low < high}.</li>
     * <li>Calculate the standard middle index: {@code mid = low + (high - low) / 2}.</li>
     * <li><b>Ensure Mid is Even:</b> If {@code mid} is odd, subtract 1 to make it even.
     * This guarantees {@code mid} points to the start of a potential pair.</li>
     * <li><b>Compare:</b> Check if {@code nums[mid] == nums[mid + 1]}.</li>
     * <li><b>If Equal (Paired):</b> The pair {@code (nums[mid], nums[mid + 1])} is complete.
     * The single element must be located *after* this pair. Move the lower bound:
     * {@code low = mid + 2}.</li>
     * <li><b>If Not Equal (Unpaired):</b> The single element must be at {@code mid}
     * or somewhere in the subarray *before* {@code mid}. Move the upper bound to
     * keep {@code mid} in the search space: {@code high = mid}.</li>
     * <li>When the loop terminates ({@code low == high}), the element at this index is
     * the single non-duplicate element.</li>
     * </ol>
     *
     * @param nums The sorted array of integers. All elements appear twice except one.
     * @return The unique single element.
     */

    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            mid = (mid % 2) == 0 ? mid : mid - 1;

            if (nums[mid] == nums[mid + 1]) {
                low = mid + 2;
            } else {
                high = mid;
            }
        }
        return nums[low];
    }

    // I find this one slightly difficult
    public int singleNonDuplicate1(int[] nums) {
        int low = 0, high = nums.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;

            // find right side elements are even or not
            boolean isEven = (high - mid) % 2 == 0;

            if (nums[mid] == nums[mid + 1]) {
                if (isEven) {
                    low = mid + 2;
                } else {
                    high = mid - 1;
                }
            } else {
                if (isEven) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return nums[low];
    }
}
