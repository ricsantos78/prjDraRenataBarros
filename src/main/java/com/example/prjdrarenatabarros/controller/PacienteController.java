package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.Enum.SexoPaciente;
import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping(value = "/cadastro-paciente")
    public String cadastroPaciente(ModelMap andView){
        andView.addAttribute("pacienteobj", new Paciente());
        andView.addAttribute("sexoTypes", SexoPaciente.values());
        return "cadastro/cadastro-paciente";
    }

    @PostMapping(value = "**/salvar-paciente")
    public String salvar(@Valid Paciente paciente, BindingResult bindingResult, Model andView, RedirectAttributes attr){
        Paciente log = pacienteService.findPacienteByCpf(paciente.getCpf());
        List<String> msg = new ArrayList<>();

        if(log != null){
            andView.addAttribute("pacienteobj", paciente);
            andView.addAttribute("sexoTypes", SexoPaciente.values());
            attr.addFlashAttribute("msgError", "Paciente já existe");
            return "redirect:/cadastro-paciente";
        }
        if (bindingResult.hasErrors()){
            andView.addAttribute("pacienteobj", paciente);
            andView.addAttribute("sexoTypes", SexoPaciente.values());
            for (ObjectError objectError : bindingResult.getAllErrors()){
                msg.add(objectError.getDefaultMessage()); //<--vem  getDefaultMessage vem das anotaçoes
            }
            attr.addFlashAttribute("msgError", msg);
            return "redirect:/cadastro-paciente";
        }

        pacienteService.save(paciente);
        andView.addAttribute("pacienteobj", new Paciente());
        andView.addAttribute("sexoTypes", SexoPaciente.values());
        attr.addFlashAttribute("msgSucess","Paciente cadastrado com sucesso");
        return "redirect:/gerenciamento-paciente";
    }



    @GetMapping(value = "/gerenciamento-paciente")
    public String paciente(ModelMap andView){
        Iterable<Paciente> pacientesIt = pacienteService.findAll();
        andView.addAttribute("pacientes", pacientesIt);
        andView.addAttribute("sexoTypes", SexoPaciente.values());
        return "gerenciamento/gerenciamento-paciente";
    }

    @PostMapping(value = "**/salvar-paciente-editado")
    public ModelAndView salvarEdit(Paciente paciente){
        pacienteService.save(paciente);
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-paciente");
        Iterable<Paciente> pacientesIt = pacienteService.findAll();
        andView.addObject("pacientes", pacientesIt);
        return andView;
    }


    @GetMapping(value = "/excluir-paciente/{idPaciente}")
    public String excluirPaciente(@PathVariable("idPaciente")Long idPaciente, RedirectAttributes attr){
        pacienteService.delete(idPaciente);

        attr.addFlashAttribute("pacientes", pacienteService.findAll());
        attr.addFlashAttribute("msgSucess", "Paciente excluido com sucesso");

        return "redirect:/gerenciamento-paciente";
    }

    @PostMapping("**/pesquisar-paciente")
    public String pesquisar(ModelMap andView,@RequestParam("pacientePesquisar") String pacientePesquisar){
        andView.addAttribute("pacientes",pacienteService.findPacienteByName(pacientePesquisar));
        return "gerenciamento/gerenciamento-paciente";
    }

}
