package nghiem.learning.web.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import nghiem.learning.web.DashboardAppViewConfiguration;
import nghiem.learning.web.model.Employee;

@Controller
public class EmployeeThymeleafController {

    private static final Object PORT_SEPARATOR = ":";
    private static final Object PROTOCOL_SEPARATOR = "//";
    private static final Object PATH_SEPARATOR = "/";

    @Autowired
    DashboardAppViewConfiguration m_viewConfiguration;
    
    @Autowired
    private RestTemplate m_restTemplate;
    
    private String m_backendURLPrefix;

    @SuppressWarnings("unchecked")
    @GetMapping("/")
    public String getAll(Model model) {
        List<Employee> employees = m_restTemplate.getForObject(URI.create(getBackEndUrlPrefix() + "employee"), List.class);
        model.addAttribute("ei", employees);
        return "/index";
    }
    
    private String getBackEndUrlPrefix() {
        if (m_backendURLPrefix == null) {
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(m_viewConfiguration.getProtocol())
                .append(PORT_SEPARATOR).append(PROTOCOL_SEPARATOR)
                .append(m_viewConfiguration.getHost())
                .append(PORT_SEPARATOR)
                .append(m_viewConfiguration.getPort())
                .append(PATH_SEPARATOR);
            
            m_backendURLPrefix = urlBuilder.toString();
        }
        
        return m_backendURLPrefix;
    }
}
