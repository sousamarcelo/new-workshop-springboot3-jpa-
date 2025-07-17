package com.educandoweb.couse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.educandoweb.couse.entities.User;
import com.educandoweb.couse.repositories.UserRepository;
import com.educandoweb.couse.services.exceptions.DatabaseException;
import com.educandoweb.couse.services.exceptions.ResourceNotFoundException;

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
		return obj.orElseThrow(() -> new ResourceNotFoundException(id)); //o metodo "orElseThrow()" tenta fezer um get, caso ocorre a exceção por vazio lança a exceção personalizada do argumento
	}
	
	//retornar o usuario salvo
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			if(repository.existsById(id)) {
				repository.deleteById(id);
			} else {
				throw new ResourceNotFoundException(id);
			}
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		User entity = repository.getReferenceById(id); //esse metodo "getReferenceById" é mais vantagoso que o "findById(id)" porque é um objeto monitorado sendo possivel trabalhar com ele, o "findById(id)" custaria o acesso ao banco para buscar o usuario. 
		updateData(entity, obj);
		return repository.save(entity);
	}

	//nesse metodo auxiliar nem todos os dados necessariamente devem ser atualizar, no exemplo atualizamos somente 3 dados. o id nem a senha serão atualizados nesse caso
	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
		
	}
}
