package nghiem.learning.web.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "Employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column
    private String name;
    
    @Column
    private int age;
    
    @Column
    private String position;
    
    @Column
    private String office;
    
    @Column
    private Date startDate;
    
    @Column
    private long salary;
}
