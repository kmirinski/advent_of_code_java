package aoc2025;

import java.util.Scanner;

public class Main {

    static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        IO.print("Enter day: ");
        int day = sc.nextInt();

        switch (day) {
            case 1 -> new Day01().run();
            case 2 -> new Day02().run();
            case 3 -> new Day03().run();
            case 4 -> new Day04().run();
            case 5 -> new Day05().run();
            case 6 -> new Day06().run();
            case 7 -> new Day07().run();
            default -> IO.println("Day not implemented!");
        }
    }
}

