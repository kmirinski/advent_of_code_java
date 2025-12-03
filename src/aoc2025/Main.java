package aoc2025;

import java.util.Scanner;

public class Main {

    static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        IO.print("Enter day: ");
        int day = sc.nextInt();

        switch (day) {
            case 1 -> new Day01().run();
            default -> IO.println("Day not implemented!");
        }
    }
}

