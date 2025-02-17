

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MovieDAO movieDAO = new MovieDAO();
        BookingDAO bookingDAO = new BookingDAO();

        while (true) {
            System.out.println("\n=== Movie Ticket Booking System ===");
            System.out.println("1. View Available Tickets");
            System.out.println("2. Book a Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    movieDAO.viewAvailableTickets();
                    break;
                case 2:
                    System.out.print("Enter Ticket ID: ");
                    int ticketId = sc.nextInt();
                    System.out.print("Enter Customer ID: ");
                    int customerId = sc.nextInt();
                    System.out.print("Enter Number of Tickets: ");
                    int numTickets = sc.nextInt();
                    bookingDAO.bookTicket(ticketId, customerId, numTickets);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

