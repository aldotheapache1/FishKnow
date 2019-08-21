package com.FishKnow.arcoremeasure;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PeixeAdapter extends RecyclerView.Adapter<PeixeAdapter.PeixesViewHolder> {

    private List<Peixes> peixes;
    private Context context;

    public PeixeAdapter(List<Peixes> peixes, Context context) {
        this.peixes = peixes;
        this.context = context;
    }


    @NonNull
    @Override
    public PeixesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        Peixes peixe = peixes.get(i);

        View view  =  null;
        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.peixes_layout,viewGroup, false);
        PeixesViewHolder peixesViewHolder = new PeixesViewHolder(view);

        return peixesViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PeixesViewHolder peixesViewHolder, int i) {

        Peixes peixe = peixes.get(i);

        peixesViewHolder.nomeCientifico.setText(peixe.getNomeCientifico());
        peixesViewHolder.nomePopular.setText(peixe.getNomePopular());
        peixesViewHolder.nomePopular.setTextColor(Color.BLACK);
        if(peixe.isExtincao() == false && peixe.isMinMax() == true){
            peixesViewHolder.nomePopular.setText("Nome : " + peixe.getNomePopular() + ", Tamanho mínimo e máximo exigidos");
            peixesViewHolder.nomePopular.setBackgroundResource(R.color.cor_peixe_amarelo);

        }
        else if(peixe.isExtincao() == false && peixe.isMinMax() == false){

            if(peixe.getNomePopular().equals("")) {
                peixesViewHolder.nomePopular.setText(""+ peixe.getNomePopular());

                peixesViewHolder.nomePopular.setBackgroundResource(R.color.white);
            }
            else {
                peixesViewHolder.nomePopular.setText("Nome: " + peixe.getNomePopular());
            }
        }

        else if(peixe.isExtincao() == true && peixe.isMinMax() == false){
            peixesViewHolder.nomePopular.setBackgroundResource(R.color.cor_peixe_vermelho);
            peixesViewHolder.nomePopular.setTextColor(Color.WHITE);
            if(peixe.getNomePopular().equals("")) {
                peixesViewHolder.nomePopular.setText(""+ peixe.getNomePopular()+"Proibida a captura");
            }
            else {
                peixesViewHolder.nomePopular.setText("Nome: " + peixe.getNomePopular()+ ", Proibida a captura");
            }
        }

        peixesViewHolder.nomeCientifico.setText("Nome Científico: " + peixe.getNomeCientifico());
        peixesViewHolder.descricao.setText("Sobre o peixe: " + peixe.getDescricao());

        peixesViewHolder.familia.setText("Família: " + peixe.getFamilia());
        peixesViewHolder.genero.setText("Gênero: " + peixe.getGenero());

        int tamanhoUtilizacao = peixe.getDescricao().length();
        if(tamanhoUtilizacao >= 100){
            String descriptionTrim = peixe.getDescricao().substring(0, 100) + "...";
            peixesViewHolder.descricao.setText(descriptionTrim);
        }else{
            peixesViewHolder.descricao.setText(peixe.getDescricao());
        }

        int imageID = context.getResources().getIdentifier(peixe.getImage(), "drawable", context.getPackageName());
        peixesViewHolder.image.setImageResource(imageID);

    }


    @Override
    public int getItemCount() {
        return peixes.size();
    }

    public static class PeixesViewHolder extends RecyclerView.ViewHolder {

        final TextView nomeCientifico;
        final TextView nomePopular;
        final TextView descricao;
        final TextView familia;
        final TextView genero;
        final ImageView image;


        public PeixesViewHolder(View view) {
            super(view);
            nomeCientifico = view.findViewById(R.id.nomeCientifico);
            nomePopular = view.findViewById(R.id.nome);
            descricao = view.findViewById(R.id.utilizacao);
            familia = view.findViewById(R.id.familia);
            genero = view.findViewById(R.id.genero);
            image = view.findViewById(R.id.imageEqpt);
        }

    }

}
