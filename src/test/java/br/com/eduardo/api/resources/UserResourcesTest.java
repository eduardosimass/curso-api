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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest

class UserResourcesTest {
    public static final Integer ID = 1;
    public static final String NAME = "valdir";
    public static final String EMAIL = "email@email.com";
    public static final String PASSWORD = "1234";
    public static final String OBJETO_NÃO_ENCONTRADO = "Objeto Não Encontrado";
    public static final int INDEX = 0;
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
    void WhenfindAllThenReturnListOfUsersDTO() {
        when(service.findAll()).thenReturn(List.of(users));
        when(mapper.map(any(), any())).thenReturn(userDTO);

        ResponseEntity<List<UserDTO>> response = resources.findAll();
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PASSWORD, response.getBody().get(INDEX).getPassword());

    }

    @Test
    void WhenCreateThenReturnCreated() {
        when(service.create(any())).thenReturn(users);
        ResponseEntity<UserDTO> response = resources.create(userDTO);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getHeaders().get("Location"));
    }

    @Test
    void WhenUpdateThenReturnSucess() {
        when(service.update(userDTO)).thenReturn(users);
        when(mapper.map(any(), any())).thenReturn(userDTO);
        ResponseEntity<UserDTO> response = resources.update(ID, userDTO);
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());
        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());


    }

    @Test
    void WhenDeleteThenReturnSucess() {
        doNothing().when(service).delete(anyInt());
        ResponseEntity<UserDTO> response = resources.delete(ID);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(service, times(1)).delete(anyInt());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    }

    private void startUser(){
        users = new Users(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);

    }
}