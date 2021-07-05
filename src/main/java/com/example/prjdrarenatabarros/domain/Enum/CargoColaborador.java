package com.example.prjdrarenatabarros.domain.Enum;

public enum CargoColaborador {
    ADM("Administrador"),
    COL("Colaborador"),
    REC("Recepção");

    private final String cargo;
    CargoColaborador(String cargo){
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}
