package com.edifica.apiedifica.repositories;

import com.edifica.apiedifica.domain.gestao.Gestao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GestaoRepository extends JpaRepository<Gestao, Long> {

    List<Gestao> findByUsuarioId(String id);

}
