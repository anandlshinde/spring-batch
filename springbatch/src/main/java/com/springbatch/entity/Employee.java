package com.springbatch.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_employee")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Column(name = "employee_id")
    @Id
    private String id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "job_title")
    private String jobTitle;
    @Column(name = "department")
    private String department;
    @Column(name = "business_unit")
    private String businessUnit;
    @Column(name = "gender")
    private String gender;

}
