package com.edifica.apiedifica.services;

import com.edifica.apiedifica.domain.material.Material;
import com.edifica.apiedifica.domain.material.MaterialClassificado;
import com.edifica.apiedifica.repositories.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<MaterialClassificado> getMaterialsByABC() {
        List<Material> materials = materialRepository.findAll();

        // Ordenar os materiais pelo valor total (valor) em ordem decrescente
        materials.sort(Comparator.comparing(Material::getValor).reversed());

        BigDecimal totalSum = materials.stream()
                .map(Material::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal accumulatedPercentage = BigDecimal.ZERO;
        List<MaterialClassificado> materialsWithClassification = new ArrayList<>();

        for (Material material : materials) {
            BigDecimal individualPercentage =
                    material.getValor().divide(totalSum, 4, BigDecimal.ROUND_HALF_UP);

            accumulatedPercentage = accumulatedPercentage.add(individualPercentage);

            String classificacao;
            if (accumulatedPercentage.compareTo(new BigDecimal("0.8")) <= 0) {
                classificacao = "A";
            } else if (accumulatedPercentage.compareTo(new BigDecimal("0.95")) <= 0) {
                classificacao = "B";
            } else {
                classificacao = "C";
            }

            materialsWithClassification.add(
                    new MaterialClassificado(material, classificacao));
        }

        return materialsWithClassification;
    }

}
