package com.springbatch.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springbatch.entity.Employee;
import com.springbatch.entity.EmployeeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.io.DataInput;

@Slf4j
public class EmployeeProcessor implements ItemProcessor<EmployeeDto,Employee> {

    @Override
    public Employee process(EmployeeDto employeeDto) throws Exception {
        log.info("employeeDto department {} ",employeeDto.getDepartment());
        Employee employee=new Employee();
        employee.setId(employeeDto.getId());
        employee.setGender(employeeDto.getGender());
        employee.setDepartment(employeeDto.getDepartment());
        employee.setJobTitle(employeeDto.getJobTitle());
        employee.setBusinessUnit(employeeDto.getBusinessUnit());
        return employee;
    }
}
