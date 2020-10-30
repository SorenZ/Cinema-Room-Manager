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
                case 3:
                    printStatistic(rows, columns, boughTickets);
                    break;

                default:
                    throw new IllegalStateException("Unexpected value");
            }
        } while (!isExit);

    }

    private static void printStatistic(int rows, int columns,boolean[][] boughTickets)
    {
        int tickets = numberOfPurchasedTicket(rows,columns,boughTickets);
        float percent = (tickets * 100f ) / (float)(rows * columns);
        int currentIncome = calculateCurrentIncome(rows,columns, boughTickets);
        int totalIncome = calculateTotalIncome(rows, columns);


        System.out.printf("Number of purchased tickets: %d\n", tickets);
        System.out.printf("Percentage: %.2f%%\n", percent);
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
    }

    private static int calculateCurrentIncome(int rows, int columns, boolean[][] boughTickets)
    {
        int currentIncome = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if(boughTickets[i-1][j-1])
                    currentIncome+= calculateSeatPrice(rows, columns, i, j);
            }
        }

        return currentIncome;
    }

    private static int numberOfPurchasedTicket(int rows, int columns, boolean[][] boughTickets)
    {
        int tickets = 0;

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                if(boughTickets[i-1][j-1])
                    tickets++;
            }
        }

        return tickets;
    }

    private static int calculateTotalIncome(int rows, int columns) {
        var totalSeats = rows * columns;

        if(totalSeats <= 60)
            return totalSeats * 10;

        int firstHalf = rows / 2;

        int secondHalf = firstHalf + (((rows % 2) == 0) ? 0 : 1);

        return firstHalf * columns * 10 + secondHalf * columns * 8;
    }

    private static void buyTicket(int rows, int columns, boolean[][] boughTickets)
    {
        int occupiedRow = 0;
        int occupiedColumn = 0;

        while(true)
        {
            System.out.println("Enter a row number:");
            occupiedRow = scanner.nextInt();

            System.out.println("Enter a seat number in that row:");
            occupiedColumn = scanner.nextInt();

            if(occupiedRow > rows || occupiedColumn > columns) {
                System.out.println("Wrong input!");
                continue;
            }

            if(! boughTickets[occupiedRow-1][occupiedColumn-1]) {
                break;
            }

            System.out.println("That ticket has already been purchased!");
        }

        System.out.println("Ticket price: $" + calculateSeatPrice(rows,columns, occupiedRow, occupiedColumn));

        boughTickets[occupiedRow-1][occupiedColumn-1] = true;
    }

    private static void printMenu()
    {
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("2. Statistics");
        System.out.println("0. Exit");
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