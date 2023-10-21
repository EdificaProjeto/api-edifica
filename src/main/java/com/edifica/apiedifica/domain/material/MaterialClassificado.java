package com.edifica.apiedifica.domain.material;

public class MaterialClassificado {

    private Material material;
    private String classificacao;

    public MaterialClassificado(Material material, String classificacao) {
        this.material = material;
        this.classificacao = classificacao;
    }

    public Material getMaterial() {
        return material;
    }

    public String getClassificacao() {
        return classificacao;
    }
}
