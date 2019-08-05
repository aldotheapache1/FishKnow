package com.FishKnow.arcoremeasure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MedidaPermitida extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medida_permitida);
        TextView textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button3);
        Peixes peixes = (Peixes) getIntent().getSerializableExtra(Peixes.TAG_PEIXE);

        textView.setText("Seu peixe de nome: "+peixes.getNomeCientifico()+" e medida: "
                + peixes.getMedida() +" cm, está dentro do tamanho permitido.");


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedidaPermitida.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
