package nghiem.learning.web.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import nghiem.learning.web.model.Employee;
import nghiem.learning.web.service.EmployeeBasicCrudService;

@RestController("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeBasicCrudService m_basicCrudService;
    
    @GetMapping
    public List<Employee> getAllEmployee() {
        m_basicCrudService.getAllEmployee();
        return Collections.emptyList();
    }
    
    @GetMapping("/employee/count")
    public int getEmployeeCount() {
        return m_basicCrudService.getAllEmployee().size();
    }
    
    @GetMapping("/employee/page")
    public List<Employee> getEmployeeByPage(@RequestParam(name = "pageNumber") int pageNumber, 
            @RequestParam(name = "pageSize") int pageSize) {
        return m_basicCrudService.findEmployeeByPage(pageNumber, pageSize);
    }
    
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@RequestParam int id) {
        return m_basicCrudService.findEmployeeById(id);
    }
    
    @PutMapping("/employee/add")
    public void addEmployee(@RequestBody Employee e) {
        m_basicCrudService.saveEmployee(e);
    }
    
    @PostMapping("/employee/save")
    public Employee saveEmployee(@RequestBody Employee e) {
        return m_basicCrudService.saveEmployee(e);
    }
    
    @DeleteMapping("/employee/delete/{id}")
    public void deleteEmployee(@RequestParam(name = "id") long id) {
        m_basicCrudService.deleteEmployeeById(id);
    }
}
