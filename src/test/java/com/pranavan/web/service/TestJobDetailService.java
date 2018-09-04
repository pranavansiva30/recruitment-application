package com.pranavan.web.service;

import com.pranavan.web.dao.JobDetailDao;
import com.pranavan.web.model.JobDetail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranavan on 7/15/18.
 */
@RunWith(SpringRunner.class)
public class TestJobDetailService {
    @MockBean
    private JobDetailDao jobDetailDao;
    private JobDetailService jobDetailService;
    private JobDetail job;
    List<JobDetail> jobDetails=new ArrayList<>();


    @Before
    public void setUp() {
        jobDetailService=new JobDetailServiceImpl();
        ReflectionTestUtils.setField(jobDetailService, "jobDetailDao", jobDetailDao);
        job=new JobDetail();
        job.setId(1L);
        job.setCode("mason");
        job.setRecruitCostPerMan(200.0);
        job.setTitle("Mason");
        jobDetails.add(job);
        Mockito.when(
                jobDetailDao.findAll()).thenReturn(jobDetails);
    }

    @Test
    public void testsaveJobDetail(){
        JobDetail jobDetailCreate=job;
        jobDetailCreate.setId(null);

        Mockito.when(
                jobDetailDao.save(jobDetailCreate)).thenReturn(job);
        JobDetail actual=jobDetailService.saveJobDetail(jobDetailCreate);
        Assert.assertEquals(actual,job);

    }

    @Test
    public void testupdateJobDetail(){
        JobDetail jobDetailUpdate=job;
        jobDetailUpdate.setRecruitCostPerMan(260.0);
        jobDetails.remove(job);
        jobDetails.add(jobDetailUpdate);

        Mockito.when(
                jobDetailDao.save(jobDetailUpdate)).thenReturn(jobDetailUpdate);
        JobDetail actual=jobDetailService.updateJobDetail(jobDetailUpdate);
        Assert.assertEquals(actual,jobDetailUpdate);

    }

    @Test
    public void testFindOne(){
        Mockito.when(
                jobDetailDao.findOne(1L)).thenReturn(job);
        JobDetail actual=jobDetailService.findOne(1L);
        Assert.assertEquals(actual,job);

    }
    @Test
    public void testGetJobDetails(){

        Mockito.when(
                jobDetailDao.findAll()).thenReturn(jobDetails);
        List <JobDetail> actual =jobDetailService.getJobDetails();
        Assert.assertEquals(actual.get(0),job);
    }
    @Test
    public void testGetJobDetailByCode(){
        Mockito.when(
                jobDetailDao.findByCode("mason")).thenReturn(job);

        JobDetail actual=jobDetailService.getJobDetailByCode("mason");
        Assert.assertEquals(actual,job);

    }
}
