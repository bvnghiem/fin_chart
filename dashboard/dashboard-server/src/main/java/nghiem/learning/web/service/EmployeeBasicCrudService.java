package nghiem.learning.web.service;

import java.util.List;

import nghiem.learning.web.model.Employee;

public interface EmployeeBasicCrudService {

    List<Employee> getAllEmployee();

    List<Employee> findEmployeeByPage(int pageNumber, int pageSize);

    Employee findEmployeeById(long id);

    void addEmployee(Employee e);

    Employee saveEmployee(Employee e);

    void deleteEmployeeById(long id);
}
