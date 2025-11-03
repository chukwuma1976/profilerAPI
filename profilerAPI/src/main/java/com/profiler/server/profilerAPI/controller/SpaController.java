package com.profiler.server.profilerAPI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpaController {
    
    @RequestMapping(value = {
        "/{path:[^\\.]*}",        // one segment without a dot
        "/**/{path:[^\\.]*}"      // multi-segment without a dot
    })
    public String forward() {
        return "forward:/index.html";
    }
}
