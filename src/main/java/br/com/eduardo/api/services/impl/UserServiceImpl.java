package br.com.eduardo.api.services.impl;

import br.com.eduardo.api.domain.Users;
import br.com.eduardo.api.domain.dto.UserDTO;
import br.com.eduardo.api.repository.UserRepository;
import br.com.eduardo.api.services.UserService;
import br.com.eduardo.api.services.exceptions.DataIntegratyViolationException;
import br.com.eduardo.api.services.exceptions.ObjectNotFoundException;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public Users findById(Integer id) {
        Optional<Users> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
  public List<Users> findAll(){
        return repository.findAll();
  }

    @Override
    public Users create(UserDTO obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Users.class));
    }

    @Override
    public Users update(UserDTO obj) {
        findByEmail(obj);
        return repository.save((mapper.map(obj, Users.class)));
    }

  @Override
  public void delete(Integer id){
        findById(id);
        repository.deleteById(id);
  }

    private void findByEmail(UserDTO obj ){
        Optional<Users> user = repository.findByEmail(obj.getEmail());
        if(user.isPresent() && !user.get().getId().equals(obj.getEmail())){
            throw new DataIntegratyViolationException("Email já cadastrado no sistema!");

        }
    }
}
