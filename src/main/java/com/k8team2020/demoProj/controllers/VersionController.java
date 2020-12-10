package com.k8team2020.demoProj.controllers;

import com.k8team2020.demoProj.models.Version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    
    @GetMapping({"/version", "/version.html"})
    public Version index(){
        String versionString = System.getenv("");
        versionString = versionString == null ? "0.0.1" : versionString;
        
        Version version = new Version();
        version.setVersion(versionString);
        return version;
    }

}