package aoc2025;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day05 {

    long res = 0;

    public void run() {
        IO.println("Running Day 05 solution...");

        long startTime = System.nanoTime();

        try {
            Scanner fileScanner = new Scanner(new File("src/resources/day05_input.txt"));
            List<Interval> intervals = new ArrayList<>();
            List<Long> ids = new ArrayList<>();

            while (true) {
                String line = fileScanner.nextLine();
                if (line.isEmpty()) break;
                String[] bounds = line.split("-");
                intervals.add(new Interval(Long.parseLong(bounds[0]), Long.parseLong(bounds[1])));
            }

            Collections.sort(intervals);
            optimizeOverlapping(intervals);

            while (fileScanner.hasNextLine()) {
                ids.add(Long.parseLong(fileScanner.nextLine()));
            }

//            subtask1(intervals, ids);
            subtask2(intervals);


            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            IO.println("Execution time: " + duration / 1_000_000 + " ms");

            IO.println("Answer: " + this.res);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void subtask1(List<Interval> intervals, List<Long> ids) {
        for (long id : ids) {
            int l = 0;
            int r = intervals.size() - 1;
            while(l <= r) {
                int mid = (l + r) / 2;
                Interval midInterval = intervals.get(mid);
                if (id < midInterval.low) r = mid - 1;
                else {
                    if (id <= midInterval.high) {
                        res ++;
                        break;
                    }
                    l = mid + 1;
                }
            }
        }
    }

    private void subtask2(List<Interval> intervals) {
        for (Interval i : intervals) res += i.high - (i.low - 1);
    }

    private void optimizeOverlapping(List<Interval> intervals) {
        for (int i = 0; i < intervals.size() - 1; i++) {
            Interval cur = intervals.get(i);
            Interval next = intervals.get(i + 1);

            if (cur.high >= next.low) {
                if (next.high > cur.high) cur.high = next.high;
                intervals.remove(i + 1);
                i--;
            }
        }
    }

    static class Interval implements Comparable<Interval> {
        long low;
        long high;

        Interval(long low, long high) {
            this.low = low;
            this.high = high;
        }


        @Override
        public int compareTo(Interval o) {
            return Long.compare(this.low, o.low);
        }
    }
}

