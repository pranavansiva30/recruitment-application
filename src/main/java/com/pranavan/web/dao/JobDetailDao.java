package com.pranavan.web.dao;

import com.pranavan.web.model.JobDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by pranavan on 7/12/18.
 */
public interface JobDetailDao extends JpaRepository<JobDetail, Long> {

    @Query("FROM JobDetail WHERE code=:code")
    JobDetail findByCode(@Param("code") String code);

}
