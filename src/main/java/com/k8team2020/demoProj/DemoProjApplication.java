package com.k8team2020.demoProj;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoProjApplication {
	
	private Logger logger = LoggerFactory.getLogger(DemoProjApplication.class.getName());
	
	public static void main(String[] args) {
		SpringApplication.run(DemoProjApplication.class, args);
	}
	
	@GetMapping("/")
	public String returnAMessage(HttpServletRequest request) {
		return "Hi there! I'm a microservice powered by Java";
	}
	
	@GetMapping("/version")
	public String returnVersion() {
		String version = System.getenv("K8APPVERSION");
		return "Deployed version:"+version;
	}

}
