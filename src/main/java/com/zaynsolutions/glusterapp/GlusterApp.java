package com.zaynsolutions.glusterapp;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
 
	@Autowired
	private Environment env;
	
   @RequestMapping("/hello/{name}")
   String hello(@PathVariable String name) {
 
       return "Hi " + name + " !";
 
   }

   @RequestMapping("/listFiles")
   String listFiles() {          
            
	   List<String> result=null;
	   String path=env.getProperty("file.path");
		try (Stream<Path> walk = Files.walk(Paths.get(path))) {

			 result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());

			result.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}
	   if(result!=null) {
		   String response="List of files !";
		   for(int i=0;i<result.size();i++) {
			   response=response+"<br>"+result.get(i);
		   }
		   return response;
	   }
	   else
		   return "No file is avilable in the folder";      

   }

	//http://gfs-dev.apps.us-west-2.online-starter.openshift.com//listFiles
	//git add .
	//git commit -m 'added config in pom'
	//git push origin master
	
	
   @RequestMapping(value = "/storeFile", method = RequestMethod.POST)
   public String storeFile(@RequestBody GFile gfile) {	      
       return "File created successfully. File name is "+gfile.getName()+". The file contents are "+gfile.getContent() ;
   }


}
