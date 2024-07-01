package EmployeeManagement;

import java.security.SecureRandom;

public class Employee {

    private int id;
    private final String name;


    private String userName;
    private final String password;
    private int salary;
    private final boolean admin;

    public Employee(String name, String userName, String password, int salary, boolean admin) {
        this.id = new SecureRandom().nextInt(1, 99);
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.salary = salary;
        this.admin = admin;
    }

    public String getName() {
        return name;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }


    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isAdmin() {
        return admin;
    }


    @Override
    public String toString() {
        return STR." name : \{name}, userName : \{userName}, salary : \{salary}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
