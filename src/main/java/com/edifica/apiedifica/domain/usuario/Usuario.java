package com.edifica.apiedifica.domain.usuario;

import com.edifica.apiedifica.domain.gestao.Gestao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    private String id;
    private String nome;
    private String email;
    private String senha;
    private UsuarioPapel papel;

    public Usuario(String nome, String email, String senha, UsuarioPapel papel){
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
    }

    @JsonIgnore
    @OneToMany(
            mappedBy = "usuario", cascade = CascadeType.ALL
    )
    private List<Gestao> gestoes = new ArrayList<Gestao>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.papel == UsuarioPapel.ADMIN) return List.of(new SimpleGrantedAuthority("PAPEL_ADMIN"), new SimpleGrantedAuthority("PAPEL_USUARIO"));
        else return List.of(new SimpleGrantedAuthority("PAPEL_USUARIO"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
