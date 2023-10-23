package com.springbatch.processor;

import com.springbatch.entity.Employee;
import org.springframework.batch.item.ItemProcessor;


public class EmployeeProcessor implements ItemProcessor<Employee,Employee> {

    @Override
    public Employee process(Employee employee) throws Exception {
        //TODO Process logic for employee data

        if(employee.getGender().contains("Male")){
            employee.setGender("1");
        }
        if(employee.getGender().contains("Female")){
            employee.setGender("2");
        }

        return employee;
    }
}
