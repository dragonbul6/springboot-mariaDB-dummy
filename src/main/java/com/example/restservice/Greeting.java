package com.example.restservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Greeting {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private  long id;
	private  String content;
	
	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Greeting() {
		
	}

	public Greeting(long id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
}
