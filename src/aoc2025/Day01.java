package aoc2025;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day01 {

    int pos = 50;
    int res = 0;

    public void run() {
        IO.println("Running Day 01 solution...");

        long startTime = System.nanoTime();

        try {
            Scanner fileScanner = new Scanner(new File("src/resources/day01_input.txt"));
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                boolean left = line.startsWith("L");
                int step = Integer.parseInt(line.substring(1));
                subtask2(left, step);

            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            IO.println("Execution time: " + duration / 1_000_000 + " ms");

            IO.println("Answer: " + this.res);

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void subtask1(boolean direction, int step) {
        if (direction) this.pos = (this.pos - step + 100) % 100;
        else this.pos = (this.pos + step + 100) % 100;
        if (this.pos == 0) this.res++;
    }

    private void subtask2(boolean left, int step) {
        int target;
        int loops;
        if (left) {
            target = this.pos - step;
            loops = Math.abs(target / 100);
            loops += (target <= 0 && this.pos != 0) ? 1 : 0;

            this.res += loops;
            this.pos = (target + (loops + 1) * 100) % 100;
        }
        else {
            target = this.pos + step;
            loops = target / 100;

            this.res += loops;
            this.pos = target % 100;
        }
    }
}
