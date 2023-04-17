package br.com.eduardo.api.services;

import br.com.eduardo.api.domain.Users;
import br.com.eduardo.api.domain.dto.UserDTO;
import jakarta.persistence.Id;

import java.util.List;

public interface UserService {
    Users findById(Integer id);
    List<Users>  findAll();
    Users create (UserDTO obj);
    Users update (UserDTO obj );
}
