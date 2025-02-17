import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO ed = new EmployeeDAO();
        PayrollDAO pd = new PayrollDAO();

        Scanner sc=new Scanner(System.in);
        while (true){
            System.out.println("1. Add Employee");
            System.out.println("2. View Employee");
            System.out.println("3. Process Payroll");
            System.out.println("4. Exit");
            System.out.println("Enter your Choice");
        int choice = sc.nextInt();
        sc.nextLine();

        switch (choice){
            case 1:
                System.out.println("Enter name");
                String name = sc.nextLine();
                System.out.println("Enter Department");
                String dept=sc.nextLine();
                System.out.println("Enter Position");
                String position = sc.nextLine();
                System.out.println("Enter Salary");
                float salary=sc.nextFloat();
                ed.addEmployee(name, dept, position, salary);
                break;
            case 2:
                ed.viewEmployee();
                break;
            case 3:
                System.out.println("Enter employee id ");
                int emp_id = sc.nextInt();
                System.out.println("Enter deduction");
                float deduction = sc.nextFloat();
                pd.calculateSalary(emp_id, deduction);
                break;
            case 4:
                System.out.println("Exiting....");
                sc.close();;
                System.exit(0);
            default:
                System.out.println("Invalid choice, Try Again!!!");

        }
        }
    }
}