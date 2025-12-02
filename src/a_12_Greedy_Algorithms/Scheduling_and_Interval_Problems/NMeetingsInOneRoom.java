package a_12_Greedy_Algorithms.Scheduling_and_Interval_Problems;

import java.util.Arrays;

class Data {
    int start;
    int end;
    int pos;

    @Override
    public String toString() {
        return "Data{" +
                "start=" + start +
                ", end=" + end +
                ", pos=" + pos +
                '}';
    }
}

public class NMeetingsInOneRoom {
    public static void main(String[] args) {
        System.out.println(maxMeetings(new int[]{1, 3, 0, 5, 8, 5}, new int[]{2, 4, 6, 7, 9, 9}));
        System.out.println(maxMeetings(new int[]{10, 12, 20}, new int[]{20, 25, 30}));
        System.out.println(maxMeetings(new int[]{1, 4, 6, 9}, new int[]{2, 5, 7, 12}));
    }

    public static int maxMeetings(int[] start, int[] end) {
        Data[] data = new Data[start.length];

        for (int i = 0; i < start.length; i++) {
            data[i] = new Data();
            data[i].start = start[i];
            data[i].end = end[i];
            data[i].pos = i;
        }

        Arrays.sort(data, (data1, data2) -> {
            if (data1.end != data2.end) {
                return data1.end - data2.end; // Sort by end time ASC
            } else {
                return data1.pos - data2.pos; // If end times are equal, sort by position ASC
            }
        });

        System.out.println(Arrays.toString(data));

        int count = 1, freeTime = data[0].end;
        for (int i = 1; i < data.length; i++) {
            if (data[i].start > freeTime) {
                count++;
                freeTime = data[i].end;
            }
        }
        return count;
    }
}
