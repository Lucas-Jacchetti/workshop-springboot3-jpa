package com.webservices.course.config;

import java.time.Instant;
import java.util.Arrays;
import com.webservices.course.entities.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.webservices.course.entities.Order;
import com.webservices.course.entities.Product;
import com.webservices.course.entities.User;
import com.webservices.course.entities.enums.OrderStatus;
import com.webservices.course.repositories.CategoryRepository;
import com.webservices.course.repositories.OrderRepository;
import com.webservices.course.repositories.ProductRepository;
import com.webservices.course.repositories.UserRepository;

//*Definir bins e instancias de classe no spring */
//controlar classes que nao sao originarias do spring

@Configuration //especificar que é uma classe de configuração para o spring
@Profile("test") //especificar o perfil
public class TestConfig implements CommandLineRunner{

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired   //injeção de dependência
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        
    
        

        Category c1 = new Category(null, "Electronics");
        Category c2 = new Category(null, "Books");

        Product p1 = new Product(null, "McLaren Lego", "Versão 2024", 330.5, "");
        Product p2 = new Product(null, "Lego Star Wars", "Versão 2023", 930.5, "");

        categoryRepository.saveAll(Arrays.asList(c1, c2));
        productRepository.saveAll(Arrays.asList(p1, p2));
        
        User u1 = new User(null, "Maria", "maria@gmail.com", "97777777777", "12344568");
        User u2 = new User(null, "Carlos", "carlos@gmail.com", "99999999999", "12344568");

        Order o1 = new Order(null, Instant.parse("2025-01-31T22:30:40Z"), OrderStatus.WAITING_PAYMENT, u1);
        Order o2 = new Order(null, Instant.parse("2025-02-23T12:51:12Z"), OrderStatus.PAID, u2);
        Order o3 = new Order(null, Instant.parse("2025-03-02T12:14:43Z"), OrderStatus.CANCELED, u1);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }


}
