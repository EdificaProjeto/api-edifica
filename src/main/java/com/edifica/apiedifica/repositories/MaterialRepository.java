package com.edifica.apiedifica.repositories;

import com.edifica.apiedifica.domain.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialRepository extends JpaRepository<Material, Long> {
}
