package com.webservices.course.resourses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.webservices.course.entities.User;


    //!Camada de recursos (resource layer) - controladores Rest!

//*controlador rest que responde no caminho /users
@RestController
@RequestMapping(value = "/users")       
public class UserResource {
    
    @GetMapping
    public ResponseEntity <User> findAll(){  //*O método findAll é um endpoint para acessar os usuários*//
        User u = new User (1L, "Maria", "maria@gmail.com", "99999999", "12345");
        return ResponseEntity.ok().body(u);
    }
}

