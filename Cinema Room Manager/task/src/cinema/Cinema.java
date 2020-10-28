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

        printSeats(rows, columns, 0, 0);

        System.out.println("Enter a row number:");
        int occupiedRow = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int occupiedColumn = scanner.nextInt();

        System.out.println("Ticket price: $" + calculateSeatPrice(rows,columns, occupiedRow, occupiedColumn));
        System.out.println();
        printSeats(rows, columns, occupiedRow, occupiedColumn);

//        System.out.println("Total income:");
//        System.out.println("$" + calculateIncome(rows, columns));

    }

    private static int calculateIncome(int rows, int columns) {
        var totalSeats = rows * columns;

        if(totalSeats <= 60)
            return totalSeats * 10;

        int firstHalf = rows / 2;

        int secondHalf = firstHalf + (((rows % 2) == 0) ? 0 : 1);

        return firstHalf * columns * 10 + secondHalf * columns * 8;
    }

    private static int calculateSeatPrice(int rows, int columns, int occupiedRow, int occupiedColumn) {
        var totalSeats = rows * columns;

        if(totalSeats <= 60)
            return 10;

        int firstHalf = rows / 2;

        int secondHalf = firstHalf + (((rows % 2) == 0) ? 0 : 1);

        return (occupiedRow <= firstHalf)
                ? 10
                : 8;
    }

    private static void printSeats(int rows, int columns, int occupiedRow, int occupiedColumn) {
        System.out.println("cinema.Cinema:");

        System.out.print(" ");
        for (int i = 1; i <= columns; i++) {
            System.out.print(" " + i);
        }

        System.out.println();

        for (int i = 1; i <= rows; i++) {
            System.out.print(i);
            for (int j = 1; j <= columns; j++) {
                System.out.print((occupiedRow == i && occupiedColumn == j) ? " B" : " S");
            }
            System.out.println();
        }
    }
}