import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String url="jdbc:mysql://localhost:3306/payrolldb";
    private static String uname="root";
    private static String password="Reem@2004#my$ql";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection(url, uname, password);
    }
}