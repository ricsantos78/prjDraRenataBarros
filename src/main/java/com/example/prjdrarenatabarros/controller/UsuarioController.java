package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.domain.entity.UsuarioRole;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import com.example.prjdrarenatabarros.services.RoleService;
import com.example.prjdrarenatabarros.services.UsuarioRoleService;
import com.example.prjdrarenatabarros.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EspecialidadeService especialidadeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UsuarioRoleService usuarioRoleService;


    @RequestMapping(method = RequestMethod.GET, value = "cadastro-usuario")
    public String cadastroUsuario(ModelMap andView){
        andView.addAttribute("usuarioObj", new Usuario());
        andView.addAttribute("especialidades", especialidadeService.findAll());
        return "cadastro/cadastro-usuario";
    }

    @RequestMapping(method = RequestMethod.POST, value = "**/salvarUsuario")
    public String salvar(@Valid Usuario usuario, BindingResult bindingResult, @RequestParam("confirma")String senha, Model andView, RedirectAttributes attr){
        Usuario log = usuarioService.findUsuarioByLogin(usuario.getLogin());
        List<String> msg = new ArrayList<>();
        if(log != null){
            andView.addAttribute("usuarioObj", usuario);
            andView.addAttribute("especialidades", especialidadeService.findAll());
            attr.addFlashAttribute("msgError", "Usuario já existe");
            return "redirect:/cadastro-usuario";

        }
        if(!(usuario.getSenha().equals(senha))){
            andView.addAttribute("usuarioObj", usuario);
            andView.addAttribute("especialidades", especialidadeService.findAll());
            attr.addFlashAttribute("msgError", "As senhas devem ser identicas!");
            return "redirect:/cadastro-usuario";

        }
            if(bindingResult.hasErrors()){
                andView.addAttribute("usuarioObj", usuario);
                andView.addAttribute("especialidades", especialidadeService.findAll());

                for (ObjectError objectError : bindingResult.getAllErrors()){
                    msg.add(objectError.getDefaultMessage()); //<--vem  getDefaultMessage vem das anotaçoes
                }
                attr.addFlashAttribute("msgError", msg);
                return "redirect:/cadastro-usuario";
            }

        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuarioService.save(usuario);
        Iterable<Usuario> usuarioIt = usuarioService.findAll();
        andView.addAttribute("usuarios", usuarioIt);
        andView.addAttribute("cargoTypes", roleService.findAll());
        andView.addAttribute("especialidades", especialidadeService.findAll());
        attr.addFlashAttribute("msgSucess","Usuario cadastrado com sucesso");
        return "redirect:/gerenciamento-usuario";
    }

    @RequestMapping(method = RequestMethod.GET, value = "gerenciamento-usuario")
    public String usuarios(ModelMap andView){
        Iterable<Usuario> usuarioIt = usuarioService.findAll();
        andView.addAttribute("usuarios", usuarioIt);
        andView.addAttribute("cargoTypes", roleService.findAll());
        andView.addAttribute("especialidades", especialidadeService.findAll());
        return "gerenciamento/gerenciamento-usuario";
    }

    @PostMapping(value = "**/salvar-usuario-editado")
    public String salvarEdit(@Valid Usuario usuario, BindingResult bindingResult,Model andView, RedirectAttributes attr){
        List<String> msg = new ArrayList<>();
        Usuario log = usuarioService.findUsuarioByLogin(usuario.getLogin());

        if(log != null){
            Iterable<Usuario> usuarioIt = usuarioService.findAll();
            andView.addAttribute("usuarios", usuarioIt);
            andView.addAttribute("cargoTypes", roleService.findAll());
            andView.addAttribute("especialidades", especialidadeService.findAll());
            attr.addFlashAttribute("msgError", "Não houve alteração, Usuario já existe");
            return "redirect:/gerenciamento-usuario";
        }
        if (bindingResult.hasErrors()){
            Iterable<Usuario> usuarioIt = usuarioService.findAll();
            andView.addAttribute("usuarios", usuarioIt);
            andView.addAttribute("cargoTypes", roleService.findAll());
            andView.addAttribute("especialidades", especialidadeService.findAll());
            for (ObjectError objectError : bindingResult.getAllErrors()){
                msg.add(objectError.getDefaultMessage()); //<--vem  getDefaultMessage vem das anotaçoes
            }
            attr.addFlashAttribute("msgError", msg);
            return "redirect:/gerenciamento-usuario";
        }

        usuarioService.save(usuario);
        Iterable<Usuario> usuarioIt = usuarioService.findAll();
        andView.addAttribute("usuarios", usuarioIt);
        andView.addAttribute("cargoTypes", roleService.findAll());
        andView.addAttribute("especialidades", especialidadeService.findAll());
        attr.addFlashAttribute("msgSucess","Usuario editado com sucesso");
        return "redirect:/gerenciamento-usuario";
    }

    @GetMapping("/excluir-usuario/{idusuario}") //recebendo o parametro da url com o idusuario
    public String excluirusuario(@PathVariable("idusuario")Long idusuario, RedirectAttributes attr){ //pegando a vareavel, "idusuario" esta pegando na URL e depois passa o tipo de dados igual ao objeto id do usuario

        usuarioService.delete(idusuario);

        attr.addFlashAttribute("usuarios", usuarioService.findAll());
        attr.addFlashAttribute("cargoTypes", roleService.findAll());
        attr.addFlashAttribute("especialidades", especialidadeService.findAll());
        attr.addFlashAttribute("msgSucess", "Usuario excluido com sucesso");

        return "redirect:/gerenciamento-usuario";
    }

    @PostMapping("**/pesquisar-usuario")
    public String pesquisar(ModelMap andView,@RequestParam("usuarioPesquisar")String usuarioPesquisar){

        andView.addAttribute("usuarios", usuarioService.findUsuarioByName(usuarioPesquisar));
        andView.addAttribute("cargoTypes", roleService.findAll());
        andView.addAttribute("especialidades", especialidadeService.findAll());

        return "gerenciamento/gerenciamento-usuario";
    }

    @PostMapping(value = "**/role-usuario/{idUsuario}")
    public String salvar(UsuarioRole usuarioRole,
                               @PathVariable("idUsuario") Long idu,
                               @RequestParam("role") Long idr, Model andView, RedirectAttributes attr){

        Usuario usuario = usuarioService.find(idu);
        usuarioRole.setUsuario(usuario);

        UsuarioRole log = usuarioRoleService.findUsuarioRole(usuario.getId(),idr);  //usuarioService.findUsuarioByLogin(usuario.getLogin());

        if(log != null){
            Iterable<Usuario> usuarioIt = usuarioService.findAll();
            andView.addAttribute("usuarios", usuarioIt);
            andView.addAttribute("cargoTypes", roleService.findAll());
            andView.addAttribute("especialidades", especialidadeService.findAll());
            attr.addFlashAttribute("msgError", "Já existe o cargo " +usuarioRole.getRole().getDescricao()+ " para o Usuario "+usuarioRole.getUsuario().getNome()+"!");
            return "redirect:/gerenciamento-usuario";
        }

        usuarioRoleService.save(usuarioRole);
        Iterable<Usuario> usuarioIt = usuarioService.findAll();
        andView.addAttribute("usuarios", usuarioIt);
        andView.addAttribute("cargoTypes", roleService.findAll());
        andView.addAttribute("especialidades", especialidadeService.findAll());
        attr.addFlashAttribute("msgSucess", "Cargo " +usuarioRole.getRole().getDescricao()+ " atribuido para o Usuario "+usuarioRole.getUsuario().getNome()+" com sucesso!");
        return "redirect:/gerenciamento-usuario";
    }
}
