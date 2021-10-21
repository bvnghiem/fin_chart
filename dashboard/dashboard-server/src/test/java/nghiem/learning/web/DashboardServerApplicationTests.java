package nghiem.learning.web;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import nghiem.learning.web.controller.EmployeeController;

@SpringBootTest
class DashboardServerApplicationTests {

    @Autowired
    private EmployeeController m_emplController;
    
    @Test
    void contextLoads() {
        assertNotNull(m_emplController);
    }

}
