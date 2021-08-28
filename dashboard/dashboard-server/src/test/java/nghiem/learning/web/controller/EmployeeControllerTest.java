package nghiem.learning.web.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import nghiem.learning.web.model.Employee;
import nghiem.learning.web.service.EmployeeBasicCrudService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
    
    @LocalServerPort
    private int m_testPort;
    
    @MockBean
    private EmployeeBasicCrudService m_basicCrudService;
    
    @Autowired
    private TestRestTemplate m_restTemplate;
    
    @Test
    public void testGetAll() throws Exception {
        when(m_basicCrudService.getAllEmployee()).thenReturn(Collections.emptyList());
        ResponseEntity<String> response = m_restTemplate.getForEntity(
            new URL("http://localhost:" + m_testPort + "/employee").toString(), String.class);
        assertEquals("[]", response.getBody());
    }

    @Test
    public void testGet1() throws Exception {
        Employee e = new Employee();
        e.setId(1L);
        e.setName("Test employee");
        when(m_basicCrudService.findEmployeeById(1L)).thenReturn(e);
        ResponseEntity<String> response = m_restTemplate.getForEntity(
            new URL("http://localhost:" + m_testPort + "/employee/1").toString(), String.class);
        assertEquals("{\"id\":1,\"name\":\"Test employee\",\"age\":0,\"position\":null,\"office\":null,\"startDate\":null,\"salary\":0}", response.getBody());
    }

    @Test
    public void testGetAllByPage() throws Exception {
        Employee e = new Employee();
        e.setId(1L);
        e.setName("Test employee");
        when(m_basicCrudService.findEmployeeByPage(0, 1)).thenReturn(Arrays.asList(e));
        ResponseEntity<String> response = m_restTemplate.getForEntity(
            new URL("http://localhost:" + m_testPort + "/employee/page?pageNumber=0&pageSize=1").toString(), String.class);
        assertEquals("[{\"id\":1,\"name\":\"Test employee\",\"age\":0,\"position\":null,\"office\":null,\"startDate\":null,\"salary\":0}]", response.getBody());
    }

    @Test
    public void testAdd() throws Exception {
        Employee e = new Employee();
        e.setId(1L);
        e.setName("Test employee");
        ResponseEntity<String> response = m_restTemplate.postForEntity(
            new URL("http://localhost:" + m_testPort + "/employee/add").toString(), e, String.class);
        verify(m_basicCrudService).addEmployee(isA(Employee.class));
        assertNull(response.getBody());
    }

    @Test
    public void testSave() throws Exception {
        Employee e = new Employee();
        e.setId(1L);
        e.setName("Test employee");
        ResponseEntity<String> response = m_restTemplate.postForEntity(
            new URL("http://localhost:" + m_testPort + "/employee/save").toString(), e, String.class);
        verify(m_basicCrudService).saveEmployee(isA(Employee.class));
        assertNull(response.getBody());
    }

    @Test
    public void testDelete() throws Exception {
        Employee e = new Employee();
        e.setId(1L);
        e.setName("Test employee");
        ResponseEntity<String> response = m_restTemplate.postForEntity(
            new URL("http://localhost:" + m_testPort + "/employee/delete/1").toString(), null, String.class);
        verify(m_basicCrudService).deleteEmployeeById(1L);
        assertNull(response.getBody());
    }
}
