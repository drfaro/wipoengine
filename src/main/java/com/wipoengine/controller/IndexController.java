package com.wipoengine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView home(ModelAndView model) throws Exception {
        model.setViewName("index");
        return model;
    }
    @GetMapping("/index")
    public ModelAndView index(ModelAndView model) throws Exception {
        model.setViewName("index");
        return model;
    }


}
