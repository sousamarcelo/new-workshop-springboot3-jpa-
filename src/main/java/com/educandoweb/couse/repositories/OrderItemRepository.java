package com.educandoweb.couse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.couse.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> { //arg01 = o tipo da entidade, arg02 = a chave
	
}
