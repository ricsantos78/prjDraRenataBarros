package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.services.PacienteService;
import com.example.prjdrarenatabarros.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/usuario")
public class UsuarioRestController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PacienteService pacienteService;

    @RequestMapping(value = "find/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> find(@PathVariable("id") Long id){
        try{
            return new ResponseEntity<Usuario>(usuarioService.find(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "findPaciente/{id}", method = RequestMethod.GET, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public ResponseEntity<Paciente> findPaciente(@PathVariable("id") Long id){
        try{
            return new ResponseEntity<Paciente>(pacienteService.find(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Paciente>(HttpStatus.BAD_REQUEST);
        }
    }
}
