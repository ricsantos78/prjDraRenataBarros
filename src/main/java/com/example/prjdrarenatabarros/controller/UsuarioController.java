package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.Enum.CargoUsuario;
import com.example.prjdrarenatabarros.domain.entity.Paciente;
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

    @RequestMapping(method = RequestMethod.GET, value = "/cadastro-usuario")
    public ModelAndView cadastrousuario(){
        ModelAndView andView = new ModelAndView("cadastro/cadastro-usuario");
        andView.addObject("especialidades", especialidadeRepository.findAll());
        andView.addObject("usuarioObj", new Usuario());
        andView.addObject("cargoTypes", CargoUsuario.values());
        return andView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "**/salvarUsuario")
    public ModelAndView salvar(Usuario usuario){
        usuarioRepository.save(usuario);
        ModelAndView andView = new ModelAndView("cadastro/cadastro-usuario");
        andView.addObject("usuarioObj", new Usuario());
        andView.addObject("cargoTypes", CargoUsuario.values());
        return andView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "gerenciamento-usuario")
    public ModelAndView usuarios(){
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-usuario");
        Iterable<Usuario> usuarioIt = usuarioRepository.findAll();
        andView.addObject("usuarios", usuarioIt);
        return andView;
    }

    @GetMapping("/editar-usuario/{idusuario}") //recebendo o parametro da url com o idusuario
    public ModelAndView editarusuario(@PathVariable("idusuario")Long idusuario){ //pegando a vareavel, "idusuario" esta pegando na URL e depois passa o tipo de dados igual ao objeto id do usuario
        ModelAndView andView = new ModelAndView("editar/editar-usuario");//retorna para tela de editar apos o click do botao
        Optional<Usuario> usuario = usuarioRepository.findById(idusuario);//busca o objeto usuario pelo id
        andView.addObject("usuarioObj", usuario.get());//passar o objeto da tela
        andView.addObject("cargoTypes", CargoUsuario.values());
        andView.addObject("especialidades", especialidadeRepository.findAll());
        return andView;
    }

    @PostMapping(value = "**/salvar-usuario-editado")
    public ModelAndView salvarEdit(Usuario usuario){
        usuarioRepository.save(usuario);
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-usuario");
        Iterable<Usuario> usuarioIt = usuarioRepository.findAll();
        andView.addObject("usuarios", usuarioIt);
        return andView;
    }

    @GetMapping("/excluir-usuario/{idusuario}") //recebendo o parametro da url com o idusuario
    public ModelAndView excluirusuario(@PathVariable("idusuario")Long idusuario){ //pegando a vareavel, "idusuario" esta pegando na URL e depois passa o tipo de dados igual ao objeto id do usuario

        usuarioRepository.deleteById(idusuario);

        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-usuario");//retorna para tela de editar apos o click do botao

        andView.addObject("usuarios", usuarioRepository.findAll());

        return andView;
    }

    @PostMapping("**/pesquisar-usuario")
    public ModelAndView pesquisar(@RequestParam("usuarioPesquisar")String usuarioPesquisar){

        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-usuario");//retorna para tela de editar apos o click do botao

        andView.addObject("usuarios", usuarioRepository.findUsuarioByName(usuarioPesquisar));

        return andView;
    }
}
