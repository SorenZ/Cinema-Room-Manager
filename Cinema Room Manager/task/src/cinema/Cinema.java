package cinema;

import javax.lang.model.util.ElementScanner6;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int columns = scanner.nextInt();

        System.out.println("Total income:");
        System.out.println("$" + calculateIncome(rows, columns));

    }

    private static int calculateIncome(int rows, int columns) {
        var totalSeats = rows * columns;

        if(totalSeats <= 60)
            return totalSeats * 10;

        int firstHalf = rows / 2;

        int secondHalf = firstHalf + (((rows % 2) == 0) ? 0 : 1);

        return firstHalf * columns * 10 + secondHalf * columns * 8;
    }

    private static void printSeats() {
        System.out.println("cinema.Cinema:");
        System.out.println("  1 2 3 4 5 6 7 8");
        for (int i = 1; i < 8; i++) {
            System.out.println(String.format("%d S S S S S S S S",i));
        }
    }
}