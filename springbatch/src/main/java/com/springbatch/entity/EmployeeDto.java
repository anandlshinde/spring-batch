package com.springbatch.entity;

import lombok.*;

import javax.persistence.Column;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String id;
    private String fullName;
    private String jobTitle;
    private String department;
    private String businessUnit;
    private String gender;
}
