package com.edifica.apiedifica.controllers;

import com.edifica.apiedifica.domain.gestao.Gestao;
import com.edifica.apiedifica.domain.usuario.AutenticacaoDTO;
import com.edifica.apiedifica.domain.usuario.LoginResponseDTO;
import com.edifica.apiedifica.domain.usuario.RegistrarDTO;
import com.edifica.apiedifica.domain.usuario.Usuario;
import com.edifica.apiedifica.infra.security.TokenService;
import com.edifica.apiedifica.repositories.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AutenticacaoController {


    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository usuarioRepository;

    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AutenticacaoDTO data){

        var senhaUSuario = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(senhaUSuario);
        var token = tokenService.gerarToken((Usuario) auth.getPrincipal());
        Usuario usuario = usuarioRepository.findByEmail(data.email());
        return ResponseEntity.ok(new LoginResponseDTO(token, usuario.getId(),usuario.getNome()));

    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid RegistrarDTO data){
        if(this.usuarioRepository.findByEmail(data.email()) != null) return ResponseEntity.badRequest().build();

        String senhaCriptografada = new BCryptPasswordEncoder().encode(data.senha());
        Usuario novoUsuario = new Usuario(data.nome(), data.email(), senhaCriptografada, data.papel());

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();

    }

    @GetMapping
    public List<Usuario> getUsuario(){
        return usuarioRepository.findAll();
    }


}
