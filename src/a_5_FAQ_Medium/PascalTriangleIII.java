package a_5_FAQ_Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PascalTriangleIII {
    public static void main(String[] args) {
        PascalTriangleIII obj = new PascalTriangleIII();
        List<List<Integer>> ans = obj.pascalTriangleIII(6);
        System.out.println("Output 1 = " + ans);
    }

    public List<List<Integer>> pascalTriangleIII(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            ans.add(pascalTriangleRow(i));
        }
        return ans;
    }

    public List<Integer> pascalTriangleRow(int r) {
        List<Integer> row = new ArrayList<>();
        row.add(1);
        for (int i = 1; i < r; i++) {
            row.add((int) ((long) row.get(i - 1) * (r - i) / i));
        }
        return row;
    }
}
