package com.webservices.course.resourses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webservices.course.entities.User;
import com.webservices.course.services.UserServices;


    //!Camada de recursos (resource layer) - controladores Rest!

//*controlador rest que responde no caminho /users
@RestController
@RequestMapping(value = "/users")       
public class UserResource {

    @Autowired
    private UserServices service; // Injeção de dependência para o serviço*
    
    @GetMapping
    public ResponseEntity <List<User>> findAll(){  //O método findAll é um endpoint para acessar os usuários*//
        
        List<User> list = service.findAll(); // O método findAll é um endpoint para acessar os usuários*
        
        return ResponseEntity.ok().body(list); // Retorna a resposta com sucesso*
    }

    @GetMapping(value = "/{id}") // O método findById é um endpoint para acessar um usuário por Id*
    public ResponseEntity <User> findById(@PathVariable Long id){
        
        User obj = service.findById(id);
        
        return ResponseEntity.ok().body(obj); // Retorna a resposta com sucesso*
    }
}

