package com.educandoweb.couse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { //arg01 = o tipo da entidade, arg02 = a chave
	
}
