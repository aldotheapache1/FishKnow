package com.FishKnow.arcoremeasure;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListaEquipamento extends AppCompatActivity {
    private ArrayList<Equipamentos> equipamentoLista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_equipamento);

        AlertDialog.Builder builder1 = new AlertDialog.Builder(ListaEquipamento.this);
        builder1.setTitle("Atenção!");
        builder1.setMessage(Html.fromHtml("Aqui estão presentes alguns equipamentos PROIBIDOS de serem utilizados, para mais informações: Instituto de Meio Ambiente de Mato Grosso do Sul, <a href=\"http://www.imasul.ms.gov.br/\">IMASUL</a>"));

        builder1.setCancelable(false);
        builder1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog Alert1 = builder1.create();
        Alert1 .show();
        ((TextView)Alert1.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());

        equipamentoLista.add(new Equipamentos("Tarrafa", "É uma rede de pesca circular com pequenos pesos distribuídos em torno de toda a circunferência da malha. A tarrafa é arremessada geralmente com as mãos, de tal maneira que esta se abra o máximo possível antes de cair na água. Ao entrar em contato com a água, a rede afunda imediatamente.", "tarrafa", false));
        equipamentoLista.add(new Equipamentos("Anzol de galho", "Como o nome já diz, se trata de um anzol amarrado em um galho, o uso é proibido, pois não afeta somente os peixes, mas também outros animais que transitam pelo local.", "anzoldegalho", false));
        equipamentoLista.add(new Equipamentos("Rede", "Estica-se a rede e aguarda um animal se enroscar", "rede", false));
        equipamentoLista.add(new Equipamentos("Cercado", "O cercado nada mais é do que uma estrutura cercada por tela, em que os peixes entram e não conseguem sair.", "cercado", false));
        equipamentoLista.add(new Equipamentos("Boia", "A boia é uma ferramenta que flutua sobre a água enquanto um anzol fica submerso.", "boia", false));
        equipamentoLista.add(new Equipamentos("Gancho ou garateia, pelo processo de lambada ", "O processo de lambada consiste em, deixar o gancho afundar e depois puxar ao sentir um peixe preso, é proibido pois muitas vezes apenas machuca os animais.", "garrateia", false));
        equipamentoLista.add(new Equipamentos("Fisga", "É semelhante a um tridente, é utilizado para perfurar o animal.", "fisga", false));
        equipamentoLista.add(new Equipamentos("Arpão", "O arpão pode ser utilizado de duas formas: ser atirado por um lancador de arpão ou ser lançado com as mãos.", "arpao", false));
        equipamentoLista.add(new Equipamentos("Espinhel", "Consiste na junção de vários anzóis esticados em uma linha.", "espinhel", false));
        equipamentoLista.add(new Equipamentos("Flecha", "A flecha é lançada com a utilização de do arco para atingir o peixe.", "flecha", false));
        equipamentoLista.add(new Equipamentos("Covo", "Estrutura em que o peixe entra com facilidade, mas não consegue sair.", "covo", false));
        ArrayAdapter<Equipamentos> adapter = new ListaEquipamento.equipamentosArrayAdapter(this, 0, equipamentoLista);

        ListView listView = findViewById(R.id.idListView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Equipamentos equipamentos = equipamentoLista.get(position);

                Intent intent = new Intent(ListaEquipamento.this, DetalheEquipamento.class);
                intent.putExtra("imageEqpt", equipamentos.getImage());
                intent.putExtra("nome", equipamentos.getNome());
                intent.putExtra("utilizacao", equipamentos.getUtilizacao());

                    intent.setClass(ListaEquipamento.this, DetalheEquipamento.class);
                    startActivity(intent);
                
            }

        });



    }

    class equipamentosArrayAdapter extends ArrayAdapter<Equipamentos> {
        private Context context;
        private List<Equipamentos> equipamentoLista;

        public equipamentosArrayAdapter(Context context, int resource, ArrayList<Equipamentos> objects) {
            super(context, resource, objects);

            this.context = context;
            this.equipamentoLista = objects;
        }
        public View getView(int position, View convertView, ViewGroup parent) {

            Equipamentos equipamentos = equipamentoLista.get(position);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view;
            if(equipamentos.isPermitido() == false){
               view = inflater.inflate(R.layout.equipamento_layout_alt, null);
            }else{
                view = inflater.inflate(R.layout.equipamento_layout, null);
            }


            TextView nome = view.findViewById(R.id.nome);
            if(equipamentos.isPermitido() == false){
                nome.setText("Nome : " + equipamentos.getNome() + ", A utilização é crime!");
            }
            else{
                nome.setText("Nome : " + equipamentos.getNome());
            }

            TextView utilizacao = view.findViewById(R.id.utilizacao);
            utilizacao.setText("Descrição: " + equipamentos.getUtilizacao());
            ImageView image = view.findViewById(R.id.imageEqpt);

            int tamanhoUtilizacao = equipamentos.getUtilizacao().length();
            if(tamanhoUtilizacao >= 100){
                String descriptionTrim = equipamentos.getUtilizacao().substring(0, 100) + "...";
                utilizacao.setText(descriptionTrim);
            }else{
                utilizacao.setText(equipamentos.getUtilizacao());
            }

            int imageID = context.getResources().getIdentifier(equipamentos.getImage(), "drawable", context.getPackageName());
            image.setImageResource(imageID);

            return view;
        }
    }
}
