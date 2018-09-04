package com.pranavan.web.controller;

import com.pranavan.web.model.Employee;
import com.pranavan.web.service.EmployeeService;
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
 * Created by pranavan on 7/13/18.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends BaseFormController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(final @RequestParam(required = false, value = "q") String query) {
        final Model model = new ExtendedModelMap();
        model.addAttribute("pagetitle", "Employee");
        return model;

    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public JsonResponse onSubmit(@RequestBody final Employee employee, final BindingResult errors, final HttpServletRequest request
    ) throws ServletRequestBindingException {
        final Locale locale = request.getLocale();
        final boolean isNew = employee.getId()== null;
        final JsonResponse res = new JsonResponse();
        try{


            employeeService.saveEmployee(employee);
            res.setResult(employeeService.getEmployees());
            res.setStatus(CommonConstants.Status.SUCCESS.toString());
            String key = (isNew) ? "employee.added" : "employee.updated";
            res.setMessage(getText(key, locale));
            saveMessage(request, getText(key, locale));
            return res;

        } catch (Exception e) {
            res.setStatus(CommonConstants.Status.FAIL.toString());
            String messageKey = (isNew) ? "headhunter.added.failed" : "headhunter.updated.failed";
            log.error(e.getMessage());
            res.setMessage(getText(messageKey, locale));
            saveMessage(request, getText(messageKey, locale));
            return res;
        }
    }


    @RequestMapping(method = RequestMethod.PUT, produces = "application/json",value="/{id}")
    @ResponseBody
    public JsonResponse updateCourse(@PathVariable("id") final Long id, @RequestBody final Employee employee, final BindingResult errors, final HttpServletRequest request
    ) throws ServletRequestBindingException {
        final Locale locale = request.getLocale();
        final boolean isNew = employee.getId()== null;
        final JsonResponse res = new JsonResponse();
        try{


            employeeService.updateEmployee(employee);
            res.setResult(employeeService.getEmployees());
            res.setStatus(CommonConstants.Status.SUCCESS.toString());
            String key = (isNew) ? "employee.added" : "employee.updated";
            res.setMessage(getText(key, locale));
            saveMessage(request, getText(key, locale));
            return res;

        } catch (Exception e) {
            res.setStatus(CommonConstants.Status.FAIL.toString());
            String messageKey = (isNew) ? "employee.added.failed" : "employee.updated.failed";
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
            employeeService.deleteEmployee(id);
            jsonResponse.setStatus(CommonConstants.Status.SUCCESS.toString());
            jsonResponse.setMessage(getText("employee.deleted", locale));
            jsonResponse.setResult(employeeService.getEmployees());
        }
        catch(Exception e){
            log.error(e.getMessage());
            jsonResponse.setStatus(CommonConstants.Status.FAIL.toString());
            jsonResponse.setMessage(getText("employee.deleted.fail", locale));
        }
        return jsonResponse;
    }

}
