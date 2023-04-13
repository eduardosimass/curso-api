package br.com.eduardo.api.services;

import br.com.eduardo.api.domain.Users;

public interface UserService {
    Users findById(Integer id);

}
