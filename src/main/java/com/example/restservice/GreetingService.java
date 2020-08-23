package com.example.restservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

	@Autowired
	private GreetingRepository greetingRepo;
	
	
	public List<Greeting> retrieveGreeting(){
		return (List<Greeting>) greetingRepo.findAll();
	}
	
	public Optional<Greeting> retrieveGreeting(Long id){
		return greetingRepo.findById(id);
	}
	
	public List<Greeting> retrieveGreeting(String content){
		return greetingRepo.findByContent(content);
	}
	
	public Greeting createGreeting(Greeting greeting) {
		
		return greetingRepo.save(greeting);
	}
	
	public Optional<Greeting> updateGreeting(Long id,Greeting greeting){
		Optional<Greeting> greetingOptional = greetingRepo.findById(id);
		if(!greetingOptional.isPresent()) {
			return greetingOptional;
		}
		
		greeting.setId(id);
		return Optional.of(greetingRepo.save(greeting));
	}
	
	public boolean deleteGreeting(Long id) {
		try {
			greetingRepo.deleteById(id);
			return true;
		}catch(EmptyResultDataAccessException e) {
			return false;
		}
	}
}
