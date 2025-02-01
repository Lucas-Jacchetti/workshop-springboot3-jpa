package com.webservices.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.webservices.course.entities.User;
import com.webservices.course.repositories.UserRepository;

//*Definir bins e instancias de classe no spring */
//controlar classes que nao sao originarias do spring

@Configuration //especificar que é uma classe de configuração para o spring
@Profile("test") //especificar o perfil
public class TestConfig implements CommandLineRunner{
    
    @Autowired   //injeção de dependência
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria", "maria@gmail.com", "97777777777", "12344568");
        User u2 = new User(null, "Carlos", "carlos@gmail.com", "99999999999", "12344568");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }


}
