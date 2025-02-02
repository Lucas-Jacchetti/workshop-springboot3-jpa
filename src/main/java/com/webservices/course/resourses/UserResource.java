package com.webservices.course.resourses;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.webservices.course.entities.User;
import com.webservices.course.services.UserServices;


    //!Camada de recursos (resource layer) - controladores Rest!

//*controlador rest que responde no caminho /users
@RestController // Anotação que indica que é um controlador Rest*
@RequestMapping(value = "/users") //
public class UserResource {
    //put, get, post, delete, patch, options, head (metodos http)

    @Autowired
    private UserServices service; // Injeção de dependência para o serviço*
    
    @GetMapping // GET "/users" 
    public ResponseEntity <List<User>> findAll(){  //O método findAll é um endpoint para acessar os usuários*//
        
        List<User> list = service.findAll(); 
        
        return ResponseEntity.ok().body(list); // Retorna a resposta com sucesso*
    }

    @GetMapping(value = "/{id}") // O método findById é um endpoint para acessar um usuário por Id*
    public ResponseEntity <User> findById(@PathVariable Long id){
        
        User obj = service.findById(id);
        
        return ResponseEntity.ok().body(obj); // Retorna a resposta com sucesso*
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id ){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}

