package com.edifica.apiedifica.controllers;

import com.edifica.apiedifica.domain.gestao.Gestao;
import com.edifica.apiedifica.repositories.GestaoRepository;
import com.edifica.apiedifica.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/gestao")
public class GestaoController {

    private final GestaoRepository gestaoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Gestao adicionar(@RequestBody @Valid Gestao gestao){

        return gestaoRepository.save(gestao);
    }

    @GetMapping
    public List<Gestao> getGestao(){
        return gestaoRepository.findAll();
    }

}
