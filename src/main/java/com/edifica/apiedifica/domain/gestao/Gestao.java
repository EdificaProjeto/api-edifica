package com.edifica.apiedifica.domain.gestao;

import com.edifica.apiedifica.domain.material.Material;
import com.edifica.apiedifica.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Gestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nome;

    @Enumerated(EnumType.STRING)
    private GestaoStatus status;

    @Enumerated(EnumType.STRING)
    private GestaoTipo tipo;

    @JsonIgnore
    @OneToMany(
            mappedBy = "gestao", cascade = CascadeType.ALL
    )
    private List<Material> materiais;

    @ManyToOne
    private Usuario usuario;

    public Gestao(String nome, GestaoStatus status, GestaoTipo tipo, List<Material> materiais) {
        this.nome = nome;
        this.status = status;
        this.tipo = tipo;
        this.materiais = materiais;
    }
}
