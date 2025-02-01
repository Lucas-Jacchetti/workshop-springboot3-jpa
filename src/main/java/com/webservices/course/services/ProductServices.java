package com.webservices.course.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservices.course.entities.Product;
import com.webservices.course.repositories.ProductRepository;

@Service
public class ProductServices { // Classe de serviço
    
    @Autowired
    private ProductRepository repository; // Injeção de dependência

    public List<Product> findAll(){ 
        return repository.findAll();
    }

    public Product findById(Long Id){ 
        Optional<Product> obj = repository.findById(Id); 
        return obj.get(); 
    }
}
