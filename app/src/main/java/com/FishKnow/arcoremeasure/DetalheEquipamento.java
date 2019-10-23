package com.FishKnow.arcoremeasure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalheEquipamento extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhe_equipamento);

        Intent intent = getIntent();

        ImageView imageView = findViewById(R.id.imageEqpt);
        TextView textView = findViewById(R.id.nome);
        TextView textView2 = findViewById(R.id.utilizacao);


        String image = intent.getStringExtra("imageEqpt");
        String imageFull = intent.getStringExtra("imageFull");
        int imageID = this.getResources().getIdentifier(imageFull, "drawable", this.getPackageName());

        imageView.setImageResource(imageID);


        textView.setText(getIntent().getStringExtra("nome"));
        textView2.setText(getIntent().getStringExtra("utilizacao"));
    }
}
