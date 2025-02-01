package com.webservices.course.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webservices.course.entities.Category;
import com.webservices.course.repositories.CategoryRepository;

@Service
public class CategoryServices { // Classe de serviço
    
    @Autowired
    private CategoryRepository repository; // Injeção de dependência

    public List<Category> findAll(){ 
        return repository.findAll();
    }

    public Category findById(Long Id){ 
        Optional<Category> obj = repository.findById(Id); 
        return obj.get(); 
    }
}
