package com.webservices.course.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_user") //renomeação da tabela do banco de dados para nao entrar em conflito com palavras reservadas
public class User implements Serializable{ //trafego na rede, gravar em arquivo, etc

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incremento
    private Long id;   //atributos básicos
    private String name;
    private String email;
    private String phone;
    private String password;

    //mapeado pelo atributo client da classe Order
    @OneToMany(mappedBy = "client") //um usuario para muitos pedidos    
    private List<Order> orders = new ArrayList<>(); //associação com a classe Order
    
    //*Construtores */
    public User() {
    }

    public User(Long id, String name, String email, String phone, String password) {  //construtor com argumentos
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    //*Getters e setters*//

    public List<Order> getOrders() {
        return this.orders;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //*hashCode e equals */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phone='" + getPhone() + "'" +
            ", password='" + getPassword() + "'" +
            "}";
    }

}
