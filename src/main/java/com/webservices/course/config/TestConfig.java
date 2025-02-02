package com.webservices.course.config;

import java.time.Instant;
import java.util.Arrays;
import com.webservices.course.entities.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.webservices.course.entities.Order;
import com.webservices.course.entities.OrderItem;
import com.webservices.course.entities.Payment;
import com.webservices.course.entities.Product;
import com.webservices.course.entities.User;
import com.webservices.course.entities.enums.OrderStatus;
import com.webservices.course.repositories.CategoryRepository;
import com.webservices.course.repositories.OrderItemRepository;
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

    @Autowired
    private OrderItemRepository orderItemRepository;



    @Override
    public void run(String... args) throws Exception {
        
    
        

        Category c1 = new Category(null, "Electronics");
        Category c2 = new Category(null, "Books");

        Product p1 = new Product(null, "McLaren Lego", "Versão 2024", 330.5, "");
        Product p2 = new Product(null, "Lego Star Wars", "Versão 2023", 930.5, "");

        User u1 = new User(null, "Maria", "maria@gmail.com", "97777777777", "12344568");
        User u2 = new User(null, "Carlos", "carlos@gmail.com", "99999999999", "12344568");

        Order o1 = new Order(null, Instant.parse("2025-01-31T22:30:40Z"), OrderStatus.WAITING_PAYMENT, u1);
        Order o2 = new Order(null, Instant.parse("2025-02-23T12:51:12Z"), OrderStatus.PAID, u2);
        Order o3 = new Order(null, Instant.parse("2025-03-02T12:14:43Z"), OrderStatus.CANCELED, u1);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        categoryRepository.saveAll(Arrays.asList(c1, c2));
        productRepository.saveAll(Arrays.asList(p1, p2));

        p1.getCategories().add(c2); //adicionando a categoria ao produto
        p2.getCategories().add(c1);

        productRepository.saveAll(Arrays.asList(p1, p2));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p2, 1, p2.getPrice());
        OrderItem oi3 = new OrderItem(o2, p1, 3, p1.getPrice());
        
        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3));
    
        Payment pay1 = new Payment(null, Instant.parse("2025-01-31T22:30:40Z"), o2);
        
        o2.setPayment(pay1);

        orderRepository.save(o2);
    
    }



}
