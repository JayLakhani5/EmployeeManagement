package EmployeeManagement;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeManage {

    private final List<Employee> employeeList;

    public EmployeeManage() {
        this.employeeList = new ArrayList<>();
    }

    public void newEmployee(String name, String username, String password, int salary, boolean admin) throws ClassNotFoundException, SQLException {
        Employee employee = new Employee(name, username, password, salary, admin);
        employeeList.add(employee);
        System.out.println(STR."id: \{employee.getId()},name : \{employee.getName()}, username : \{employee.getUserName()}, salary : \{employee.getSalary()}, admin : \{employee.isAdmin()}");

//        //connectivity
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "jay");
//        PreparedStatement preparedStatement = connection.prepareStatement("insert into newEmployee values(?,?,?,?,?)");
//        preparedStatement.setInt(1, employee.getId());
//        preparedStatement.setString(2, employee.getName());
//        preparedStatement.setString(3, employee.getUserName());
//        preparedStatement.setString(4, employee.getPassword());
//        preparedStatement.setInt(5, employee.getSalary());
//        int result = preparedStatement.executeUpdate();
//        if (result > 0) {
//            System.out.println("Record inserted");
//        } else {
//            System.out.println("Record not inserted");
//        }
    }

    public Employee loginEmployee(String username, String password) throws ClassNotFoundException, SQLException {
        if (employeeList.isEmpty()) {
            System.out.println("No Employee");
        } else {
            for (Employee employee : employeeList) {
                if (employee.getUserName().equals(username) && password.equals(employee.getPassword())) {
                    System.out.println("login successful ");
                    Date date = new Date();
                    System.out.println(date);
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "jay");
                    PreparedStatement preparedStatement = connection.prepareStatement("insert into loginEmployee values(?,?,'" + date + "')");
                    preparedStatement.setString(1, employee.getUserName());
                    preparedStatement.setString(2, employee.getPassword());

                    int result = preparedStatement.executeUpdate();
                    if (result > 0) {
                        System.out.println("Record inserted");
                    } else {
                        System.out.println("Record not found");
                    }
                    return employee;
                }
            }
        }
        return null;
    }

    public void editEmployee(String username, String password, int percentage) throws ClassNotFoundException, SQLException {
        if (employeeList.isEmpty()) {
            System.out.println("Employee is Empty");
        } else {
            for (Employee employee : employeeList) {
                if (employee.getUserName().equals(username) && employee.getPassword().equals(password)) {
                    int i = employee.getSalary() + (employee.getSalary() * percentage / 100);
                    employee.setSalary(i);
                    System.out.println(STR."user name : \{employee.getName()}, username : \{employee.getUserName()}, salary is : \{employee.getSalary()}");

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "jay");
                    PreparedStatement preparedStatement = connection.prepareStatement("update newEmployee set salary = ? where username = ?");
                    preparedStatement.setInt(1, i);
                    preparedStatement.setString(2, employee.getUserName());
                    int result = preparedStatement.executeUpdate();
                    if (result > 0) {
                        System.out.println(" Record update successful");
                    } else {
                        System.out.println("Record not updated");
                    }
                }
            }
        }
    }

    public void deleteEmployee(String username, String password) throws ClassNotFoundException, SQLException {
        if (employeeList.isEmpty()) {
            System.out.println("Employee is Empty no delete ");
        } else {

            for (Employee employee : employeeList) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "jay");
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE from newEmployee where username = '" + employee.getUserName() + "'");
                int result = preparedStatement.executeUpdate();
                if (result > 0) {
                    System.out.println("record deleted");
                } else {
                    System.out.println("Record not deleted");
                }
                if (employee.getUserName().equals(username) && employee.getPassword().equals(password)) {
                    employeeList.remove(employee);
                    System.out.println("Employee is delete ");

                    //Connectivity

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "jay");
                    PreparedStatement preparedStatement1 = connection1.prepareStatement("DELETE from newEmployee where username = '" + employee.getUserName() + "'");
                    int result1 = preparedStatement1.executeUpdate();
                    if (result1 > 0) {
                        System.out.println("record deleted");
                    } else {
                        System.out.println("Record not deleted");
                    }
                } else {
                    if (!(employee.getUserName().equals(username)) && !employee.getPassword().equals(password)) {
                        System.out.println("Employee is not deleted ");

                    }
                }
            }
        }
    }

    public void allEmployeeList() throws SQLException, ClassNotFoundException {
        if (employeeList.isEmpty()) {
            System.out.println("Employee list is Empty");
        } else {
            for (Employee employee : employeeList) {
                System.out.println(employee);

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/EmployeeManagement", "root", "jay");
                PreparedStatement preparedStatement = connection.prepareStatement("select * from newEmployee");
                ResultSet resultset = preparedStatement.executeQuery();

                while (resultset.next()) {
                    String name = resultset.getString("name");
                    System.out.println("first name : " + name);

                    String username = resultset.getString("username");
                    System.out.println("last name : " + username);

                    int salary = resultset.getInt("salary");
                    System.out.println("last name" + salary);

                    System.out.println("++++++++++++++++++++++++++++++++");

                }
            }
        }
    }

    public boolean checkUsernameExistance(String username) {
        for (Employee employee : employeeList) {
            if (employee.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUsernamePassword(String username, String password) {
        for (Employee employee : employeeList) {
            if (employee.getUserName().equals(username) && employee.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
