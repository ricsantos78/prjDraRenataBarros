package com.example.prjdrarenatabarros.domain.dtos;

import com.example.prjdrarenatabarros.domain.entity.Atendimento;
import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class UsuarioDto {
    @NotBlank
    private String nome;

    @NotBlank
    private List<Role> roles;

    @Size(min = 5, max = 30, message = "Usuario deve conter entre 5 a 30 caracteres")
    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    private Especialidade especialidade;

    private List<Atendimento> atendimentos;
}
