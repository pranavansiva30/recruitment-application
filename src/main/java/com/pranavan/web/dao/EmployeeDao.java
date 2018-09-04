package com.pranavan.web.dao;

import com.pranavan.web.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by pranavan on 7/13/18.
 */
public interface EmployeeDao extends JpaRepository<Employee, Long> {

    @Query("select distinct emp FROM Employee emp inner join emp.headhunter as headhunter inner join emp.job as job WHERE (headhunter.id=:headhunterId and job.code=:jobCode) and (emp.recruitedDate between :fromDate and :toDate)")
    public List<Employee> findByHeadhunterAndJobAndRecruitedDate(@Param("headhunterId") Long headhunterId,@Param("jobCode") String jobCode,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
    @Query("FROM Employee emp inner join fetch emp.headhunter as headhunter inner join fetch emp.job as job WHERE emp.id=:id")
    Employee getById(@Param("id") Long id);

    @Query("select distinct emp FROM Employee emp inner join emp.headhunter as headhunter WHERE (headhunter.id=:headhunterId) and (emp.recruitedDate between :fromDate and :toDate)")
    public List<Employee> findByHeadhunterAndRecruitedDate(@Param("headhunterId") Long headhunterId,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

}
