package aoc2025;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day03 {

    long res = 0;

    public void run() {
        IO.println("Running Day 03 solution...");

        long startTime = System.nanoTime();

        try {
            Scanner fileScanner = new Scanner(new File("src/resources/day03_input.txt"));
            while (fileScanner.hasNextLine()) {
                char[] joltages = fileScanner.nextLine().toCharArray();
                res +=  subtask2(joltages);
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

    private long subtask1(char[] joltages) {
        char[] number = {'0', '0'};
        int n = joltages.length;
        for (int i = 0; i < n - 1; i++) {
            char pointerTens = joltages[i];
            if (pointerTens > number[0]) number[0] = pointerTens;
            for (int j = i + 1; j < n; j++) {
                char pointerOnes = joltages[j];
                if (pointerOnes > number[1]) number[1] = pointerOnes;
                if (j != n - 1 && number[1] > number[0])  {
                    number[1] = '0';
                    i = j - 1;
                    break;
                }
                i = j - 1;
            }
        }
        return (number[0] - '0') * 10 + (number[1] - '0');
    }

    private long subtask2(char[] joltages) {
        char[] number = new char[12];
        Arrays.fill(number, '0');
        int n = joltages.length;
        for (int i = 0; i < n; i++) {
            char joltage = joltages[i];
            for (int j = 0; j < 12; j++) {
                char digit = number[j];
                if (joltage > digit && i < n - 11 + j) {
                    number[j] = joltage;
                    for (int k = j + 1; k < 12; k++) {
                        number[k] = '0';
                    }
                    break;
                }
            }
        }
        long result = 0;
        for (int i = 0; i < 12; i++) {
            result += (long) ((number[i] - '0') * Math.pow(10, 11 - i));
        }

        return result;
    }
}
