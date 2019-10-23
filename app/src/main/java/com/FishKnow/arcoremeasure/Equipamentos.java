package com.FishKnow.arcoremeasure;

public class Equipamentos {
    private String nome;
    private String utilizacao;
    private String image;
    private boolean permitido;
    private String imageFull;

    Equipamentos(String nome, String utilizacao, String image, boolean permitido, String imageFull){
        this.nome = nome;
        this.utilizacao = utilizacao;
        this.image = image;
        this.permitido = permitido;
        this.imageFull = imageFull;
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

    public String getImageFull() {
        return imageFull;
    }

}
