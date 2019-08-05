package com.FishKnow.arcoremeasure;

public class Equipamentos {
    private String nome;
    private String utilizacao;
    private String image;
    private boolean permitido;

    Equipamentos(String nome, String utilizacao, String image, boolean permitido){
        this.nome = nome;
        this.utilizacao = utilizacao;
        this.image = image;
        this.permitido = permitido;
    }

    public String getNome() {
        return nome;
    }

    public String getUtilizacao() {
        return utilizacao;
    }

    public String getImage() {
        return image;
    }

    public boolean isPermitido() {
        return permitido;
    }

}
