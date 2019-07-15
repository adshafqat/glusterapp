package com.zaynsolutions.glusterapp;

import org.springframework.stereotype.Component;

@Component
public class GFile {
	 
	  private String name;
	  private String content;

	  public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	GFile() {}

	  GFile(String name, String content) {
	    this.name = name;
	    this.content = content;
	  }
}
