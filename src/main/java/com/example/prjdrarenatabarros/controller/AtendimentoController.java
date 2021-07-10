package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.domain.repository.AtendimentoRepository;
import com.example.prjdrarenatabarros.domain.repository.EspecialidadeRepository;
import com.example.prjdrarenatabarros.domain.repository.PacienteRepository;
import com.example.prjdrarenatabarros.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.List;
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
        ModelAndView andView = new ModelAndView("servicos/atendimento");
        Optional<Paciente> paciente = pacienteRepository.findById(idPaciente);
        andView.addObject("pacienteobj", paciente.get());
        andView.addObject("especialidades", especialidadeRepository.findAll());
        return andView;
    }

    @RequestMapping(value = "**/usuariosPorEspecialidade", method = RequestMethod.GET)
    public @ResponseBody
    List<Usuario> usuarioPorEspecialidade(
            @RequestParam(value = "especialidadeId", required = true) Long especialidadeId) {
        Optional<Especialidade> especialidade = especialidadeRepository.findById(especialidadeId);
        return usuarioRepository.findUsuarioByEspecialidade(especialidade);
    }
}
