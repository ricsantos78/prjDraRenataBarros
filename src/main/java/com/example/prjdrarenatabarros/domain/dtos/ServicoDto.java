package com.example.prjdrarenatabarros.domain.dtos;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;

import javax.validation.constraints.NotBlank;

public class ServicoDto {
    @NotBlank
    private String nome;
    @NotBlank
    private String valor;
    @NotBlank
    private Especialidade especialidade;
}
