package com.webservices.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.webservices.course.entities.enums.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne //muitos pedidos para um usuario
    @JoinColumn(name = "client_id") //nome da chave estrangeira 
    private User client; //associacao com a classe User

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


}
