package com.edifica.apiedifica.services;

import com.edifica.apiedifica.domain.gestao.Gestao;
import com.edifica.apiedifica.domain.gestao.GestaoDTO;

public interface GestaoService {
    GestaoDTO criarGestao(String usuarioId, GestaoDTO gestaoDto);
}
