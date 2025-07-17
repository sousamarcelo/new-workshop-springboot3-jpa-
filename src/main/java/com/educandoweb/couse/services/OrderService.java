package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.Order;
import com.educandoweb.couse.repositories.OrderRepository;

//"@Component ou @Service"Regitrando a classe como componente para que as injeções de dependencias dessa classe utilizadas em outras classe funcione
@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get(); //o get retorna o objeto do tipo <Order> que estiver dentro do "obj"(Opctional)
	}

}
