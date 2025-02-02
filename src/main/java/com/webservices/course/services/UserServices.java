package com.webservices.course.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.webservices.course.entities.User;
import com.webservices.course.repositories.UserRepository;

@Service
public class UserServices { // Classe de serviço
    
    @Autowired
    private UserRepository repository; // Injeção de dependência

    public List<User> findAll(){ // Método para retornar todos os usuários
        return repository.findAll(); // Retorna todos os usuários
    }

    public User findById(Long Id){ // Método para retornar um usuário por Id
        Optional<User> obj = repository.findById(Id); // Retorna um usuário por Id
        return obj.get(); // Retorna o usuário
    }

    public User insert(User obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
    
}
