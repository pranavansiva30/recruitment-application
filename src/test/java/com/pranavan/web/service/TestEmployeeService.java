package com.pranavan.web.service;

import com.pranavan.web.Enum.GenderType;
import com.pranavan.web.dao.EmployeeDao;
import com.pranavan.web.dao.JobDetailDao;
import com.pranavan.web.model.Employee;
import com.pranavan.web.model.Headhunter;
import com.pranavan.web.model.JobDetail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by pranavan on 7/15/18.
 */
@RunWith(SpringRunner.class)
public class TestEmployeeService {
    @MockBean
    private EmployeeDao employeeDao;
    @MockBean
    private JobDetailDao jobDetailDao;

    private EmployeeService employeeService;

    private Employee employee;
    private Headhunter headhunter;
    private JobDetail job;
    private List<Employee> employeeList=new ArrayList<>();
    List<JobDetail> jobDetails=new ArrayList<>();
    String pattern = "MM-dd-yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);


    @Before
    public void setUp() {
        employeeService=new EmployeeServiceImpl();

        ReflectionTestUtils.setField(employeeService, "jobDetailDao", jobDetailDao);
        ReflectionTestUtils.setField(employeeService, "employeeDao", employeeDao);
        ReflectionTestUtils.setField(employeeService, "amountOfgroupPeople", 5);
        ReflectionTestUtils.setField(employeeService, "commission", 10.0);
        employee=new Employee();
        headhunter= new Headhunter();
        headhunter.setId(1L);
        headhunter.setActive(true);
        headhunter.setName("ABC");
        job=new JobDetail();
        job.setId(1L);
        job.setCode("mason");
        job.setRecruitCostPerMan(200.0);
        job.setTitle("Mason");
        jobDetails.add(job);
        employee.setId(1L);
        employee.setFirstName("pranavan");
        employee.setLastName("sivasundaram");
        employee.setGender(GenderType.MALE);
        employee.setHeadhunter(headhunter);
        employee.setJob(job);

        try {
            Date date = simpleDateFormat.parse("2018-07-13");
            employee.setRecruitedDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        employeeList.add(employee);
        Mockito.when(
                employeeDao.findAll()).thenReturn(employeeList);




    }
    @Test
    public void testsaveEmployee(){
        Employee employeeCreate=employee;
        employeeCreate.setId(null);

        Mockito.when(
                employeeDao.save(employeeCreate)).thenReturn(employee);
        Employee actual=employeeService.saveEmployee(employeeCreate);
        Assert.assertEquals(actual,employee);

    }
    @Test
    public void testupdateEmployee(){
        Employee employeeUpdate=employee;
        employeeUpdate.setLastName("sivasun");

        Mockito.when(
                employeeDao.save(employeeUpdate)).thenReturn(employeeUpdate);
        Employee actual=employeeService.saveEmployee(employeeUpdate);
        Assert.assertEquals(actual,employeeUpdate);

    }
    @Test
    public void testFindOne(){
        Mockito.when(
                employeeDao.getById(1L)).thenReturn(employee);
        Employee actual=employeeService.findOne(1L);
        Assert.assertEquals(actual,employee);

    }
    @Test
    public void testGetEmployees(){
        List< Employee> actual=employeeService.getEmployees();
        Assert.assertEquals(actual.get(0).getFirstName(),"pranavan");




    }
@Test
    public void testGetRecruitedCostByHeadhunterAndRecruitedDate(){
        Date fromDate=null;
        Date toDate=null;
        try {
            fromDate = simpleDateFormat.parse("2018-07-13");
            toDate = simpleDateFormat.parse("2018-07-16");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Mockito.when(
                jobDetailDao.findAll()).thenReturn(jobDetails);
        Mockito.when(
                employeeDao.findByHeadhunterAndJobAndRecruitedDate(1L, "mason", fromDate, toDate)).thenReturn(employeeList);


      Double actual=employeeService.getRecruitedCostByHeadhunterAndRecruitedDate(1L,fromDate,toDate);
        Assert.assertEquals(actual,new Double(200.0));
    }
    public void testGetEmployeesByHeadhunterAndRecruitedDate()
    {

        Date fromDate=null;
        Date toDate=null;
        try {
            fromDate = simpleDateFormat.parse("2018-07-13");
            toDate = simpleDateFormat.parse("2018-07-16");

        } catch (ParseException e) {
            e.printStackTrace();
        }
          Mockito.when(
                employeeDao.findByHeadhunterAndRecruitedDate(1L, fromDate, toDate)).thenReturn(employeeList);


        List<Employee> actual=employeeService.getEmployeesByHeadhunterAndRecruitedDate(1L,fromDate,toDate);
        Assert.assertEquals(actual.get(0).getFirstName(),"pranavan");

    }
}
