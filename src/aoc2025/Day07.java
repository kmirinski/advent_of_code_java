package aoc2025;

import java.io.*;
import java.util.*;

public class Day07 {

    long res = 0;

    public void run() {
        IO.println("Running Day 07 solution...");

        long startTime = System.nanoTime();

        try {
//            Scanner fileScanner = new Scanner(new File("src/resources/day07_input.txt"));
            BufferedReader br = new BufferedReader(new FileReader("src/resources/day07_input.txt"));

            List<char[]> rows = new ArrayList<>();

            String line;
            while((line = br.readLine()) != null) {
                rows.add(line.toCharArray());
            }
            br.close();

            char[][] grid = rows.toArray(new char[0][]);

//            subtask1(grid);
            Coordinate start = findStart(grid[0]);
            this.res = subtask2(grid, start);

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            IO.println("Execution time: " + duration / 1_000_000 + " ms");

            IO.println("Answer: " + this.res);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Coordinate findStart(char[] firstRow) {
        for (int j = 0; j < firstRow.length; j++) {
            if (firstRow[j] == 'S') {
                return new Coordinate(0, j);
            }
        }
        return new Coordinate();
    }

    private void subtask1(char[][] grid) {
        Deque<Coordinate> dq = new LinkedList<>();
        Set<Coordinate> seen = new HashSet<>();

        int n = grid.length;

        Coordinate start = findStart(grid[0]);
        dq.add(start);

        while (!dq.isEmpty()) {
            Coordinate current = dq.poll();
            if (seen.contains(current)) continue;
            seen.add(current);
            int newX = current.x + 1;
            int y = current.y;
            if (newX == n) continue;
            if (grid[newX][y] == '^') {
                dq.add(new Coordinate(newX, current.y - 1));
                dq.add(new Coordinate(newX, current.y + 1));
                this.res++;
            }
            else {
                dq.add(new Coordinate(newX, y));
            }
        }
    }

    private long subtask2(char[][] grid, Coordinate start) {
        int rows = grid.length;
        int cols = grid[0].length;

        long[][] dp = new long[rows][cols];

        for (int j = 0; j < cols; j++) {
            dp[rows - 1][j] = 1;
        }

        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j < cols; j++) {
                if (grid[i + 1][j] == '^') {
                    if (j > 0) dp[i][j] += dp[i + 1][j - 1];
                    if (j < cols - 1) dp[i][j] += dp[i + 1][j + 1];
                }
                else dp[i][j] = dp[i + 1][j];
            }
        }

        return dp[start.x][start.y];

    }

    static class Coordinate {
        int x;
        int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Coordinate() {
            this.x = 0;
            this.y = 0;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}
