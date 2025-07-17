package com.educandoweb.couse.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.couse.entities.Order;
import com.educandoweb.couse.entities.User;
import com.educandoweb.couse.entities.enums.OrderStatus;
import com.educandoweb.couse.repositories.OrderRepository;
import com.educandoweb.couse.repositories.UserRepository;

/**
 * 
 */
/**
 * 
 */
@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
	
	//usando o "@Autowired" o spring faz a injeção de dependencia, associoando uma instancia do "userRepository".
	@Autowired  
	private UserRepository userRepository;
	
	@Autowired  
	private OrderRepository ordeRepository;
	
	
	// Com a implementação da interface o metodo abaixo é adicionado, tudo que for inserido nesse metodo será executado quando a aplicação foi iniciada
	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT,u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT,u1);
		
		//utilizando o objeto "userRepository" injetado é possivel utilizar o methodo saveAll que recebe uma lista dos elementos que serão salvos no banco.
		userRepository.saveAll(Arrays.asList(u1, u2));
		ordeRepository.saveAll(Arrays.asList(o1,o2,o3));		
	}
}
