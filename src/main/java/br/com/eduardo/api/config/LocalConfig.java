package br.com.eduardo.api.config;


import br.com.eduardo.api.domain.Users;
import br.com.eduardo.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {
    @Autowired
    private UserRepository repository;
    @Bean
    public void startDb(){
        Users u1 = new Users(null,"Valdir", "validr@valdir.com.br" ,"123" );
        Users u2 = new Users(null,"Eduardo", "eduardo@eduardo.com.br" ,"123" );
        repository.saveAll(List.of(u1, u2));
    }

}
