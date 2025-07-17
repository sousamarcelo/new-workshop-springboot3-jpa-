package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Product;
import com.educandoweb.couse.repositories.ProductRepository;

//"@Component ou @Service"Regitrando a classe como componente para que as injeções de dependencias dessa classe utilizadas em outras classe funcione
@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		return obj.get(); //o get retorna o objeto do tipo <Product> que estiver dentro do "obj"(Opctional)
	}

}
