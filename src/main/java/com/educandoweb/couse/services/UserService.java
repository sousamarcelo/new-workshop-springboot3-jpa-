package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.User;
import com.educandoweb.couse.repositories.UserRepository;

//"@Component ou @Service"Regitrando a classe como componente para que as injeções de dependencias dessa classe utilizadas em outras classe funcione
@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get(); //o get retorna o objeto do tipo <User> que estiver dentro do "obj"(Opctional)
	}

}
