package com.webservices.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webservices.course.entities.enums.OrderStatus;

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
@Table(name = "tb_order") //renomeacao da tabela do banco de dados para nao entrar em conflito com palavras reservadas  
public class Order implements Serializable { //serializable para trafegar na rede, gravar em arquivo, etc
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incremento
    private Long id;   //atribuicoes basicas 

    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") //formato de data ISO8601
    private Instant moment;

    private Integer orderStatus;

    @OneToOne (mappedBy = "order", cascade = CascadeType.ALL) //mapeamento para os dois terem o mesmo id
    private Payment payment;

    @ManyToOne //muitos pedidos para um usuario
    @JoinColumn(name = "client_id") //nome da chave estrangeira 
    private User client; //associacao com a classe User

    @OneToMany(mappedBy = "id.order") //um pedido para muitos itens
    private Set<OrderItem> items = new HashSet<>(); //set para garantir que nao vai ter item repetido no mesmo pedido

    public Set<OrderItem> getItems() {
        return this.items;
    }
    
    public Order() {  

    }

    public Order(Long id, Instant moment, OrderStatus orderStatus, User cient) {
        this.id = id;
        this.moment = moment;
        setOrderStauts(orderStatus);
        this.client = cient;
    }

    public OrderStatus getOrderStauts() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStauts(OrderStatus orderStatus) {
        if(orderStatus != null){
            this.orderStatus = orderStatus.getCode();
        }
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return this.moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public User getCient() {
        return this.client;
    }

    public void setCient(User cient) {
        this.client = cient;
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Double getTotal(){
        double sum = 0.0;
        for (OrderItem x : items) {
            sum += x.getSubTotal(); 
        }
        return sum;
    }

}
