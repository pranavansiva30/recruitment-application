package com.pranavan.web.service;

import com.pranavan.web.dao.JobDetailDao;
import com.pranavan.web.model.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pranavan on 7/12/18.
 */
@Service
public class JobDetailServiceImpl implements JobDetailService{
    @Autowired
    private JobDetailDao jobDetailDao;

    @Override
    public JobDetail findOne(Long id) {
        return jobDetailDao.findOne(id);
    }

    @Override
    public JobDetail saveJobDetail(JobDetail jobDetail) {
        return jobDetailDao.save(jobDetail);
    }

    @Override
    public JobDetail updateJobDetail(JobDetail jobDetail) {
        return jobDetailDao.save(jobDetail);
    }

    @Override
    public void deleteJobDetail(Long id) {
        jobDetailDao.delete(id);
    }

    @Override
    public List<JobDetail> getJobDetails() {
        return jobDetailDao.findAll();
    }

    @Override
    public JobDetail getJobDetailByCode(String code) {
        return jobDetailDao.findByCode(code);
    }
}
