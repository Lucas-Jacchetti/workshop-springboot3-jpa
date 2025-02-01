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
import com.webservices.course.entities.Order;
import com.webservices.course.services.OrderServices;




    //!Camada de recursos (resource layer) - controladores Rest!

//*controlador rest que responde no caminho /users
@SuppressWarnings("unused")
@RestController // Anotação que indica que é um controlador Rest*
@RequestMapping(value = "/orders") //
public class OrderResource {
    //put, get, post, delete, patch, options, head (metodos http)

    @Autowired
    private OrderServices service; // Injeção de dependência para o serviço*
    
    @GetMapping // GET "/users" 
    public ResponseEntity <List<Order>> findAll(){  //O método findAll é um endpoint para acessar os usuários*//
        
        List<Order> list = service.findAll(); 
        
        return ResponseEntity.ok().body(list); // Retorna a resposta com sucesso*
    }

    @GetMapping(value = "/{id}") // O método findById é um endpoint para acessar um usuário por Id*
    public ResponseEntity <Order> findById(@PathVariable Long id){
        
        Order obj = service.findById(id);
        
        return ResponseEntity.ok().body(obj); // Retorna a resposta com sucesso*
    }
}

