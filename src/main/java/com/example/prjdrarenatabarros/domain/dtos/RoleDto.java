package com.example.prjdrarenatabarros.domain.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleDto {
    @NotBlank
    private String nomeRole;
    @NotBlank
    private String Descricao;
}
