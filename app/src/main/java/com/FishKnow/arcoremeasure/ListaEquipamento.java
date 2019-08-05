package com.FishKnow.arcoremeasure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        equipamentoLista.add(new Equipamentos("Tarrafa", "É uma rede de pesca circular com pequenos pesos distribuídos em torno de toda a circunferência da malha. A tarrafa é arremessada geralmente com as mãos, de tal maneira que esta se abra o máximo possível antes de cair na água. Ao entrar em contato com a água, a rede afunda imediatamente.", "tarrafa", false));
        equipamentoLista.add(new Equipamentos("Anzol de galho", "sdf", "anzoldegalho", false));
        equipamentoLista.add(new Equipamentos("3", "sdf", "", false));
        equipamentoLista.add(new Equipamentos("4", "sdf", "property_image_1", false));
        equipamentoLista.add(new Equipamentos("5", "sdf", "", false));
        equipamentoLista.add(new Equipamentos("6", "sdf", "property_image_1", false));
        equipamentoLista.add(new Equipamentos("7", "sdf", "", false));
        equipamentoLista.add(new Equipamentos("8", "sdf", "property_image_1", false));
        equipamentoLista.add(new Equipamentos("9", "sdf", "", false));
        equipamentoLista.add(new Equipamentos("10", "sdf", "property_image_1", false));

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
                nome.setText("Nome : " + equipamentos.getNome() + ", Proibida a utilização");
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
