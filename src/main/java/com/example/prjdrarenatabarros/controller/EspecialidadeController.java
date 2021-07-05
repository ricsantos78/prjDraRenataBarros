package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.domain.repository.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EspecialidadeController {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @GetMapping(value = "/cadastro-especialidade")
    public ModelAndView cadastroEspecialidade(){
        ModelAndView andView = new ModelAndView("cadastro/cadastro-especialidade");
        andView.addObject("especialidadeobj", new Paciente());
        return andView;
    }

    @PostMapping(value = "**/salvar-especialidade")
    public ModelAndView salvar(Especialidade especialidade){
        especialidadeRepository.save(especialidade);
        ModelAndView andView = new ModelAndView("cadastro/cadastro-especialidade");
        andView.addObject("especialidadeobj", new Especialidade());
        return andView;
    }

    @PostMapping(value = "**/salvar-especialidade-editado")
    public ModelAndView salvarEdit(Especialidade especialidade){
        especialidadeRepository.save(especialidade);
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-especialidade");
        Iterable<Especialidade> especialidadeIt = especialidadeRepository.findAll();
        andView.addObject("especialidades", especialidadeIt);
        return andView;
    }

    @GetMapping(value = "/gerenciamento-especialidade")
    public ModelAndView especialidade(){
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-especialidade");
        Iterable<Especialidade> especialidadeIt = especialidadeRepository.findAll();
        andView.addObject("especialidades", especialidadeIt);
        return andView;
    }


    @GetMapping(value = "/excluir-especialidade/{idEspecialidade}")
    public ModelAndView excluirEspecialidade(@PathVariable("idEspecialidade")Long idEspecialidade){
        especialidadeRepository.deleteById(idEspecialidade);
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-especialidade");
        andView.addObject("especialidades", especialidadeRepository.findAll());
        return andView;
    }

    @PostMapping("**/pesquisar-especialidade")
    public ModelAndView pesquisar(@RequestParam("especialidadePesquisar") String especialidadePesquisar){
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-especialidade");
        andView.addObject("especialidades",especialidadeRepository.findEspecialidadeByName(especialidadePesquisar));
        return andView;
    }
}
