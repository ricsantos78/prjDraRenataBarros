package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.services.PacienteService;
import com.example.prjdrarenatabarros.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "api/usuario")
public class UsuarioRestController {

    private final UsuarioService usuarioService;

    private final PacienteService pacienteService;

    @GetMapping(value = "find/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> find(@PathVariable("id") UUID id){
        try{
            return new ResponseEntity<>(usuarioService.find(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "findPaciente/{id}", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> findPaciente(@PathVariable("id") UUID id){
        try{
            return new ResponseEntity<>(pacienteService.find(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
