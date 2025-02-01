package com.webservices.course.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservices.course.entities.Order;
import com.webservices.course.entities.User;
import com.webservices.course.repositories.OrderRepository;
import com.webservices.course.repositories.UserRepository;

@SuppressWarnings("unused")
@Service
public class OrderServices { // Classe de serviço
    
    @Autowired
    private OrderRepository repository; // Injeção de dependência

    public List<Order> findAll(){ // Método para retornar todos os usuários
        return repository.findAll(); // Retorna todos os usuários
    }

    public Order findById(Long Id){ // Método para retornar um usuário por Id
        Optional<Order> obj = repository.findById(Id); // Retorna um usuário por Id
        return obj.get(); // Retorna o usuário
    }
}
