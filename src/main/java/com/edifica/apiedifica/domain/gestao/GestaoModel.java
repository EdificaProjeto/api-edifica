package com.edifica.apiedifica.domain.gestao;

import com.edifica.apiedifica.domain.usuario.Usuario;

public record GestaoModel(Long id, String nome, GestaoStatus status, GestaoTipo tipo, String usuario_id) {

    public static GestaoModel from(Gestao gestao){
        return new GestaoModel(gestao.getId(), gestao.getNome(), gestao.getStatus(), gestao.getTipo(), gestao.getUsuario().getId());
    }

}
