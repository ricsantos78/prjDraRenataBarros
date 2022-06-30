package com.example.prjdrarenatabarros.domain.dtos;

import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.domain.entity.Usuario;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AtendimentoDto {
    @NotBlank
    private Usuario usuario;
    @NotBlank
    private Paciente paciente;
    @NotBlank
    private String dataPedido;
}
