package br.com.eduardo.api.services.impl;

import br.com.eduardo.api.domain.Users;
import br.com.eduardo.api.repository.UserRepository;
import br.com.eduardo.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;
    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElse(null);
    }
}