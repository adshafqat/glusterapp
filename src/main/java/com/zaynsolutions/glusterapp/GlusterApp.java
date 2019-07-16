package com.zaynsolutions.glusterapp;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
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
	   System.out.println(path);
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
	
   @RequestMapping(value = "//storeFile", method = RequestMethod.POST,consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)	
   public String storeFile(GFile gfile) {	      
	   String path=env.getProperty("file.path");
	   File file = new File(path+"/"+gfile.getName());
	   try{
	 //Create the file
	 if (file.createNewFile())
	 {
	     System.out.println("File is created!");
	 } else {
	     System.out.println("File already exists.");
	 }
	  
	 //Write Content
	 FileWriter writer = new FileWriter(file);
	 writer.write(gfile.getContent());
	 writer.close();
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
       return "File created successfully. File name is "+path+"/"+gfile.getName()+". The file contents are "+gfile.getContent()+". If you want to see all files click here <a href='/listFiles'>List Files</a>" ;
   }


}