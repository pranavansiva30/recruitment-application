package com.pranavan.web.rest;

import com.pranavan.web.model.JobDetail;
import com.pranavan.web.service.JobDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by pranavan on 7/12/18.
 */
@Component
@Path("/jobdetail")
public class JobDetailApi {


    @Autowired
    private JobDetailService jobDetailService;
    @GET
    @Produces("application/json")
    @Path("/")
    public List<JobDetail> getAllJobDetail(){

        return jobDetailService.getJobDetails();
    }
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public JobDetail getJobDetailById(final @PathParam("id") Long id){
        return jobDetailService.findOne(id);

    }
}
