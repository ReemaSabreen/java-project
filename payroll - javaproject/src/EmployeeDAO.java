import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

public void addEmployee(String name, String dept, String position, float salary){
    String sql="insert into employee(name, department, position, salary) values (?, ?, ?, ?)";
    try(Connection con= DBConnection.getConnection();
            PreparedStatement pst=con.prepareStatement(sql)){
pst.setString(1, name);
pst.setString(2, dept);
pst.setString(3, position);
pst.setFloat(4, salary);
pst.executeUpdate();
        System.out.println("Data inserted successfully.");
    }
    catch(SQLException ss){
        ss.printStackTrace();
    }
}
public void viewEmployee(){
    String sql="select * from employee";
    try( Connection con=DBConnection.getConnection();
    PreparedStatement pst=con.prepareStatement(sql);
         ResultSet rs=pst.executeQuery()){
     while (rs.next()){
         System.out.println("ID :"+ rs.getInt("id")+
                 "NAME :"+ rs.getString("name")+
                 "Department :"+ rs.getString("department")+
                 "Position :"+ rs.getString("position")+
                 "Salary :"+ rs.getFloat("salary"));
     }
    }
    catch (SQLException ss){
        ss.printStackTrace();
    }
}
}
