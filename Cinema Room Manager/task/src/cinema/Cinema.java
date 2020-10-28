package cinema;

import java.util.Scanner;

public class Cinema {

    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        int occupiedRow = 0;
        int occupiedColumn = 0;
        boolean isExit = false;

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int columns = scanner.nextInt();

        boolean[][] boughTickets = new boolean[rows][columns];



        do {
            System.out.println();

            printMenu();
            var chose = scanner.nextInt();

            System.out.println();

            switch (chose) {
                case 0:
                    isExit = true;
                    break;
                case 1:
                    printSeats(rows,columns, boughTickets);
                    break;
                case 2:
                    buyTicket(rows, columns, boughTickets);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value");
            }
        } while (!isExit);

    }

    private static void buyTicket(int rows, int columns, boolean[][] boughTickets)
    {
        System.out.println("Enter a row number:");
        int occupiedRow = scanner.nextInt();

        System.out.println("Enter a seat number in that row:");
        int occupiedColumn = scanner.nextInt();

        System.out.println("Ticket price: $" + calculateSeatPrice(rows,columns, occupiedRow, occupiedColumn));

        boughTickets[occupiedRow-1][occupiedColumn-1] = true;
    }

    private static void printMenu()
    {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("0. Exit");
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

    private static void printSeats(int rows, int columns, boolean[][] boughTickets) {
        System.out.println("cinema.Cinema:");

        System.out.print(" ");
        for (int i = 1; i <= columns; i++) {
            System.out.print(" " + i);
        }

        System.out.println();

        for (int i = 1; i <= rows; i++) {
            System.out.print(i);
            for (int j = 1; j <= columns; j++) {
                System.out.print((boughTickets[i-1][j-1]) ? " B" : " S");
            }
            System.out.println();
        }
    }
}