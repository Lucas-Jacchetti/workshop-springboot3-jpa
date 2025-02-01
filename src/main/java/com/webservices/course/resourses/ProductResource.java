package com.webservices.course.resourses;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webservices.course.entities.Product;
import com.webservices.course.services.ProductServices;


    //!Camada de recursos (resource layer) - controladores Rest!

//*controlador rest que responde no caminho /users
@RestController // Anotação que indica que é um controlador Rest*
@RequestMapping(value = "/products") //
public class ProductResource {

    @Autowired
    private ProductServices service; // Injeção de dependência para o serviço*
    
    @GetMapping // GET  
    public ResponseEntity<List<Product>> findAll(){  //O método findAll é um endpoint para acessar os usuários*//
        
        List<Product> list = service.findAll(); 
        
        return ResponseEntity.ok().body(list); // Retorna a resposta com sucesso*
    }

    @GetMapping(value = "/{id}") // O método findById é um endpoint para acessar um usuário por Id*
    public ResponseEntity<Product> findById(@PathVariable Long id){
        
        Product obj = service.findById(id);
        
        return ResponseEntity.ok().body(obj); // Retorna a resposta com sucesso*
    }
}

