package com.pranavan.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by pranavan on 7/13/18.
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseFormController {
    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(final @RequestParam(required = false, value = "q") String query) {
        final Model model = new ExtendedModelMap();
        model.addAttribute("pagetitle", "Report");
        return model;

    }
}
