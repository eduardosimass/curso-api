package br.com.eduardo.api.services;

import br.com.eduardo.api.domain.Users;

import java.util.List;

public interface UserService {
    Users findById(Integer id);
    List<Users>  findAll();
}
