package com.FishKnow.arcoremeasure;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import static java.sql.Types.NULL;

public class DetalhePeixeAlt extends AppCompatActivity {

    Double tamanhoRegua;

    private Peixes peixe;

    public Double getTamanhoRegua() {
        return tamanhoRegua;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_peixe_alt);

        Intent intent = getIntent();

        ImageView imageView = findViewById(R.id.imageEqpt);
        final TextView textView = findViewById(R.id.nome);
        TextView textView2 = findViewById(R.id.nomeCientifico);
        TextView textView3 = findViewById(R.id.utilizacao);
        TextView textView4 = findViewById(R.id.familia);
        TextView textView5 = findViewById(R.id.genero);
        final EditText editText = findViewById(R.id.editText2);
        Button button = findViewById(R.id.button);
        Button button2 = findViewById(R.id.button2);

        peixe = (Peixes) getIntent().getSerializableExtra("peixe");

        String image = peixe.getImageFull();
        int imageID = this.getResources().getIdentifier(image, "drawable", this.getPackageName());



        imageView.setImageResource(imageID);
        if(peixe.getNomePopular().equals("")) {
            textView.setText(""+ peixe.getNomePopular());
        }
        else {
            textView.setText("Nome: " + peixe.getNomePopular());
        }
        textView2.setText("Nome científico: "+ peixe.getNomeCientifico());
        textView3.setText(""+ peixe.getDescricao());
        textView4.setText("Família: "+ peixe.getFamilia());
        textView5.setText("Gênero: "+ peixe.getGenero());

 final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Atenção!");
        alertDialogBuilder.setMessage("Essa espécie possui tamanho minimo e máximo, " +
                "se deseja mensurar o tamanho dela clique no botão " +
                "\"MEDIR ESSA ESPÉCIE USANDO O APLICATIVO\", " +
                "também possui a opção de medir com uma régua e inserir a medida." +
                " caso escolha essa opção insira a medida em centimetros e clique em confirmar.");
        alertDialogBuilder.setPositiveButton(" Ok ", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogBuilder.show();

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetalhePeixeAlt.this, ArMeasureActivityAlt.class);
                intent.putExtra(Peixes.TAG_PEIXE, peixe);
                intent.setClass(DetalhePeixeAlt.this, ArMeasureActivityAlt.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tamanhoRegua = ( Double.parseDouble(editText.getText().toString()));
                peixe.setMedida(tamanhoRegua);
                if (getTamanhoRegua() >= peixe.getMin() && getTamanhoRegua() <= peixe.getMax()) {
                    Intent intent = new Intent(DetalhePeixeAlt.this, MedidaPermitida.class);
                    intent.putExtra(Peixes.TAG_PEIXE, peixe);
                    intent.setClass(DetalhePeixeAlt.this, MedidaPermitida.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(DetalhePeixeAlt.this, MedidaNegada.class);
                    intent.putExtra(Peixes.TAG_PEIXE, peixe);
                    intent.setClass(DetalhePeixeAlt.this, MedidaNegada.class);
                    startActivity(intent);

                }
            }
        });
    }
}
