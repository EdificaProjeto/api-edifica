package com.edifica.apiedifica.controllers;

import com.edifica.apiedifica.domain.material.Material;
import com.edifica.apiedifica.domain.material.MaterialClassificado;
import com.edifica.apiedifica.repositories.MaterialRepository;
import com.edifica.apiedifica.services.MaterialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/materiais")
public class MaterialController {

    private final MaterialService materialService;
    private final MaterialRepository materialRepository;

    public MaterialController(MaterialService materialService, MaterialRepository materialRepository) {
        this.materialService = materialService;
        this.materialRepository = materialRepository;
    }

    @GetMapping("/abc")
    public Map<String, List<Material>> getMaterialsByABC() {
        List<MaterialClassificado> materialClassificado = materialService.getMaterialsByABC();


        Map<String, List<Material>> materialsGroupedByClassification = materialClassificado.stream()
                .collect(Collectors.groupingBy(MaterialClassificado::getClassificacao,
                        Collectors.mapping(MaterialClassificado::getMaterial, Collectors.toList())));

        return materialsGroupedByClassification;
    }

    @GetMapping
    public List<Material> listar(){
        return materialRepository.findAll();
    }

    @GetMapping("/{materialId}")
    public ResponseEntity<Material> buscar(@PathVariable Long materialId){
        return materialRepository.findById(materialId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Material adicionar(@Valid @RequestBody Material material) {
        //material.setValor(material.getValor().divide(BigDecimal.valueOf(material.getQuantidade())));
        return materialRepository.save(material);
    }

    @PutMapping("/{materialId}")
    public ResponseEntity<Material> atualizar(@PathVariable Long materialId, @Valid @RequestBody Material material){
        if(!materialRepository.existsById(materialId)) {
            return ResponseEntity.notFound().build();
        }

        material.setId(materialId);
        material = materialRepository.save(material);

        return ResponseEntity.ok(material);

    }

    @DeleteMapping("/{materialId}")
    public ResponseEntity<Void> remover(@PathVariable Long materialId){
        if(!materialRepository.existsById(materialId)) {
            return ResponseEntity.notFound().build();
        }

        materialRepository.deleteById(materialId);

        return ResponseEntity.noContent().build();
    }

}
