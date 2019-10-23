package com.FishKnow.arcoremeasure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import static java.sql.Types.NULL;

public class DetalhePeixe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_peixe);


        Intent intent = getIntent();

        ImageView imageView = findViewById(R.id.imageEqpt);
        TextView textView = findViewById(R.id.nome);
        TextView textView2 = findViewById(R.id.nomeCientifico);
        TextView textView3 = findViewById(R.id.utilizacao);
        TextView textView4 = findViewById(R.id.familia);
        TextView textView5 = findViewById(R.id.genero);

        Peixes peixes = (Peixes) getIntent().getSerializableExtra(Peixes.TAG_PEIXE);

        String image = peixes.getImageFull();
        int imageID = this.getResources().getIdentifier(image, "drawable", this.getPackageName());



        imageView.setImageResource(imageID);
        if(peixes.getNomePopular().equals("")) {
            textView.setText(""+ peixes.getNomePopular());
        }
        else {
            textView.setText("Nome: " + peixes.getNomePopular());
        }

        textView2.setText("Nome científico: "+ peixes.getNomeCientifico());
        textView3.setText(""+ peixes.getDescricao());
        textView4.setText("Família: "+ peixes.getFamilia());
        textView5.setText("Gênero: "+ peixes.getGenero());
    }
}
