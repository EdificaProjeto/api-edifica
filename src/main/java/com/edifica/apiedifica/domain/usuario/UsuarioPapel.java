package com.edifica.apiedifica.domain.usuario;

public enum UsuarioPapel {

    ADMIN("admin"),
    USUARIO("usuario");

    private String papel;

    UsuarioPapel(String papel){
        this.papel = papel;
    }

    public String getPapel(){
        return papel;
    }

}
