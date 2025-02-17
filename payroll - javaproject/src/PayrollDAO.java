import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayrollDAO {
    public void calculateSalary(int emp_id, float deduction){
     // net_salary = salary + allowances(hra + ta + da) - deductions(pf+ins+pt)
        String getSalaryQry = "select salary from employee where id = ?";
     String insertPayrollQry = "insert into payroll(emp_id, base_salary, deductions, net_salary, pay_date)" +
             "values(?, ?, ?, ?, now())";
        try (Connection con= DBConnection.getConnection();
          PreparedStatement getSalaryStmt =con.prepareStatement(getSalaryQry);
          PreparedStatement insertPayrollStmt = con.prepareStatement(insertPayrollQry)
          ){
          getSalaryStmt.setInt(1, emp_id);
            ResultSet rs=getSalaryStmt.executeQuery();
            if(rs.next()){
                float baseSalary = rs.getFloat("salary");
                float netSalary = baseSalary - deduction;
                insertPayrollStmt.setInt(1, emp_id);
                insertPayrollStmt.setFloat(2, baseSalary);
                insertPayrollStmt.setFloat(3, deduction);
                insertPayrollStmt.setFloat(4, netSalary);
                insertPayrollStmt.executeUpdate();
                System.out.println("Salary processed for employee id  "+ emp_id);
            }
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }

    }
}
