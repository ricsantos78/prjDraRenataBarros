package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class EspecialidadeController {

    private final EspecialidadeService especialidadeService;

    @GetMapping(value = "/cadastro-especialidade")
    public ModelAndView cadastroEspecialidade(){
        ModelAndView andView = new ModelAndView("cadastro/cadastro-especialidade");
        andView.addObject("especialidadeobj", new Paciente());
        return andView;
    }

    @PostMapping(value = "**/salvar-especialidade")
    public ModelAndView salvar(Especialidade especialidade){
        especialidadeService.save(especialidade);
        ModelAndView andView = new ModelAndView("cadastro/cadastro-especialidade");
        andView.addObject("especialidadeobj", new Especialidade());
        return andView;
    }

    @GetMapping(value = "/gerenciamento-especialidade")
    public ModelAndView especialidade(){
        ModelAndView andView = new ModelAndView(getViewGerenciamento());
        Iterable<Especialidade> especialidadeIt = especialidadeService.findAll();
        andView.addObject(getEspecialidades(), especialidadeIt);
        return andView;
    }




    @GetMapping(value = "/excluir-especialidade/{idEspecialidade}")
    public ModelAndView excluirEspecialidade(@PathVariable("idEspecialidade") UUID idEspecialidade){
        especialidadeService.delete(idEspecialidade);
        ModelAndView andView = new ModelAndView(getViewGerenciamento());
        andView.addObject(getEspecialidades(), especialidadeService.findAll());
        return andView;
    }

    @PostMapping("**/pesquisar-especialidade")
    public ModelAndView pesquisar(@RequestParam("especialidadePesquisar") String especialidadePesquisar){
        ModelAndView andView = new ModelAndView(getViewGerenciamento());
        andView.addObject(getEspecialidades(),especialidadeService.findEspecialidadeByNome(especialidadePesquisar));
        return andView;
    }

    @Contract(pure = true)
    private @NotNull String getViewGerenciamento() {
        return "gerenciamento/gerenciamento-specialised";
    }

    @Contract(pure = true)
    private @NotNull String getEspecialidades() {
        return "especialidades";
    }
}
