package com.example.prjdrarenatabarros.domain.Enum;

public enum CargoUsuario {
    ADM("Administrador"),
    COL("Colaborador"),
    REC("Recepção");

    private final String cargo;
    CargoUsuario(String cargo){
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }
}
