package com.educandoweb.couse.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.couse.entities.User;
import com.educandoweb.couse.services.UserService;

//indica que essa classe é um controlador rest, os metodos constroem os endpoints da aplicação
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value= "/{id}")   											//"(value= "/{id}")" indica que a requisição vai aceitar um id dentro da url
	public ResponseEntity<User> findById(@PathVariable Long id) { 			//indica para o methodo que recebera a informação de "/{id}" inserida na annotation GetMapping da requisição
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	

}
