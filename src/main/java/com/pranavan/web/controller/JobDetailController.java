package com.pranavan.web.controller;

import com.pranavan.web.model.JobDetail;
import com.pranavan.web.service.JobDetailService;
import com.pranavan.web.util.CommonConstants;
import com.pranavan.web.util.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by pranavan on 7/12/18.
 */
@Controller
@RequestMapping("/jobdetail")
public class JobDetailController extends BaseFormController{
    @Autowired
    private JobDetailService jobDetailService;

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(final @RequestParam(required = false, value = "q") String query) {
        final Model model = new ExtendedModelMap();
        model.addAttribute("pagetitle", "Job Detail");
        return model;

    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public JsonResponse onSubmit(@RequestBody final JobDetail jobDetail, final BindingResult errors, final HttpServletRequest request
    ) throws ServletRequestBindingException {
        final Locale locale = request.getLocale();
        final boolean isNew = jobDetail.getId()== null;
        final JsonResponse res = new JsonResponse();
        try{


            jobDetailService.saveJobDetail(jobDetail);
            res.setResult(jobDetailService.getJobDetails());
            res.setStatus(CommonConstants.Status.SUCCESS.toString());
            String key = (isNew) ? "jobdetail.added" : "jobdetail.updated";
            res.setMessage(getText(key, locale));
            saveMessage(request, getText(key, locale));
            return res;

        } catch (Exception e) {
            res.setStatus(CommonConstants.Status.FAIL.toString());
            String messageKey = (isNew) ? "jobdetail.added.failed" : "jobdetail.updated.failed";
            log.error(e.getMessage());
            res.setMessage(getText(messageKey, locale));
            saveMessage(request, getText(messageKey, locale));
            return res;
        }
    }


    @RequestMapping(method = RequestMethod.PUT, produces = "application/json",value="/{id}")
    @ResponseBody
    public JsonResponse updateCourse(@PathVariable("id") final Long id, @RequestBody final JobDetail jobDetail, final BindingResult errors, final HttpServletRequest request
    ) throws ServletRequestBindingException {
        final Locale locale = request.getLocale();
        final boolean isNew = jobDetail.getId()== null;
        final JsonResponse res = new JsonResponse();
        try{


            jobDetailService.updateJobDetail(jobDetail);
            res.setResult(jobDetailService.getJobDetails());
            res.setStatus(CommonConstants.Status.SUCCESS.toString());
            String key = (isNew) ? "jobdetail.added" : "jobdetail.updated";
            res.setMessage(getText(key, locale));
            saveMessage(request, getText(key, locale));
            return res;

        } catch (Exception e) {
            res.setStatus(CommonConstants.Status.FAIL.toString());
            String messageKey = (isNew) ? "jobdetail.added.failed" : "jobdetail.updated.failed";
            log.error(e.getMessage());
            res.setMessage(getText(messageKey, locale));
            saveMessage(request, getText(messageKey, locale));
            return res;
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    @ResponseBody
    public JsonResponse delete(@PathVariable("id") final Long id, final HttpServletRequest request) {
        final Locale locale = request.getLocale();
        final JsonResponse jsonResponse = new JsonResponse();
        try{
            jobDetailService.deleteJobDetail(id);
            jsonResponse.setStatus(CommonConstants.Status.SUCCESS.toString());
            jsonResponse.setMessage(getText("jobdetail.deleted", locale));
            jsonResponse.setResult(jobDetailService.getJobDetails());
        }
        catch(Exception e){
            log.error(e.getMessage());
            jsonResponse.setStatus(CommonConstants.Status.FAIL.toString());
            jsonResponse.setMessage(getText("jobdetail.deleted.fail", locale));
        }
        return jsonResponse;
    }
}
