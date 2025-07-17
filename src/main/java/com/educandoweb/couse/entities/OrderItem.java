package com.educandoweb.couse.entities;

import java.io.Serializable;
import java.util.Objects;

import com.educandoweb.couse.entities.pk.OrderItemPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId //porque é uma chave composta não foi utilizado o @Id....
	private OrderItemPK id = new OrderItemPK(); //sempre que for criar um classe auxiliar que é um id composto, precisa instacia-la
	
	private Integer quantity;
	private Double price;
	
	public OrderItem() {
	}
	
	public OrderItem(Order order, Product product, Integer quantity, Double price) { //setando o objeto OrderItemPK atraves do construtor do OrderItem, uma vez que a classe OrderItem contem a composição
		
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}
	
	//inserido os gets e set de "Order" e "Product", mesmo não tendo os atributos dessas classes para manupulas a chasse pk é necessario os gets e sets por aqui mesmo(OrderItem)	
	@JsonIgnore //para evitar o loopin de mão dupla no json, foi colocado aqui por que esse metodo que chama o pedido e o pedido chamando o item gerando o looping
	public Order getOrder() {
		return id.getOrder();
	}
	
	public void setOrder(Order order) {
		id.setOrder(order);
	}
	
	
	public Product getProduct() {
		return id.getProduct();
	}
		
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	}		
}
