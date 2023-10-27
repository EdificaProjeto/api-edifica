package com.edifica.apiedifica.controllers;

import com.edifica.apiedifica.domain.gestao.Gestao;
import com.edifica.apiedifica.domain.material.Material;
import com.edifica.apiedifica.domain.material.MaterialClassificado;
import com.edifica.apiedifica.repositories.GestaoRepository;
import com.edifica.apiedifica.repositories.MaterialRepository;
import com.edifica.apiedifica.services.MaterialService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gestao/{gestaoId}/materiais")
@AllArgsConstructor
public class MaterialController {

    private final MaterialService materialService;
    private final MaterialRepository materialRepository;
    private final GestaoRepository gestaoRepository;

    @GetMapping
    public List<Material> listar(@PathVariable Long gestaoId){
        Gestao gestao = gestaoRepository.findById(gestaoId).orElseThrow(() -> new RuntimeException("Erro!"));
        return gestao.getMateriais();
    }

    @GetMapping("/abc")
    public Map<String, List<Material>> getMaterialsByABC(@PathVariable Long gestaoId) {

        List<MaterialClassificado> materialClassificado = materialService.getMaterialsByABC(gestaoId);

        Map<String, List<Material>> materiaisClassificados = materialClassificado.stream()
                .collect(Collectors.groupingBy(MaterialClassificado::getClassificacao,
                        Collectors.mapping(MaterialClassificado::getMaterial, Collectors.toList())));

        return materiaisClassificados;
    }


/*
    @GetMapping("/{materialId}")
    public ResponseEntity<Material> buscar(@PathVariable Long materialId){
        return materialRepository.findById(materialId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

 */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Material adicionar(@PathVariable Long gestaoId,@Valid @RequestBody Material material) {
        //material.setValor(material.getValor().divide(BigDecimal.valueOf(material.getQuantidade())));
        Gestao gestao = gestaoRepository.findById(gestaoId).orElseThrow(() -> new RuntimeException("Erro!"));
        material.setGestao(gestao);
        gestao.getMateriais().add(material);
        gestaoRepository.save(gestao);
        return material;
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
