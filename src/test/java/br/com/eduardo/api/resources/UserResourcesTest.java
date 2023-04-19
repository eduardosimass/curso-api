package br.com.eduardo.api.resources;

import br.com.eduardo.api.domain.Users;
import br.com.eduardo.api.domain.dto.UserDTO;
import br.com.eduardo.api.services.UserService;
import br.com.eduardo.api.services.impl.UserServiceImpl;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest

class UserResourcesTest {
    public static final Integer ID = 1;
    public static final String NAME = "valdir";
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "1234";
    public static final String OBJETO_NÃO_ENCONTRADO = "Objeto Não Encontrado";
    private Users users;
    private UserDTO userDTO;
    @InjectMocks
    private UserResources resources;
    @Mock
    private ModelMapper mapper;
    @Mock
    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnSucess() {
        when(service.findById(anyInt())).thenReturn(users);
        when(mapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<UserDTO> response = resources.findById(ID);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PASSWORD, response.getBody().getPassword());

    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        users = new Users(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);

    }
}