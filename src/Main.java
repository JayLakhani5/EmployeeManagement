import EmployeeManagement.Employee;
import EmployeeManagement.EmployeeManage;
import Pojo.MenuService;

public class Main {
    static EmployeeManage employeeManage;

    static {
        employeeManage = new EmployeeManage();
        System.out.println("+++++++ ADMIN USER+++++++");
        try {
            MenuService.createEmployee(employeeManage, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("+++++++ ADMIN USER CREATED+++++++");
    }

    public static void main(String[] args) {
        startMenu();
    }

    public static void startMenu() {
        try {
            MenuService.MenuStatement(false);
            int option;
            Employee employee = null;
            do {
                option = MenuService.optionMenu();
                switch (option) {
                    case 1:
                        MenuService.createEmployee(employeeManage, false);
                        startMenu();
                        break;
                    case 2:
                        employee = MenuService.login(employeeManage);
                        if (employee == null) {
                            System.out.println("Invalid username & password");
                            startMenu();
                        } else {
                            if (employee.isAdmin()) {
                                System.out.println("you are admin login");
                            }
                            MenuService.MenuStatement(employee.isAdmin());
                        }
                        break;
                    case 3:
                        MenuService.deleteUser(employeeManage);
                        startMenu();
                        break;
                    case 5:
                        assert employee != null;
                        if (employee.isAdmin()) {
                            MenuService.editUser(employeeManage);
                            MenuService.MenuStatement(employee.isAdmin());
                            break;
                        }
                    case 4:
                        employeeManage.allEmployeeList();
                        MenuService.MenuStatement(employee.isAdmin());
                        continue;
                    case 6:
                        startMenu();
                        break;
                    default:
                        System.out.println("Invalid number ");
                        startMenu();
                        break;

                }

            } while (option != 6);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            startMenu();
        }
    }
}