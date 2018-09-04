package com.pranavan.web.controller;

import com.pranavan.web.Enum.GenderType;
import com.pranavan.web.model.Employee;
import com.pranavan.web.model.Headhunter;
import com.pranavan.web.model.JobDetail;
import com.pranavan.web.service.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by pranavan on 7/15/18.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(value = EmployeeController.class, secure = false)
public class TestEmployeeController {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;

    private String employeeString="{\"id\":null,\"firstName\":\"pranavan\",\"lastName\":\"sivasundaram\",\"gender\":\"MALE\",\"headhunter\":{\"id\":1,\"name\":\"ABC\",\"active\":true},\"job\":{\"id\":1,\"title\":\"Mason\",\"code\":\"mason\",\"recruitCostPerMan\":200.0},\"recruitedDate\":\"2018-07-13\"}";
    private String employeeStringUpdate="{\"id\":1,\"firstName\":\"pranavan\",\"lastName\":\"sivasun\",\"gender\":\"MALE\",\"headhunter\":{\"id\":1,\"name\":\"ABC\",\"active\":true},\"job\":{\"id\":1,\"title\":\"Mason\",\"code\":\"mason\",\"recruitCostPerMan\":200.0},\"recruitedDate\":\"2018-07-13\"}";

    private Employee employee;
    private Employee employeeUdate;
    private Headhunter headhunter;
    private JobDetail job;
    private List<Employee> employeeList=new ArrayList<>();

    @Before
    public void setUp() {
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
        employee.setId(1L);
        employee.setFirstName("pranavan");
        employee.setLastName("sivasundaram");
        employee.setGender(GenderType.MALE);
        employee.setHeadhunter(headhunter);
        employee.setJob(job);
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        try {
            Date date = simpleDateFormat.parse("2018-07-13");
            employee.setRecruitedDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        employeeList.add(employee);
        Mockito.when(
                employeeService.getEmployees()).thenReturn(employeeList);




    }


    @Test
    public void testCreateEmployee() throws Exception {

        Mockito.when(
                employeeService.saveEmployee(
                        Mockito.any(Employee.class))).thenReturn(employee);

        // Send employee as body to /employee
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/employee")
                .accept(MediaType.APPLICATION_JSON).content(employeeString)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());


    }

    @Test
    public void testUpdateEmployee() throws Exception {
        employeeUdate=employee;
        employeeUdate.setLastName("sivasun");
        employeeList.remove(employee);
        employeeList.add(employeeUdate);
        Mockito.when(
                employeeService.updateEmployee(
                        Mockito.any(Employee.class))).thenReturn(employeeUdate);

        // Send employee as body to /employee
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/employee/1")
                .accept(MediaType.APPLICATION_JSON).content(employeeStringUpdate)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());



    }

    @Test
    public void testDeleteEmployee() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/employee/1")
                .accept(MediaType.APPLICATION_JSON).content(employeeStringUpdate)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());



    }
}
