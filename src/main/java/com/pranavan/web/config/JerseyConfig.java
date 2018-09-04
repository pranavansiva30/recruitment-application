package com.pranavan.web.config;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.pranavan.web.model.JobDetail;
import com.pranavan.web.rest.EmployeeApi;
import com.pranavan.web.rest.HeadhunterApi;
import com.pranavan.web.rest.JobDetailApi;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("/api")
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {

        this.register(new JacksonJsonProvider(new HibernateAwareObjectMapper()));
        this.property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        this.property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        //this.packages(true, "com.pranavan.web.rest");

        register(EmployeeApi.class);
        register(JobDetailApi.class);
        register(HeadhunterApi.class);
    }
}