package com.pranavan.web.service;

import com.pranavan.web.model.Employee;

import java.util.Date;
import java.util.List;

/**
 * Created by pranavan on 7/13/18.
 */
public interface EmployeeService {

    public Employee findOne(Long id);

    public Employee saveEmployee(Employee employee);
    public Employee updateEmployee(Employee employee);

    public void deleteEmployee(Long id);

    public List<Employee> getEmployees();
    public List<Employee> getEmployeesByHeadhunterAndRecruitedDate(Long headhunterId,Date fromDate,Date toDate);
    public Double getRecruitedCostByHeadhunterAndRecruitedDate(Long headhunterId,Date fromDate,Date toDate);
}
