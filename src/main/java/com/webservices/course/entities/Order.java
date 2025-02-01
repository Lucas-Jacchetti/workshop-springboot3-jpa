package com.webservices.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

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
    private Instant moment;

    @ManyToOne //muitos pedidos para um usuario
    @JoinColumn(name = "client_id") //nome da chave estrangeira 
    private User client; //associacao com a classe User

    public Order() {  

    }

    public Order(Long id, Instant moment, User cient) {
        this.id = id;
        this.moment = moment;
        this.client = cient;
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
