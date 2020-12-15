package com.k8team2020.demoProj.controllers;

import com.k8team2020.demoProj.models.Version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    
    @GetMapping({"/version", "/version.html"})
    public Version index(){
        String versionString = System.getenv("K8APPVERSION");
        String deploymentName = System.getenv("POD_RELEASE");
        
        versionString = versionString == null ? "0.0.1" : versionString;
        deploymentName = deploymentName == null ? "BLUE" : deploymentName;
        
        //Create an obj and set the values
        Version version = new Version();
        version.setVersion(versionString);
        version.setDeploymentName(deploymentName);
        
        return version;
    }

}
