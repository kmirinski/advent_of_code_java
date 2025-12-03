package aoc2025;

import java.io.File;
import java.util.Scanner;

public class Day02 {

    long res = 0L;

    public void run() {
        IO.println("Running Day 02 solution...");

        long startTime = System.nanoTime();

        try {
            Scanner scanner = new Scanner(new File("src/resources/day02_input.txt"));;
            String line = scanner.nextLine();
            String[] ranges = line.split(",");
            for (String r : ranges) {
                String[] bounds =  r.split("-");
                long low = Long.parseLong(bounds[0]);
                long high = Long.parseLong(bounds[1]);
                subtask2(low, high);
            }
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            IO.println("Execution time: " + duration / 1_000_000 + " ms");

            IO.println("Answer: " + this.res);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void subtask1(long low, long high) {
        for (long i = low ; i <= high; i++) {
            String numAsString = String.valueOf(i);
            int n = numAsString.length();
            if (n % 2 == 0) {
                boolean invalid = true;
                for (int j = 0; j < n / 2; j++) {
                    if (numAsString.charAt(j) != numAsString.charAt(n / 2 + j)) {
                        invalid = false;
                        break;
                    }
                }
                res += invalid ? i : 0;
            }
        }
    }

    private void subtask2(long low, long high) {
        for (long i = low ; i <= high; i++) { // tracks numbers within range
            String numAsString = String.valueOf(i);
            int n = numAsString.length();
            boolean detected = false;
            for (int j = 1; j <= n / 2 && !detected; j++) { // tracks sequence length
                if (n % j == 0) {
                    boolean invalid = isInvalid(n, j, numAsString);
                    if (invalid) {
                        res += i;
                        detected = true;
                    }
                }
            }
        }
    }

    private static boolean isInvalid(int n, int j, String numAsString) {
        boolean invalid = true;
        int sequences = n / j;
        // Compares corresponding indices of each subsequence
        for (int k = 0; k < j; k++) { // tracks index within a sequence
            int target = numAsString.charAt(k);
            for (int l = 1; l < sequences; l++) { // checks each index
                if (target != numAsString.charAt(k + l * j)) {
                    invalid = false;
                    break;
                }
            }
        }
        return invalid;
    }
}
