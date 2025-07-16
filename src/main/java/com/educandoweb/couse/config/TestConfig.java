package com.educandoweb.couse.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.couse.entities.User;
import com.educandoweb.couse.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	//usando o "@Autowired" o spring faz a injeção de dependencia, associoando uma instancia do "userRepository".
	@Autowired  
	private UserRepository userRepository;
	
	
	// Com a implementação da interface o metodo abaixo é adicionado, tudo que for inserido nesse metodo será executado quando a aplicação foi iniciada
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		//utilizando o objeto "userRepository" injetado é possivel utilizar o methodo saveAll que recebe uma lista dos elementos que serão salvos no banco.
		userRepository.saveAll(Arrays.asList(u1, u2));
		
	}

}
