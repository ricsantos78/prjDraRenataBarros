package com.example.prjdrarenatabarros.domain.dtos;

import com.example.prjdrarenatabarros.domain.entity.Servico;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class EspecialidadeDto {
    @NotBlank
    private String nome;

    @NotBlank
    private List<Servico> servicos;
}
