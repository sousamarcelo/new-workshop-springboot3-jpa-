package com.educandoweb.couse.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name= "tb_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	@JsonIgnore // para evitar conflito e looping de duas vias, porque nas duas classes cliente e pedido tem a annotaction de "um para muitos" e "muitos para um" isso gera um looping
	//annotation para chaves estrangerias, um usuario pode ter muitos pedidos, mas o pedido só pode ter 1 usuario.
	@OneToMany(mappedBy= "client") //dentro do parenteses deve-se informar o nome do atributo que está do outro lado da associação
	private List<Order> orders = new ArrayList<>(); //@JsonIgnore --> quando existe uma associação "para muitos" que é esse caso, um cliente pode ter muitos pedidos, o jpa não carrega os objetos no json(lazy loading), ja ao contrario carrega, ou seja quando existe um pedido para um cliente o JPA carrega o pedido e o cliente associado, porque um pedido só pode ter um cliente. caso queira que funcione ao contrario basta alterar o "@JsonIgnore" para o pedido, nesse caso no jaso carregara os usuarios e todos os pedidos associados a esse usuario.
	
	public User() {
	}

	public User(Long id, String name, String email,String phone, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Order> getOrders(){
		return orders;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
