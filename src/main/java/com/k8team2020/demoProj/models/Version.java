package com.k8team2020.demoProj.models;

public class Version {

    private String version;
    private String release;
    
    public Version() {
    }
    
    public Version(String version) {
        this.version = version;
    }
    
    public Version(String version, String release) {
        this.version = version;
        this.release = release;
    }
    
    public String getVersion() {
        return version;
    }
    
    public String getRelease(){
        return release;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    
    public void setRelease(String release){
        this.release = release;
    }
    
}
