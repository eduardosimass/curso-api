package br.com.eduardo.api.resources.exceptions;

import br.com.eduardo.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResrouceExceptionsHendlerTest {
    @InjectMocks
    private ResrouceExceptionsHendler exceptionHandler;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void WhenObjectNotFound() {
        ResponseEntity<StandartError> response = exceptionHandler
                .ObjectNotFound(
                        new ObjectNotFoundException("Objeto não encontrado"),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandartError.class, response.getBody().getClass());
        assertEquals("Objeto não encontrado", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void dataIntegratyViolationException() {
    }
}