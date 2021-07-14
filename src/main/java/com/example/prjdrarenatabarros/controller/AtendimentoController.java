package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.entity.Atendimento;
import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.domain.repository.AtendimentoRepository;
import com.example.prjdrarenatabarros.services.AtendimentoService;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import com.example.prjdrarenatabarros.services.PacienteService;
import com.example.prjdrarenatabarros.services.UsuarioService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class AtendimentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private EspecialidadeService especialidadeService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/atendimento/{idPaciente}")
    public ModelAndView editarPaciente(@PathVariable("idPaciente") Long idPaciente){
        ModelAndView andView = new ModelAndView("servicos/atendimento");
        Optional<Paciente> paciente = Optional.ofNullable(pacienteService.find(idPaciente));
        andView.addObject("pacienteobj", paciente.get());
        andView.addObject("especialidades", especialidadeService.findAll());
        return andView;
    }

    @ResponseBody
    @RequestMapping(value = "**/usuariosPorEspecialidade/{id}", method = RequestMethod.GET)
    public String usuariosPorEspecialidade(@PathVariable("id") Long id) {
        Gson gson = new Gson();
        return gson.toJson(usuarioService.findUsuarioByEspecialidade(id));
    }

    @PostMapping(value = "**/salvar-atendimento/{idPaciente}")
    public ModelAndView salvar(Atendimento atedimento,
                               @PathVariable("idPaciente") Long id){



        Paciente paciente = pacienteService.find(id);
        atedimento.setPaciente(paciente);

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dataFormatada = formatter.format(localDate);
        atedimento.setDataPedido(dataFormatada);

//        Especialidade especialidade = especialidadeService.find(idEspecialidade);
//        atedimento.setEspecialidade(especialidade);

        atendimentoService.save(atedimento);
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-paciente");
        return andView;
    }
}
