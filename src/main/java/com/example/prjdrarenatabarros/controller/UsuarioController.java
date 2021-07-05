package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.Enum.CargoColaborador;
import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.domain.repository.EspecialidadeRepository;
import com.example.prjdrarenatabarros.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastro-colaborador")
    public ModelAndView cadastroColaborador(String especialidade){
        ModelAndView andView = new ModelAndView("cadastro/cadastro-colaborador");
        andView.addObject("especialidades", especialidadeRepository.findAll());
        andView.addObject("colaboradorobj", new Usuario());
        andView.addObject("cargoTypes", CargoColaborador.values());
        return andView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "**/salvarColaborador")
    public ModelAndView salvar(Usuario usuario){
        usuarioRepository.save(usuario);
        ModelAndView andView = new ModelAndView("cadastro/cadastro-colaborador");
        andView.addObject("colaboradorobj", new Usuario());
        andView.addObject("cargoTypes", CargoColaborador.values());
        return andView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "gerenciamento-colaborador")
    public ModelAndView usuarios(){
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-colaborador");
        Iterable<Usuario> usuarioIt = usuarioRepository.findAll();
        andView.addObject("usuarios", usuarioIt);
        return andView;
    }

    @GetMapping("/editar-colaborador/{idColaborador}") //recebendo o parametro da url com o idColaborador
    public ModelAndView editarColaborador(@PathVariable("idColaborador")Long idColaborador){ //pegando a vareavel, "idcolaborador" esta pegando na URL e depois passa o tipo de dados igual ao objeto id do usuario
        ModelAndView andView = new ModelAndView("editar/editar-colaborador");//retorna para tela de editar apos o click do botao
        Optional<Usuario> usuario = usuarioRepository.findById(idColaborador);//busca o objeto usuario pelo id
        andView.addObject("colaboradorobj", usuario.get());//passar o objeto da tela
        andView.addObject("cargoTypes", CargoColaborador.values());
        return andView;
    }

    @GetMapping("/excluir-colaborador/{idColaborador}") //recebendo o parametro da url com o idColaborador
    public ModelAndView excluirColaborador(@PathVariable("idColaborador")Long idColaborador){ //pegando a vareavel, "idcolaborador" esta pegando na URL e depois passa o tipo de dados igual ao objeto id do usuario

        usuarioRepository.deleteById(idColaborador);

        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-colaborador");//retorna para tela de editar apos o click do botao

        andView.addObject("usuarios", usuarioRepository.findAll());

        return andView;
    }

    @PostMapping("**/pesquisar-colaborador")
    public ModelAndView pesquisar(@RequestParam("colaboradorPesquisar")String colaboradorPesquisar){

        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-colaborador");//retorna para tela de editar apos o click do botao

        andView.addObject("usuarios", usuarioRepository.findUsuarioByName(colaboradorPesquisar));

        return andView;
    }
}
