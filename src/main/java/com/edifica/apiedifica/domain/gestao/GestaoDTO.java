package com.edifica.apiedifica.domain.gestao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GestaoDTO {

    private Long id;
    private String nome;
    private GestaoStatus status;
    private GestaoTipo tipo;

}
