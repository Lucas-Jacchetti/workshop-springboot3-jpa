package com.webservices.course.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.webservices.course.entities.User;
import com.webservices.course.repositories.UserRepository;
import com.webservices.course.services.exceptions.DataBaseException;
import com.webservices.course.services.exceptions.ResourceNotFoundException;

@Service
public class UserServices { // Classe de serviço
    
    @Autowired
    private UserRepository repository; // Injeção de dependência

    public List<User> findAll(){ // Método para retornar todos os usuários
        return repository.findAll(); // Retorna todos os usuários
    }

    public User findById(Long Id){ // Método para retornar um usuário por Id
        Optional<User> obj = repository.findById(Id); // Retorna um usuário por Id
        return obj.orElseThrow(() -> new ResourceNotFoundException(Id));// Retorna o usuário
    }

    public User insert(User obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        try{
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException(id);
            
        }repository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataBaseException(e.getMessage());
        }
        
    }

    public User update(Long id, User obj){
        User entity = repository.getReferenceById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    public void updateData(User entity, User obj){
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
    
}
