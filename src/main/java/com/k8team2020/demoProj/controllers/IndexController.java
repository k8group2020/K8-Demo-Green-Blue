package com.k8team2020.demoProj.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    
    @GetMapping({"/", "index.html"})
    public String index(){
        return "Micro app service is ready for you. Greetings, if you are seeing this, you are on a newer version";
    }

}
