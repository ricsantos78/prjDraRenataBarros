package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.Enum.CargoUsuario;
import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import com.example.prjdrarenatabarros.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EspecialidadeService especialidadeService;

    @RequestMapping(method = RequestMethod.GET, value = "/cadastro-usuario")
    public ModelAndView cadastrousuario(){
        ModelAndView andView = new ModelAndView("cadastro/cadastro-usuario");
        andView.addObject("especialidades", especialidadeService.findAll());
        andView.addObject("usuarioObj", new Usuario());
        andView.addObject("cargoTypes", CargoUsuario.values());
        return andView;
    }

    @RequestMapping(method = RequestMethod.POST, value = "**/salvarUsuario")
    public ModelAndView salvar(Usuario usuario){
        usuarioService.save(usuario);
        ModelAndView andView = new ModelAndView("cadastro/cadastro-usuario");
        andView.addObject("usuarioObj", new Usuario());
        andView.addObject("cargoTypes", CargoUsuario.values());
        return andView;
    }

    @RequestMapping(method = RequestMethod.GET, value = "gerenciamento-usuario")
    public ModelAndView usuarios(){
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-usuario");
        Iterable<Usuario> usuarioIt = usuarioService.findAll();
        andView.addObject("usuarios", usuarioIt);
        return andView;
    }

    @GetMapping("/editar-usuario/{idusuario}") //recebendo o parametro da url com o idusuario
    public ModelAndView editarusuario(@PathVariable("idusuario")Long idusuario){ //pegando a vareavel, "idusuario" esta pegando na URL e depois passa o tipo de dados igual ao objeto id do usuario
        ModelAndView andView = new ModelAndView("editar/editar-usuario");//retorna para tela de editar apos o click do botao
        Optional<Usuario> usuario = Optional.ofNullable(usuarioService.find(idusuario));//busca o objeto usuario pelo id
        andView.addObject("usuarioObj", usuario.get());//passar o objeto da tela
        andView.addObject("cargoTypes", CargoUsuario.values());
        andView.addObject("especialidades", especialidadeService.findAll());
        return andView;
    }

    @PostMapping(value = "**/salvar-usuario-editado")
    public ModelAndView salvarEdit(Usuario usuario){
        usuarioService.save(usuario);
        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-usuario");
        Iterable<Usuario> usuarioIt = usuarioService.findAll();
        andView.addObject("usuarios", usuarioIt);
        return andView;
    }

    @GetMapping("/excluir-usuario/{idusuario}") //recebendo o parametro da url com o idusuario
    public ModelAndView excluirusuario(@PathVariable("idusuario")Long idusuario){ //pegando a vareavel, "idusuario" esta pegando na URL e depois passa o tipo de dados igual ao objeto id do usuario

        usuarioService.delete(idusuario);

        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-usuario");//retorna para tela de editar apos o click do botao

        andView.addObject("usuarios", usuarioService.findAll());

        return andView;
    }

    @PostMapping("**/pesquisar-usuario")
    public ModelAndView pesquisar(@RequestParam("usuarioPesquisar")String usuarioPesquisar){

        ModelAndView andView = new ModelAndView("gerenciamento/gerenciamento-usuario");//retorna para tela de editar apos o click do botao

        andView.addObject("usuarios", usuarioService.findUsuarioByName(usuarioPesquisar));

        return andView;
    }
}
