package com.educandoweb.couse.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.couse.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") //annotation para formatar o json, arg02 define o formato
	private Instant moment;
		
	private Integer orderStatus; //foi colocar o orderStatus como integer ao invez de OrderStatus para explicitar ao banco que é um numero inteiro, é um tratamento expecifico da classe Order e somente no atributo, o contrutor continuara recebendo um tidpo OrderStatus
	
	//annotation para chave strangeria muitos para um, um pedido para ter um cliente e um cliente pode ter muitos pedidos
	@ManyToOne
	@JoinColumn(name= "client_id") //nome da chave estrangera que será criada no banco
	private User client;
	
	@OneToMany(mappedBy = "id.order") //no orderItem tem o "id" do order(pedido) e nesse id
	private Set<OrderItem> items = new HashSet<>();
	
	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL) //mapeando Payment e Order para ter o mesmo id,.
	private Payment payment;
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	
	//tratamento especial do OrderStatus utilizando o metodo feito para convesão de int para OrderStatus
	public OrderStatus getOrderStatus() {
		return OrderStatus.vaueOf(orderStatus);
	}
	
	//tratamento especial do OrderStatus para atribuir ao atributo da classo o orderStatus recebido convertido para o codigo dele.
	public void setOrderStatus(OrderStatus orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	public Set<OrderItem> getItems(){
		return items;
	}
	
	public Double getTotal() {
		double sum = 0.0;
		for(OrderItem x : items) {
			sum +=x.getSubTotal();
		}
		return sum;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}	
}
