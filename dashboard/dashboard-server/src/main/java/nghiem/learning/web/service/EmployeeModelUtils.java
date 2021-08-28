package nghiem.learning.web.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import nghiem.learning.web.entity.EmployeeEntity;
import nghiem.learning.web.model.Employee;

public class EmployeeModelUtils {

    public static List<Employee> fromEntities(List<EmployeeEntity> entities) {
        if(hasEntry(entities)) {
            return entities.parallelStream().map((entity) -> fromEntity(entity)).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public static EmployeeEntity toEntity(Employee e) {
        EmployeeEntity entity = new EmployeeEntity();
        if(e == null) {
            return null;
        }
        
        if(e.getName() != null && !e.getName().isEmpty()) {
            entity.setName(e.getName());
        }
        if(e.getAge() >0 ) {
            entity.setAge(e.getAge());
        }
        if(e.getOffice() != null && !e.getOffice().isEmpty()) {
            entity.setOffice(e.getOffice());
        }
        if(e.getPosition() != null && !e.getPosition().isEmpty()) {
            entity.setPosition(e.getPosition());
        }
        
        if(e.getSalary() > 0) {
            entity.setSalary(e.getSalary());
        }
        
        if(e.getStartDate() != null) {
            entity.setStartDate(e.getStartDate());
        }
        
        return entity;
    }

    public static Employee fromEntity(EmployeeEntity entity) {
        if(entity == null) {
            return null;
        } else {
            Employee emp = new Employee();
            emp.setId(entity.getId());
            emp.setName(entity.getName());
            emp.setOffice(entity.getOffice());
            emp.setPosition(entity.getPosition());
            emp.setSalary(entity.getSalary());
            emp.setAge(entity.getAge());
            emp.setStartDate(entity.getStartDate());
            
            return emp;
        }
    }
    
    @SuppressWarnings("rawtypes")
    public static boolean hasEntry(Collection collection) {
        return collection != null && !collection.isEmpty();
    }
}
