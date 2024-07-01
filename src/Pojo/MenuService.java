package Pojo;

import EmployeeManagement.Employee;
import EmployeeManagement.EmployeeManage;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuService {

    public static void MenuStatement(boolean isAdmin) {

        System.out.println("1.new Employee");
        System.out.println("2.Login");
        System.out.println("3.Delete Employee");
        System.out.println("4.All employee");
        if (isAdmin) {
            System.out.println("5.Edit Employee");
            System.out.println("6.Logout");
        }
        System.out.print("Enter your selection : ");
    }

    public static void createEmployee(EmployeeManage employeeManage, boolean admin) {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        System.out.print("Enter your name : ");
        String name = scanner1.nextLine();
        if (!name.matches("[a-zA-Z_]+")) {
            System.out.println("Invalid name");
        } else {
            System.out.print("Enter your user name : ");
            String username = scanner1.nextLine();
            if (!username.matches("[a-zA-Z_]+")) {
                System.out.println("invalid");
            } else {
                if (employeeManage.checkUsernameExistance(username)) {
                    throw new RuntimeException("username already exist");
                }
                System.out.print("Enter password : ");
                String password = scanner1.nextLine();

                System.out.print("Enter salary : ");
                int salary = scanner.nextInt();
                if (salary < 0) {
                    System.out.println("invalid salary");
                } else {
                    try {
                        employeeManage.newEmployee(name, username, password, salary, admin);
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    public static Employee login(EmployeeManage employeeManage) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter your username ");
        String username = scanner1.nextLine();
        System.out.print("Enter password : ");
        String password = scanner.nextLine();
        return employeeManage.loginEmployee(username, password);

    }

    public static int optionMenu() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static void editUser(EmployeeManage employeeManage) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("user name : ");
        String username = scanner1.nextLine();
        System.out.print("Enter your password : ");
        String password = scanner.nextLine();
        if (!employeeManage.checkUsernamePassword(username, password)) {
            throw new RuntimeException("not match user & password");
        }
        System.out.print(" how many percentage increment salary (%) : ");
        int percentage = scanner.nextInt();
        if (percentage < 0) {
            System.out.println("Invalid percentage");
        }
        employeeManage.editEmployee(username, password, percentage);


    }

    public static void deleteUser(EmployeeManage employeeManage) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("Enter username : ");
        String username = scanner.nextLine();
        System.out.print("Enter password : ");
        String password = scanner1.nextLine();
        employeeManage.deleteEmployee(username, password);
    }

}
