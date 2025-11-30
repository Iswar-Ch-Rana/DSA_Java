package a_12_Greedy_Algorithms.Easy;

import java.util.Arrays;

public class AssignCookies {
    public static void main(String[] args) {
        System.out.println(findMaximumCookieStudents(new int[]{1, 2, 3}, new int[]{1, 1}));
        System.out.println(findMaximumCookieStudents(new int[]{1, 2}, new int[]{1, 2, 3}));
        System.out.println(findMaximumCookieStudents(new int[]{4, 5, 1}, new int[]{6, 4, 2}));
    }

    public static int findMaximumCookieStudents(int[] Student, int[] Cookie) {
        //your code goes here
        int studentStart = 0;
        int cookieStrat = 0;
        Arrays.sort(Student);
        Arrays.sort(Cookie);

        while (studentStart < Student.length && cookieStrat < Cookie.length) {
            if (Cookie[cookieStrat] >= Student[studentStart]) {
                studentStart++;
            }
            cookieStrat++;
        }
        return studentStart;
    }
}
