package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.Enum.SexoPaciente;
import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.domain.repository.PacienteRepository;
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
    private PacienteRepository pacienteRepository;

    @GetMapping(value = "/cadastro-paciente")
    public ModelAndView cadastroPaciente(){
        ModelAndView andView = new ModelAndView("cadastro/cadastro-paciente");
        andView.addObject("pacienteobj", new Paciente());
        andView.addObject("sexoTypes", SexoPaciente.values());
        return andView;
    }

    @PostMapping(value = "**/salvar-paciente")
    public ModelAndView salvar(Paciente paciente){
        pacienteRepository.save(paciente);
        ModelAndView andView = new ModelAndView("cadastro/cadastro-paciente");
        andView.addObject("pacienteobj", new Paciente());
        andView.addObject("sexoTypes", SexoPaciente.values());
        return andView;
    }

    @PostMapping(value = "**/salvar-paciente-editado")
    public ModelAndView salvarEdit(Paciente paciente){
        pacienteRepository.save(paciente);
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-paciente");
        Iterable<Paciente> pacientesIt = pacienteRepository.findAll();
        andView.addObject("pacientes", pacientesIt);
        return andView;
    }

    @GetMapping(value = "/gerenciamento-paciente")
    public ModelAndView paciente(){
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-paciente");
        Iterable<Paciente> pacientesIt = pacienteRepository.findAll();
        andView.addObject("pacientes", pacientesIt);
        return andView;
    }

    @GetMapping(value = "/editar-paciente/{idPaciente}")
    public ModelAndView editarPaciente(@PathVariable("idPaciente") Long idPaciente){
        ModelAndView andView = new ModelAndView("editar/editar-paciente");
        Optional<Paciente>paciente = pacienteRepository.findById(idPaciente);
        andView.addObject("pacienteobj", paciente.get());
        andView.addObject("sexoTypes", SexoPaciente.values());
        return andView;
    }

    @GetMapping(value = "/excluir-paciente/{idPaciente}")
    public ModelAndView excluirPaciente(@PathVariable("idPaciente")Long idPaciente){
        pacienteRepository.deleteById(idPaciente);
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-paciente");
        andView.addObject("pacientes", pacienteRepository.findAll());

        return andView;
    }

    @PostMapping("**/pesquisar-paciente")
    public ModelAndView pesquisar(@RequestParam("pacientePesquisar") String pacientePesquisar){
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-paciente");
        andView.addObject("pacientes",pacienteRepository.findPacienteByName(pacientePesquisar));
        return andView;
    }

}
