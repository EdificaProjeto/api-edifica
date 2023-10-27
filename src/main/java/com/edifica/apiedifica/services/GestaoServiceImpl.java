package com.edifica.apiedifica.services;

import com.edifica.apiedifica.domain.gestao.Gestao;
import com.edifica.apiedifica.domain.gestao.GestaoDTO;
import com.edifica.apiedifica.domain.usuario.Usuario;
import com.edifica.apiedifica.repositories.GestaoRepository;
import com.edifica.apiedifica.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestaoServiceImpl implements GestaoService{

    private GestaoRepository gestaoRepository;
    private UsuarioRepository usuarioRepository;

    @Autowired
    public GestaoServiceImpl(GestaoRepository gestaoRepository, UsuarioRepository usuarioRepository) {
        this.gestaoRepository = gestaoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public GestaoDTO criarGestao(String usuarioId, GestaoDTO gestaoDto) {

        Gestao gestao = mapToEntity(gestaoDto);
        Usuario usuario = usuarioRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Não deu certo"));
        gestao.setUsuario(usuario);
        Gestao novaGestao = gestaoRepository.save(gestao);

        return mapToDto(novaGestao);
    }

    private GestaoDTO mapToDto(Gestao gestao) {
        GestaoDTO gestaoDto = new GestaoDTO();
        gestao.setId(gestaoDto.getId());
        gestao.setNome(gestaoDto.getNome());
        gestao.setTipo(gestaoDto.getTipo());
        gestao.setStatus(gestaoDto.getStatus());
        return gestaoDto;
    }

    private Gestao mapToEntity(GestaoDTO gestaoDto) {
        Gestao gestao = new Gestao();
        gestao.setId(gestaoDto.getId());
        gestao.setNome(gestaoDto.getNome());
        gestao.setTipo(gestaoDto.getTipo());
        gestao.setStatus(gestaoDto.getStatus());
        return gestao;
    }
}
