package com.FishKnow.arcoremeasure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    private Button medir, lista, equipamento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_menu);

        medir = findViewById(R.id.btnMedir);
        lista = findViewById(R.id.btnLista);
        equipamento = findViewById(R.id.btnEquipamentos);

        medir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inMedir = new Intent(MainActivity.this, ArMeasureActivity.class);
                startActivity(inMedir);
            }
        });

        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inLista = new Intent(MainActivity.this, ListaPeixes.class);
                startActivity(inLista);
            }
        });

        equipamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inEquipamento = new Intent(MainActivity.this, ListaEquipamento.class);
                startActivity(inEquipamento);
            }
        });
    }
}
