package Services;

import Entities.Employee;

import static Helper.CommonHelper.compareHash;

public class Authorization {
    public Employee employee;

    public Authorization() {
        employee = new Employee();
    }

    public Boolean login(String username, String password) {
        boolean isLoginValid = false;
        var emp = employee.getEmployeeByUsername(username);
        if (emp != null) {
            isLoginValid = compareHash(password, emp.passwd);
        }
        return isLoginValid;
    }
}
