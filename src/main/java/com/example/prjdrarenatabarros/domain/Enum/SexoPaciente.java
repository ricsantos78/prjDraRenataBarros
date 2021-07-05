package com.example.prjdrarenatabarros.domain.Enum;

public enum SexoPaciente {
    M("Masculino"),
    F("Feminino");

    private final String sexo;

    SexoPaciente(String sexo) {
        this.sexo = sexo;
    }

    public String getSexo() {
        return sexo;
    }
}
