package nghiem.learning.web.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import nghiem.learning.web.entity.EmployeeEntity;
import nghiem.learning.web.model.Employee;
import nghiem.learning.web.persistence.EmployeeRepository;

@Component
public class EmployeeBasicCrudServiceImpl implements EmployeeBasicCrudService {

    private static final Logger m_logger = LogManager.getLogger(EmployeeBasicCrudServiceImpl.class);
    
    @Autowired
    private EmployeeRepository m_empRepo;

    @Override
    public List<Employee> getAllEmployee() {
        List<EmployeeEntity> result = m_empRepo.findAll();
        m_logger.info(String.format("Found %s employee from DB", result.size()));
        return EmployeeModelUtils.fromEntities(result);
    }

    @Override
    public List<Employee> findEmployeeByPage(int pageNumber, int pageSize) {
        Pageable pageConfig = PageRequest.of(pageNumber, pageSize);
        Page<EmployeeEntity> pageResult = m_empRepo.findAll(pageConfig);
        return EmployeeModelUtils.fromEntities(pageResult.getContent());
    }

    @Override
    public Employee findEmployeeById(long id) {
        Optional<EmployeeEntity> result = m_empRepo.findById(id);
        return result.isPresent() ? EmployeeModelUtils.fromEntity(result.get()) : null;
    }

    @Override
    public void addEmployee(Employee e) {
        m_empRepo.save(EmployeeModelUtils.toEntity(e));
    }

    @Override
    public Employee saveEmployee(Employee e) {
        Optional<EmployeeEntity> result = m_empRepo.findById(e.getId());
        if(result.isPresent()) {
            EmployeeEntity entity = result.get();
            EmployeeModelUtils.updateEntity(entity, e);
            EmployeeEntity savedEntity = m_empRepo.save(entity);
            return EmployeeModelUtils.fromEntity(savedEntity);
        } else {
            throw new EntityNotFoundException("Employee not found");
        }
    }

    @Override
    public void deleteEmployeeById(long id) {
        m_empRepo.deleteById(id);
    }
}
