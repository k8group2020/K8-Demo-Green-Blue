package com.k8team2020.demoProj.models;

public class Version {

    private String version;
    private String deploymentName;
    
    public Version() {
    }
    
    public Version(String version) {
        this.version = version;
    }
    
    public Version(String version, String deploymentName) {
        this.version = version;
        this.deploymentName = deploymentName;
    }
    
    public String getVersion() {
        return version;
    }
    
    public String getDeploymentName(){
        return deploymentName;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    public void setDeploymentName(String deploymentName){
        this.deploymentName = deploymentName;
    }
    
}
