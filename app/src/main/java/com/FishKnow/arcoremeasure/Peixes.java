package com.FishKnow.arcoremeasure;

import android.support.v7.widget.RecyclerView;

import java.io.Serializable;

public class Peixes implements Serializable {

    public static final String TAG_PEIXE = "peixe";

    private String nomePopular;
    private String nomeCientifico;
    private String descricao;
    private String image;
    private String familia;
    private String genero;
    private boolean extincao;
    private boolean minMax;
    private Double min;
    private Double max;
    private Double medida;



    Peixes(String nomePopular, String nomeCientifico, String descricao, String image, String familia, String genero, boolean extincao, boolean minMax, Double min, Double max){
        this.nomePopular = nomePopular;
        this.nomeCientifico = nomeCientifico;
        this.descricao = descricao;
        this.image = image;
        this.familia = familia;
        this.genero = genero;
        this.extincao = extincao;
        this.minMax = minMax;
        this.min = min;
        this.max = max;
    }

    public String getNomePopular() {
        return nomePopular;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImage() {
        return image;
    }

    public String getFamilia() {
        return familia;
    }

    public String getGenero() {
        return genero;
    }

    public boolean isExtincao() {
        return extincao;
    }

    public boolean isMinMax() {
        return minMax;
    }

    public  Double getMin(){return min;}

    public  Double getMax(){return max;}

    public Double getMedida() {
        return medida;
    }

    public void setMedida(Double medida) {
        this.medida = medida;
    }
}


