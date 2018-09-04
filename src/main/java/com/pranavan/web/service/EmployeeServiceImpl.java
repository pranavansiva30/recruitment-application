package com.pranavan.web.service;

import com.pranavan.web.dao.EmployeeDao;
import com.pranavan.web.dao.JobDetailDao;
import com.pranavan.web.model.Employee;
import com.pranavan.web.model.JobDetail;
import com.pranavan.web.util.RecruitmentCalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by pranavan on 7/13/18.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Value("${recruitment.employee.amountOfgroupPeople}")
    private Integer amountOfgroupPeople;
    @Value("${recruitment.employee.commission}")
    private Double commission ;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private JobDetailDao jobDetailDao;

    @Override
    public Employee findOne(Long id) {
        return employeeDao.getById(id);
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeDao.delete(id);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeDao.findAll();
    }

    @Override
    public List<Employee> getEmployeesByHeadhunterAndRecruitedDate(Long headhunterId, Date fromDate, Date toDate) {
        List<Employee> employeeList=employeeDao.findByHeadhunterAndRecruitedDate(headhunterId,fromDate,toDate);
        return employeeList;
    }

    @Override
    public Double getRecruitedCostByHeadhunterAndRecruitedDate(Long headhunterId, Date fromDate, Date toDate) {
        List<JobDetail> jobDetails=jobDetailDao.findAll();
        Double totalCost=0.0;
        if(jobDetails.size()>0){
            for (JobDetail jobDetail:
                    jobDetails) {
                Double costPerJob=0.0;
                List<Employee> employeeList=employeeDao.findByHeadhunterAndJobAndRecruitedDate(headhunterId, jobDetail.getCode(), fromDate, toDate);
                Integer noEmp=employeeList.size();
                Integer noOfGroups=noEmp/amountOfgroupPeople;
                Integer noOfInduvial=noEmp%amountOfgroupPeople;
                //costPerJob=noOfGroups*amountOfgroupPeople*jobDetail.getRecruitCostPerMan()*(1+commission/100)+noOfInduvial*jobDetail.getRecruitCostPerMan();
                costPerJob= RecruitmentCalUtil.getCostPerJob(amountOfgroupPeople,noOfGroups,noOfInduvial,jobDetail.getRecruitCostPerMan(),commission);
                totalCost+=costPerJob;

            }
        }

        return totalCost;
    }
}