package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.Enum.SexoPaciente;
import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.domain.repository.AtendimentoRepository;
import com.example.prjdrarenatabarros.domain.repository.EspecialidadeRepository;
import com.example.prjdrarenatabarros.domain.repository.PacienteRepository;
import com.example.prjdrarenatabarros.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class AtendimentoController {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping(value = "/atendimento/{idPaciente}")
    public ModelAndView editarPaciente(@PathVariable("idPaciente") Long idPaciente){
        ModelAndView andView = new ModelAndView("servico/atendimento");
        Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
        andView.addObject("pacienteobj", paciente.get());
        andView.addObject("especialidades", especialidadeRepository.findAll());
        andView.addObject("usuarios", usuarioRepository.findAll());
        return andView;
    }
}
