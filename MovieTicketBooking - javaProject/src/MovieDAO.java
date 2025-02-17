
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDAO {
    public void viewAvailableTickets(){
        String sql = "SELECT* FROM Ticket";
        try(Connection con = DBConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery()){
            while(rs.next()){
                System.out.println("Ticket ID: "+rs.getInt("ticket_id")+
                " | Movie: " + rs.getString("movie_name") +
                " | Available Tickets: "+ rs.getInt("available_tickets")+
                " | Price: "+ rs.getFloat("Ticprice"));

            }
        }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
    

