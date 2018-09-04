package com.pranavan.web.service;

import com.pranavan.web.model.JobDetail;

import java.util.List;

/**
 * Created by pranavan on 7/12/18.
 */
public interface JobDetailService {

    public JobDetail findOne(Long id);

    public JobDetail saveJobDetail(JobDetail jobDetail);
    public JobDetail updateJobDetail(JobDetail jobDetail);

    public void deleteJobDetail(Long id);

    public List<JobDetail> getJobDetails();
    public JobDetail getJobDetailByCode(String code);
}
