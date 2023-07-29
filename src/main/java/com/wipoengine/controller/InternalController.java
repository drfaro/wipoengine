package com.wipoengine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InternalController {

    @GetMapping("/internal")
    public ModelAndView internal(ModelAndView model) throws Exception {
        model.setViewName("internal");
        return model;
    }

}
