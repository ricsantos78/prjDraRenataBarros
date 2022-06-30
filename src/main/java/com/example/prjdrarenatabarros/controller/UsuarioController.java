package com.example.prjdrarenatabarros.controller;

import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.domain.entity.UsuarioRole;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import com.example.prjdrarenatabarros.services.RoleService;
import com.example.prjdrarenatabarros.services.UsuarioRoleService;
import com.example.prjdrarenatabarros.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final EspecialidadeService especialidadeService;

    private final RoleService roleService;

    private final UsuarioRoleService usuarioRoleService;
    
    @GetMapping(value = "cadastro-usuario")
    public String cadastroUsuario(ModelMap andView) {
        andView.addAttribute(getUsuarioObj(), new Usuario());
        andView.addAttribute(getEspecialidades(), especialidadeService.findAll());
        return "cadastro/cadastro-usuario";
    }
    
    @PostMapping(value = "**/salvarUsuario")
    public String salvar(@Valid Usuario usuario, BindingResult bindingResult, @RequestParam("confirma") String senha, Model andView, RedirectAttributes attr) {
        Usuario log = usuarioService.findUsuarioByLogin(usuario.getLogin());
        List<String> msg = new ArrayList<>();
        if (log != null) {
            andView.addAttribute(getUsuarioObj(), usuario);
            andView.addAttribute(getEspecialidades(), especialidadeService.findAll());
            attr.addFlashAttribute(getMsgError(), "Usuario já existe");
            return getRedirectCadUsuario();

        }
        if (!(usuario.getSenha().equals(senha))) {
            andView.addAttribute(getUsuarioObj(), usuario);
            andView.addAttribute(getEspecialidades(), especialidadeService.findAll());
            attr.addFlashAttribute(getMsgError(), "As senhas devem ser identicas!");
            return getRedirectCadUsuario();

        }
        if (bindingResult.hasErrors()) {
            andView.addAttribute(getUsuarioObj(), usuario);
            andView.addAttribute(getEspecialidades(), especialidadeService.findAll());

            for (ObjectError objectError : bindingResult.getAllErrors()) {
                msg.add(objectError.getDefaultMessage()); //<--vem  getDefaultMessage vem das anotaçoes
            }
            attr.addFlashAttribute(getMsgError(), msg);
            return getRedirectCadUsuario();
        }

        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        usuarioService.save(usuario);
        Iterable<Usuario> usuarioIt = usuarioService.findAll();
        andView.addAttribute(getUsuarios(), usuarioIt);
        andView.addAttribute(getCargoTypes(), roleService.findAll());
        andView.addAttribute(getEspecialidades(), especialidadeService.findAll());
        attr.addFlashAttribute(getMsgSucess(), "Usuario cadastrado com sucesso");
        return getRedirectGerUsuario();
    }

    @GetMapping(value = "gerenciamento-usuario")
    public String usuarios(ModelMap andView) {
        Iterable<Usuario> usuarioIt = usuarioService.findAll();
        andView.addAttribute(getUsuarios(), usuarioIt);
        andView.addAttribute(getCargoTypes(), roleService.findAll());
        andView.addAttribute(getEspecialidades(), especialidadeService.findAll());
        return "gerenciamento/gerenciamento-usuario";
    }

    @PostMapping(value = "**/salvar-usuario-editado")
    public String salvarEdit(@Valid Usuario usuario, BindingResult bindingResult, Model andView, RedirectAttributes attr) {
        List<String> msg = new ArrayList<>();
        Usuario log = usuarioService.findUsuarioByLogin(usuario.getLogin());

        if (log != null) {
            Iterable<Usuario> usuarioIt = usuarioService.findAll();
            andView.addAttribute(getUsuarios(), usuarioIt);
            andView.addAttribute(getCargoTypes(), roleService.findAll());
            andView.addAttribute(getEspecialidades(), especialidadeService.findAll());
            attr.addFlashAttribute(getMsgError(), "Não houve alteração, Usuario já existe");
            return getRedirectGerUsuario();
        }
        if (bindingResult.hasErrors()) {
            Iterable<Usuario> usuarioIt = usuarioService.findAll();
            andView.addAttribute(getUsuarios(), usuarioIt);
            andView.addAttribute(getCargoTypes(), roleService.findAll());
            andView.addAttribute(getEspecialidades(), especialidadeService.findAll());
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                msg.add(objectError.getDefaultMessage()); //<--vem  getDefaultMessage vem das anotaçoes
            }
            attr.addFlashAttribute(getMsgError(), msg);
            return getRedirectGerUsuario();
        }

        usuarioService.save(usuario);
        Iterable<Usuario> usuarioIt = usuarioService.findAll();
        andView.addAttribute(getUsuarios(), usuarioIt);
        andView.addAttribute(getCargoTypes(), roleService.findAll());
        andView.addAttribute(getEspecialidades(), especialidadeService.findAll());
        attr.addFlashAttribute(getMsgSucess(), "Usuario editado com sucesso");
        return getRedirectGerUsuario();
    }

    @GetMapping("/excluir-usuario/{idusuario}") //recebendo o parametro da url com o idusuario
    public String excluirusuario(@PathVariable("idusuario") UUID idusuario, RedirectAttributes attr) { //pegando a vareavel, "idusuario" esta pegando na URL e depois passa o tipo de dados igual ao objeto id do usuario

        usuarioService.delete(idusuario);

        attr.addFlashAttribute(getUsuarios(), usuarioService.findAll());
        attr.addFlashAttribute(getCargoTypes(), roleService.findAll());
        attr.addFlashAttribute(getEspecialidades(), especialidadeService.findAll());
        attr.addFlashAttribute(getMsgSucess(), "Usuario excluido com sucesso");

        return getRedirectGerUsuario();
    }

    @PostMapping("**/pesquisar-usuario")
    public String pesquisar(ModelMap andView, @RequestParam("usuarioPesquisar") String usuarioPesquisar) {

        andView.addAttribute(getUsuarios(), usuarioService.findUsuarioByNome(usuarioPesquisar));
        andView.addAttribute(getCargoTypes(), roleService.findAll());
        andView.addAttribute(getEspecialidades(), especialidadeService.findAll());

        return "gerenciamento/gerenciamento-usuario";
    }

    @PostMapping(value = "**/role-usuario/{idUsuario}")
    public String salvar(UsuarioRole usuarioRole,
                         @PathVariable("idUsuario") UUID idu,
                         @RequestParam("role") UUID idr, Model andView, RedirectAttributes attr) {

        Usuario usuario = usuarioService.find(idu);
        usuarioRole.setUsuario(usuario);

        UsuarioRole log = usuarioRoleService.findUsuarioRole(usuario.getId(), idr);

        if (log != null) {
            Iterable<Usuario> usuarioIt = usuarioService.findAll();
            andView.addAttribute(getUsuarios(), usuarioIt);
            andView.addAttribute(getCargoTypes(), roleService.findAll());
            andView.addAttribute(getEspecialidades(), especialidadeService.findAll());
            attr.addFlashAttribute(getMsgError(), "Já existe o cargo " + usuarioRole.getRole().getDescricao() + " para o Usuario " + usuarioRole.getUsuario().getNome() + "!");
            return getRedirectGerUsuario();
        }

        usuarioRoleService.save(usuarioRole);
        Iterable<Usuario> usuarioIt = usuarioService.findAll();
        andView.addAttribute(getUsuarios(), usuarioIt);
        andView.addAttribute(getCargoTypes(), roleService.findAll());
        andView.addAttribute(getEspecialidades(), especialidadeService.findAll());
        attr.addFlashAttribute(getMsgSucess(), "Cargo " + usuarioRole.getRole().getDescricao() + " atribuido para o Usuario " + usuarioRole.getUsuario().getNome() + " com sucesso!");
        return getRedirectGerUsuario();
    }
    
    @NotNull
    private String getEspecialidades() {
        return "especialidades";
    }

    @NotNull
    private String getUsuarioObj() {
        return "usuarioObj";
    }

    @NotNull
    private String getMsgError() {
        return "msgError";
    }

    @NotNull
    private String getRedirectCadUsuario() {
        return "redirect:/cadastro-usuario";
    }

    @NotNull
    private String getUsuarios() {
        return "usuarios";
    }

    @NotNull
    private String getCargoTypes() {
        return "cargoTypes";
    }

    @NotNull
    private String getMsgSucess() {
        return "msgSucess";
    }

    @NotNull
    private String getRedirectGerUsuario() {
        return "redirect:/gerenciamento-usuario";
    }
}
