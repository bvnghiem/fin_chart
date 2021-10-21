package nghiem.learning.web.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import nghiem.learning.web.entity.EmployeeEntity;
import nghiem.learning.web.model.Employee;
import nghiem.learning.web.persistence.EmployeeRepository;

@SpringBootTest
public class EmployeeBasicCrudServiceTest {

    @Mock
    private EmployeeRepository m_repository;
    
    @InjectMocks
    private EmployeeBasicCrudServiceImpl m_serviceImpl;

    private EmployeeEntity m_entity2;

    private EmployeeEntity m_entity1;
    
    @BeforeEach
    public void setup() {
        m_entity1 = new EmployeeEntity();
        m_entity1.setId(1);
        m_entity2 = new EmployeeEntity();
        m_entity2.setId(2);
    }
    
    @Test
    public void testGetAllEmployee() {
        assertTrue(m_serviceImpl.getAllEmployee().isEmpty());
        
        when(m_repository.findAll()).thenReturn(Arrays.asList(m_entity1, m_entity2));
        List<Employee> allEmployee = m_serviceImpl.getAllEmployee();
        assertEquals(2, allEmployee.size());
        assertEquals(1, allEmployee.get(0).getId());
        assertEquals(2, allEmployee.get(1).getId());
    }
    
    @Test
    public void testFindEmployeeById() {
        when(m_repository.findById(1L)).thenReturn(Optional.of(m_entity1));
        Employee employee = m_serviceImpl.findEmployeeById(1);
        assertEquals(1L, employee.getId());
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void testFindEmployeeByPage() {
        Page pageResult = mock(Page.class);
        when(pageResult.getContent()).thenReturn(Arrays.asList(m_entity1, m_entity2));
        when(m_repository.findAll(PageRequest.of(0, 2))).thenReturn(pageResult);
        List<Employee> result = m_serviceImpl.findEmployeeByPage(0, 2);
        assertEquals(2, result.size());
    }

    @Test
    public void testAddEmployee() {
        Employee e = new Employee();
        e.setName("Test employee");
        m_serviceImpl.addEmployee(e);
        verify(m_repository).save(isA(EmployeeEntity.class));
    }

    @Test
    public void testSaveEmployee() {
        Employee e = new Employee();
        e.setId(1L);
        e.setName("Test employee");
        e.setAge(10);
        e.setOffice("Hanoi");
        e.setPosition("HR");
        e.setStartDate(new Date());
        e.setSalary(200L);
        when(m_repository.findById(1L)).thenReturn(Optional.of(m_entity1));
        when(m_repository.save(isA(EmployeeEntity.class))).thenReturn(EmployeeModelUtils.toEntity(e));
        Employee savedEmployee = m_serviceImpl.saveEmployee(e);
        assertEquals(e.getName(), savedEmployee.getName());
    }
    
    @Test
    public void testDeleteEmployeeById() {
        m_serviceImpl.deleteEmployeeById(1L);
        verify(m_repository).deleteById(1L);
    }
}
