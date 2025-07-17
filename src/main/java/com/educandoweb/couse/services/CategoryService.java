package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Category;
import com.educandoweb.couse.repositories.CategoryRepository;

//"@Component ou @Service"Regitrando a classe como componente para que as injeções de dependencias dessa classe utilizadas em outras classe funcione
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll() {
		return repository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get(); // o get retorna o objeto do tipo <Category> que estiver dentro do
							// "obj"(Opctional)
	}

}
