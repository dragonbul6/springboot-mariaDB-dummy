package com.example.restservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
	
	@Autowired
	private GreetingService service;
	
	@GetMapping()
	public List<Greeting> getGreeting(){
		return service.retrieveGreeting();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getGreeting(@PathVariable Long id){
		Optional<Greeting> greeting = service.retrieveGreeting(id);
		
		if(!greeting.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(greeting);		
		
	}
	
	@GetMapping(params = "content")
	public List<Greeting> getGreeting(@RequestParam(value = "content") String content){
		return service.retrieveGreeting(content);
	}
	
	@PostMapping()
	public ResponseEntity<?> postGreeting(@RequestBody Greeting body){
		Greeting greeting = service.createGreeting(body);
		return ResponseEntity.status(HttpStatus.CREATED).body(greeting);
	}
 	
	@PutMapping("/{id}")
	public ResponseEntity<?> putGreeting(@PathVariable Long id,@RequestBody Greeting body){
		Optional<Greeting> greeting = service.updateGreeting(id, body);
		if(!greeting.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteGreeting(@PathVariable Long id){
		if(!service.deleteGreeting(id)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().build();
	}
	
	
}
