package aoc2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day06 {

    long res = 0;

    public void run() {
        IO.println("Running Day 06 solution...");

        long startTime = System.nanoTime();

        try {
            List<String> lines = Files.readAllLines(Path.of("src/resources/day06_input.txt"));

//            long[][] numMatrix = extractNumsLong(lines);
            char[][] numMatrix = extractNumsChar(lines);
            String[] ops = lines.getLast().split("\\s+");

//            subtask1(numMatrix, ops);
            subtask2(numMatrix, ops);

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            IO.println("Execution time: " + duration / 1_000_000 + " ms");

            IO.println("Answer: " + this.res);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private long[][] extractNumsLong(List<String> lines) {
        int numberRows = lines.size() - 1;

        long[][] numMatrix = new long[lines.size() - 1][lines.getFirst().trim().split("\\s+").length];

        for (int i = 0; i < numberRows; i++) {
            String line = lines.get(i);
            long[] numbers = Arrays.stream(line.trim().split("\\s+")).mapToLong(Long::parseLong).toArray();
            numMatrix[i] = numbers;
        }

        return numMatrix;
    }

    private char[][] extractNumsChar(List<String> lines) {
        int numberRows = lines.size() - 1;

        char[][] numMatrix = new char[lines.size() - 1][lines.getFirst().length()];

        for (int i = 0; i < numberRows; i++) {
            char[] characters = lines.get(i).toCharArray();
            numMatrix[i] = characters;
        }

        return numMatrix;
    }

    private void subtask1(long[][] numMatrix, String[] ops) {
        int n = numMatrix.length;
        int m = ops.length;
        long[] intermediate_results = new long[m];

        for (int j = 0; j < m; j++) {
            if (ops[j].equals("*")) intermediate_results[j] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (ops[j].equals("+")) intermediate_results[j] += numMatrix[i][j];
                else intermediate_results[j] *= numMatrix[i][j];
            }
        }

        this.res = Arrays.stream(intermediate_results).sum();
    }

    private void subtask2(char[][] numMatrix, String[] ops) {
        int n = numMatrix.length;
        int m = numMatrix[0].length;
        int opsIdx = 0;
        List<Long> temporary = new ArrayList<>();

        for (int j = 0; j < m; j++) {
            int power = 0;
            long currentNumber = 0;
            for (int i = n - 1; i >= 0; i--) {
                char digit = numMatrix[i][j];
                if (digit != ' ') {
                    currentNumber += (long) Math.pow(10, power) * (digit - '0');
                    power++;
                }
            }
            if (currentNumber != 0) temporary.add(currentNumber);
            else {
                accumulateToResult(ops, opsIdx, temporary);
                temporary.clear();
                opsIdx++;
            }
        }
        accumulateToResult(ops, opsIdx, temporary);
    }

    private void accumulateToResult(String[] ops, int opsIdx, List<Long> temporary) {
        boolean additionFlag = ops[opsIdx].equals("+");
        long tempRes = additionFlag ? 0 : 1;
        for (Long l : temporary) {
            tempRes = additionFlag ? tempRes + l : tempRes * l;
        }
        this.res += tempRes;
    }
}


