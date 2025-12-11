package aoc2025;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class Day04 {

    int res = 0;

    public void run() {
        IO.println("Running Day 04 solution...");

        long startTime = System.nanoTime();

        try {
            List<String> lines = Files.readAllLines(Path.of("src/resources/day04_input.txt"));

            int rows = lines.size();
            int cols = lines.getFirst().length();

            char[][] grid = new char[rows][cols];

            for (int i = 0; i < rows; i++) {
                grid[i] = lines.get(i).toCharArray();
            }

            subtask2(grid);

            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            IO.println("Execution time: " + duration / 1_000_000 + " ms");

            IO.println("Answer: " + this.res);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Point> subtask1(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        List<Point> removed = new ArrayList<>();
        List<Point> directions = List.of(
                new Point(-1, -1),
                new Point(-1, 0),
                new Point(-1, 1),
                new Point(0, -1),
                new Point(0, 1),
                new Point(1, -1),
                new Point(1, 0),
                new Point(1, 1)
        );
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != '@') continue;
                int neighbours = 0;
                for (Point dir : directions) {
                    int dx = i + dir.x;
                    int dy = j + dir.y;
                    if (dx >= 0 && dx < rows && dy >= 0 && dy < cols && grid[i + dir.x][j + dir.y] == '@') neighbours++;
                }
                if (neighbours < 4) removed.add(new Point(i, j));
            }
        }

        return removed;
    }

    private void subtask2(char[][] grid) {
        int removed = -1;
        while (removed != 0) {
            List<Point> removedList = subtask1(grid);
            removed = removedList.size();
            for (Point p : removedList) {
                grid[p.x][p.y] = '.';
            }
            this.res += removed;
        }
    }
}

class Point {
    int x;
    int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
