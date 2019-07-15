package com.zaynsolutions.glusterapp;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gfscontroller")
public class GlusterFSController  extends SpringBootServletInitializer {

		@GetMapping("/listFiles")
	    String listFiles() {          
	             return "Here I am going to return all files !";           
	    }
	    
	    @RequestMapping(value = "/storeFile", method = RequestMethod.POST)
	    public String storeFile(@RequestBody GFile gfile) {	      
	        return "File created successfully. File name is "+gfile.getName()+". The file contents are "+gfile.getContent() ;
	    }
   
}