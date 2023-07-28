package com.wipoengine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InternalController {

    @GetMapping("/internal")
    public String internal() throws Exception {
        return "internal";
    }

}
