package com.educandoweb.couse.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.couse.entities.Category;
import com.educandoweb.couse.services.CategoryService;

//indica que essa classe é um controlador rest, os metodos constroem os endpoints da aplicação
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping
	public ResponseEntity<List<Category>> findAll() {
		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}") // "(value= "/{id}")" indica que a requisição vai aceitar um id dentro da url
	public ResponseEntity<Category> findById(@PathVariable Long id) { // indica para o methodo que recebera a informação de "/{id}" inserida na annotation GetMapping da requisição
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
