package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.entity.Atendimento;
import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.services.AtendimentoService;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import com.example.prjdrarenatabarros.services.PacienteService;
import com.example.prjdrarenatabarros.services.UsuarioService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    private final PacienteService pacienteService;

    private final EspecialidadeService especialidadeService;

    private final UsuarioService usuarioService;

    @GetMapping(value = "/atendimento/{idPaciente}")
    public ModelAndView editarPaciente(@PathVariable("idPaciente") UUID idPaciente) {
        ModelAndView andView = new ModelAndView("servicos/atendimento");
        Optional<Paciente> paciente = Optional.ofNullable(pacienteService.find(idPaciente));
        andView.addObject("pacienteobj", paciente.orElseGet(Paciente::new));
        andView.addObject("especialidades", especialidadeService.findAll());
        return andView;
    }

    @ResponseBody
    @GetMapping(value = "**/usuariosPorEspecialidade/{id}")
    public String usuariosPorEspecialidade(@PathVariable("id") UUID id) {
        Gson gson = new Gson();
        return gson.toJson(usuarioService.findUsuarioByEspecialidadeId(id));
    }

    @PostMapping(value = "**/salvar-atendimento/{idPaciente}")
    public ModelAndView salvar(Atendimento atedimento,
                               @PathVariable("idPaciente") UUID id) {


        Paciente paciente = pacienteService.find(id);
        atedimento.setPaciente(paciente);

        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        String dataFormatada = formatter.format(localDate);
        atedimento.setDataPedido(dataFormatada);

        atendimentoService.save(atedimento);
        return new ModelAndView("gerenciamento/gerenciamento-paciente");
    }
}
