package com.example.jpadto.infraestructure.controller;

import com.example.jpadto.domain.Usuario;
import com.example.jpadto.exceptions.BeanNotFoundException;
import com.example.jpadto.infraestructure.dto.DTOusuario;
import com.example.jpadto.infraestructure.repository.UsuarioServicioInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/Put")
public class PutUsuarioControlador {

    @Autowired
    private UsuarioServicioInterface usuarioServicio;
    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("actualizar")
    public ResponseEntity<DTOusuario> actualiza(@RequestBody DTOusuario usuario) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServicio.actualiza(usuario));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
