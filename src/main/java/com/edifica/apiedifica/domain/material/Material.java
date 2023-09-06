package com.edifica.apiedifica.domain.material;

import com.edifica.apiedifica.domain.gestao.Gestao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
@Table(name = "material")
@Getter

public class Material {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;

    private int quantidade;

    private BigDecimal valor;

    @JsonIgnore
    @ManyToOne
    private Gestao gestao;




}
