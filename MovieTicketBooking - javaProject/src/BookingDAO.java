import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class BookingDAO {
    public void bookTicket(int ticketId,int customerId,int numTickets){
        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);
            String checkTicket = "Select available_tickets,TicPrice from Ticket where ticket_id = ?";
            PreparedStatement checkTicketStmt = con.prepareStatement(checkTicket);
            checkTicketStmt.setInt(1,ticketId);
            ResultSet ticketRs = checkTicketStmt.executeQuery();
        if (!ticketRs.next() || ticketRs.getInt("Available_Tickets") < numTickets) {
                System.out.println("Error: Not enough tickets available.");
                con.rollback();
                return;
            }
            float ticketPrice = ticketRs.getFloat("TicPrice");
            float totalCost = ticketPrice * numTickets;

            // Step 2: Check Customer Balance
            String checkBalance = "SELECT balance FROM Customer WHERE customer_id = ?";
            PreparedStatement checkBalanceStmt = con.prepareStatement(checkBalance);
            checkBalanceStmt.setInt(1, customerId);
            ResultSet balanceRs = checkBalanceStmt.executeQuery();
            
            if (!balanceRs.next() || balanceRs.getFloat("balance") < totalCost) {
                System.out.println("Error: Insufficient balance.");
                con.rollback();
                return;
            }

            // Step 3: Deduct Tickets
            String updateTicket = "UPDATE Ticket SET Available_Tickets = Available_Tickets - ? WHERE ticket_id = ?";
            PreparedStatement updateTicketStmt = con.prepareStatement(updateTicket);
            updateTicketStmt.setInt(1, numTickets);
            updateTicketStmt.setInt(2, ticketId);
            updateTicketStmt.executeUpdate();

            // Step 4: Deduct Amount from Customer
            String updateCustomer = "UPDATE Customer SET balance = balance - ? WHERE customer_id = ?";
            PreparedStatement updateCustomerStmt = con.prepareStatement(updateCustomer);
            updateCustomerStmt.setFloat(1, totalCost);
            updateCustomerStmt.setInt(2, customerId);
            updateCustomerStmt.executeUpdate();

            // Step 5: Insert Booking Record
            String insertBooking = "INSERT INTO Booking (ticket_id, customer_id, no_of_tickets) VALUES (?, ?, ?)";
            PreparedStatement insertBookingStmt = con.prepareStatement(insertBooking);
            insertBookingStmt.setInt(1, ticketId);
            insertBookingStmt.setInt(2, customerId);
            insertBookingStmt.setInt(3, numTickets);
            insertBookingStmt.executeUpdate();

            con.commit(); // Commit Transaction
            System.out.println("Booking Successful!");

        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback(); // Rollback Transaction if any step fails
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (con != null) con.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

