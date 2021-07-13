package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.Enum.SexoPaciente;
import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping(value = "/cadastro-paciente")
    public ModelAndView cadastroPaciente(){
        ModelAndView andView = new ModelAndView("cadastro/cadastro-paciente");
        andView.addObject("pacienteobj", new Paciente());
        andView.addObject("sexoTypes", SexoPaciente.values());
        return andView;
    }

    @PostMapping(value = "**/salvar-paciente")
    public ModelAndView salvar(Paciente paciente){
        pacienteService.save(paciente);
        ModelAndView andView = new ModelAndView("cadastro/cadastro-paciente");
        andView.addObject("pacienteobj", new Paciente());
        andView.addObject("sexoTypes", SexoPaciente.values());
        return andView;
    }

    @PostMapping(value = "**/salvar-paciente-editado")
    public ModelAndView salvarEdit(Paciente paciente){
        pacienteService.save(paciente);
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-paciente");
        Iterable<Paciente> pacientesIt = pacienteService.findAll();
        andView.addObject("pacientes", pacientesIt);
        return andView;
    }

    @GetMapping(value = "/gerenciamento-paciente")
    public ModelAndView paciente(){
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-paciente");
        Iterable<Paciente> pacientesIt = pacienteService.findAll();
        andView.addObject("pacientes", pacientesIt);
        return andView;
    }

    @GetMapping(value = "/editar-paciente/{idPaciente}")
    public ModelAndView editarPaciente(@PathVariable("idPaciente") Long idPaciente){
        ModelAndView andView = new ModelAndView("editar/editar-paciente");
        Optional<Paciente>paciente = Optional.ofNullable(pacienteService.find(idPaciente));
        andView.addObject("pacienteobj", paciente.get());
        andView.addObject("sexoTypes", SexoPaciente.values());
        return andView;
    }

    @GetMapping(value = "/excluir-paciente/{idPaciente}")
    public ModelAndView excluirPaciente(@PathVariable("idPaciente")Long idPaciente){
        pacienteService.delete(idPaciente);
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-paciente");
        andView.addObject("pacientes", pacienteService.findAll());

        return andView;
    }

    @PostMapping("**/pesquisar-paciente")
    public ModelAndView pesquisar(@RequestParam("pacientePesquisar") String pacientePesquisar){
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-paciente");
        andView.addObject("pacientes",pacienteService.findPacienteByName(pacientePesquisar));
        return andView;
    }

}
