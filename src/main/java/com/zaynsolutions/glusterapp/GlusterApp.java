package com.zaynsolutions.glusterapp;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//Spring Boot 2.x

//Spring Boot 1.x
//import org.springframework.boot.web.support.SpringBootServletInitializer;
 

@RestController
class GlusterApp {
 
   @RequestMapping("/hello/{name}")
   String hello(@PathVariable String name) {
 
       return "Hi " + name + " !";
 
   }

   @RequestMapping("/listFiles")
   String listFiles() {          
            return "Here I am going to return all files !";           
   }

	//http://gfs-dev.apps.us-west-2.online-starter.openshift.com/glusterapp-0.0.1-SNAPSHOT/gfscontroller/listFiles
	//git add .
	//git commit -m 'added config in pom'
	//git push origin master
	
	
   @RequestMapping(value = "/storeFile", method = RequestMethod.POST)
   public String storeFile(@RequestBody GFile gfile) {	      
       return "File created successfully. File name is "+gfile.getName()+". The file contents are "+gfile.getContent() ;
   }


}
