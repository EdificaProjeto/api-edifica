package com.edifica.apiedifica.controllers;

import com.edifica.apiedifica.domain.gestao.Gestao;
import com.edifica.apiedifica.domain.gestao.GestaoDTO;
import com.edifica.apiedifica.domain.gestao.GestaoModel;
import com.edifica.apiedifica.repositories.GestaoRepository;
import com.edifica.apiedifica.repositories.UsuarioRepository;
import com.edifica.apiedifica.services.GestaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/gestao")
public class GestaoController {

    private final GestaoRepository gestaoRepository;
    private GestaoService  gestaoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GestaoModel adicionar(@RequestBody @Valid Gestao gestaoInput){
        Gestao gestao1 = gestaoRepository.save(gestaoInput);
        return  GestaoModel.from(gestao1);
    }

/*
    @PostMapping("/{usuarioId}")
    public ResponseEntity<GestaoDTO> createReview(@PathVariable(value = "usuarioId") String usuarioId, @RequestBody GestaoDTO gestaoDto) {
        return new ResponseEntity<>(gestaoService.criarGestao(usuarioId, gestaoDto), HttpStatus.CREATED);
    }

 */

    /*
    @GetMapping
    public List<GestaoModel> getGestao(@Param("usuarioId") String usuarioId){

        return gestaoRepository.findByUsuarioId(usuarioId).stream().map(GestaoModel::from).collect(Collectors.toList());

    }

     */

    public List<GestaoModel> getGestao(){
        return gestaoRepository.findAll().stream().map(GestaoModel::from).collect(Collectors.toList());

    }


    @GetMapping("/{usuarioId}")
    public List<GestaoModel> getGestao(@PathVariable String usuarioId){
        return gestaoRepository.findByUsuarioId(usuarioId).stream().map(GestaoModel::from).collect(Collectors.toList());

    }


}
