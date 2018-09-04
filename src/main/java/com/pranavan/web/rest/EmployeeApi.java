package com.pranavan.web.rest;

import com.pranavan.web.model.Employee;
import com.pranavan.web.model.JobDetail;
import com.pranavan.web.service.EmployeeService;
import com.pranavan.web.service.JobDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by pranavan on 7/13/18.
 */
@Component
@Path("/employee")
public class EmployeeApi {

    @Autowired
    private EmployeeService employeeService;
    @GET
    @Produces("application/json")
    @Path("/")
    public List<Employee> getAllEmployee(){

        return employeeService.getEmployees();
    }
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Employee getEmployeeById(final @PathParam("id") Long id){
        return employeeService.findOne(id);

    }
    @GET
    @Path("/getRecruitedCostByHeadhunterAndRecruitedDate/{headhunterId}/{year}/{month}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Double getRecruitedCostByHeadhunterAndRecruitedDate(final @PathParam("headhunterId")Long headhunterId, final @PathParam("year") Integer year,final @PathParam("month")  Integer month){
        Date monthstartDate=getStartDate(year, month);
        Date monthendDate=getEndDate(year, month);
        return employeeService.getRecruitedCostByHeadhunterAndRecruitedDate(headhunterId,monthstartDate,monthendDate);

    }
    @GET
    @Path("/getEmployeesByHeadhunterAndRecruitedDate/{headhunterId}/{year}/{month}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public List<Employee> getEmployeesByHeadhunterAndRecruitedDate(final @PathParam("headhunterId")Long headhunterId, final @PathParam("year") Integer year,final @PathParam("month")  Integer month){
        Date monthstartDate=getStartDate(year, month);
        Date monthendDate=getEndDate(year, month);
        return employeeService.getEmployeesByHeadhunterAndRecruitedDate(headhunterId,monthstartDate,monthendDate);

    }

    private Date getEndDate(Integer year, Integer month){
        GregorianCalendar gc = new GregorianCalendar(year, month, 0);
        Date monthstartDate = new Date(gc.getTime().getTime());
        return monthstartDate;
    }
    private Date getStartDate(Integer year, Integer month){
        GregorianCalendar gc = new GregorianCalendar(year, month-1, 1);
        Date monthEndDate = new Date(gc.getTime().getTime());
        return monthEndDate;
    }
}
