package nghiem.learning.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import nghiem.learning.web.model.Employee;
import nghiem.learning.web.service.EmployeeBasicCrudService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeBasicCrudService m_basicCrudService;
    
    @GetMapping
    public @ResponseBody List<Employee> getAllEmployee() {
        return m_basicCrudService.getAllEmployee();
    }
    
    @GetMapping("/count")
    public int getEmployeeCount() {
        return m_basicCrudService.getAllEmployee().size();
    }
    
    @GetMapping("/page")
    public @ResponseBody List<Employee> getEmployeeByPage(@RequestParam(name = "pageNumber") int pageNumber, 
            @RequestParam(name = "pageSize") int pageSize) {
        return m_basicCrudService.findEmployeeByPage(pageNumber, pageSize);
    }
    
    @GetMapping("/{id}")
    public @ResponseBody Employee getEmployee(@PathVariable(name = "id") int id) {
        return m_basicCrudService.findEmployeeById(id);
    }
    
    @PostMapping("/add")
    public void addEmployee(@RequestBody Employee e) {
        m_basicCrudService.addEmployee(e);
    }
    
    @PostMapping("/save")
    public @ResponseBody Employee saveEmployee(@RequestBody Employee e) {
        return m_basicCrudService.saveEmployee(e);
    }
    
    @PostMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable(name = "id") long id) {
        m_basicCrudService.deleteEmployeeById(id);
    }
}
