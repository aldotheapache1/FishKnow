package com.FishKnow.arcoremeasure;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

public class ListaPeixes extends AppCompatActivity {
    private ArrayList<Peixes> peixesLista = new ArrayList<>();
    double indeterminado = Double.POSITIVE_INFINITY;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_peixes);

        carregarListaDePeixes();

        recyclerView = findViewById(R.id.idListView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // usando um linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        //usando um divisor
        recyclerView.addItemDecoration( new DividerItemDecoration(this, LinearLayout.VERTICAL));

        // especificando um adapter personalizado
        PeixeAdapter mAdapter = new PeixeAdapter(peixesLista, this);
        recyclerView.setAdapter(mAdapter);

        //evento de click
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    //click curto
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                    //click longo
                    @Override
                    public void onLongItemClick(View view, int position) {
                        Peixes peixes = peixesLista.get(position);

                        Intent intent = new Intent(ListaPeixes.this, DetalhePeixe.class);
                        intent.putExtra(Peixes.TAG_PEIXE, peixes);

                        if(peixes.isMinMax() == false) {

                            intent.setClass(ListaPeixes.this, DetalhePeixe.class);
                            startActivity(intent);
                        }
                        else{
                            intent.putExtra(Peixes.TAG_PEIXE, peixes);
                            intent.setClass(ListaPeixes.this, DetalhePeixeAlt.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ));

    }

    private void carregarListaDePeixes() {
        // PEIXES DE CAPTURA PROIBIDA
        peixesLista.add(new Peixes("Teste proibido", "sdf", "", "",
                "ser ", "mmm", true, false, 0.0, 0.0));

        // PEIXES DE CAPTURA PERMITIDA E SEM TAMANHO MINIMO E MAXIMO
        peixesLista.add(new Peixes("teste sem minimo e maximo", ",.,m", "Consideram-se iscas vivas todos os organismos\n" +
                "aquáticos e terrestres nativos da respectiva bacia\n" +
                "hidrográfica, utilizada para pesca profissional e\n" +
                "esportivaA atividade de captura de iscas vivas somente poderá", "",
                "ser ", "", false, false, 0.0, 0.0));


        //PEIXES COM TAMANHOS MÍNIMOS/MÁXIMOS PARA CAPTURA - (DECRETO ESTADUAL n° 15.166 de 21 de fevereiro de 2019)

        peixesLista.add(new Peixes("Jaú", "Zungaro jahu", "Peixe de couro, seu corpo é grosso e curto; a cabeça grande e achatada. A coloração varia do pardo-esverdeado-claro a pardo-esverdeado-escuro, com manchas, no dorso, mas o ventre é branco. O indivíduo jovem, chamado jaupoca, apresenta pintas violáceas espalhadas pelo dorso amarelado.\n" +
                "Os barbilhões maxilares são curtos, não ultrapassando a base da nadadeira dorsal. A coloração varia do pardo esverdeado claro a escuro no dorso, mas o ventre é branco; indivíduos jovens apresentam pintas claras espalhadas pelo dorso.", "zungarojahu",
                "Pimelodidae ", "Zungaro", false, true, 95.0, 120.0));

        peixesLista.add(new Peixes("Cachara", "Pseudoplatystoma reticulatum", "Alcança um comprimento máximo de 60,5 cm. Distingue-se pelas seguintes características: linhas escuras que formam um padrão de reticulação e nunca são retas; laços curvos escuros que se unem na região dorsal do corpo formando diferentes células; mais laços como barras curvadas escuras estendendo-se muito abaixo da linha lateral. A cabeça mostra manchas ou linhas. Apresenta 42-43 vértebras. A barbatana anal sempre tem manchas. Não registra demarcação clara entre o dorso escuro e regiões ventrais pálidas; mandíbula inferior com pontos.", "pseudoplatystomareticulatum",
                "Pimelodidae ", "Pseudoplatystoma", false, true, 83.0, 112.0));

        peixesLista.add(new Peixes("Pintado", "Psedudoplatystoma corruscans", "Peixe de couro, com coloração acinzentada e diversas pintas pretas cilíndricas pelo corpo. Seu ventre tem uma coloração esbranquiçada. Seu corpo é alongado e roliço. Sua cabeça é grande e achatada, com dimensão entre 1/4 a 1/3 do tamanho do corpo. Tem uma cabeça mais larga que as outras espécies do gênero.\n" +
                "Sua característica mais importante são os múltiplos pontos pretos desenhados em sua pele que é acinzentada no dorso e esbranquiçada no ventre. As barbatanas mostram um tom avermelhado. Apresenta longos barbilhões. Possui ferrões junto às nadadeiras laterais e dorsal.\n" +
                "Faz parte da cultura dos povos indígenas locais comidas feitas com este peixe, muito apreciado por não conter quase espinhos e possuir carne branca bem macia. É comumente servido no espeto na Brasa no interior de São Paulo, especificamente em Piracicaba e outras cidades da região.", "pseudoplatystomacorruscans",
                "Pimelodidae", "Pseudoplatystoma", false, true, 90.0, 115.0));

        peixesLista.add(new Peixes("Pacu", "Piaractus mesopotamicus", "O pacu-caranha apresenta a cor negra quando encontrado em rios do Pantanal de águas cristalinas e isto também ocorre na Amazônia com o tambaqui (Colossoma macropomus) em alguns rios (mesmo a cor comum ao tambaqui ser outra) e também ocorre com (Colossoma bidens). Já o pacu-caranha do Rio Aquidauana não apresenta a cor negra sendo bem mais claro.",
                "piaractusmesopotamicus", "Characidae", "Piaractus", false, true, 45.0, 57.0));

        peixesLista.add(new Peixes("Piraputanga", "Brycon hilarii", "é um peixe caracídeo de dentição forte, carne saborosa, que mede até 50 centímetros de comprimento. Sua cor é olivácea-dourada, com nadadeiras caudal e anal vermelhas. Habita águas limpas. Sua pesca se faz com anzóis com isca de frutas ou carne de outros peixes.",
                "bryconhilarii", "Characidae", "Brycon", false, true, 30.0, indeterminado));

        peixesLista.add(new Peixes("Barbado", "Pinirampus pirinampu", "É um peixe de couro, com boca pequena e barbilhões sensoriais achatados. As características mais marcantes são os barbilhões longos e achatados, daí o nome vulgar, e a nadadeira adiposa muito longa, começando logo após a nadadeira dorsal. A coloração é cinza a castanho no dorso e flancos, clareando na região ventral. Logo ao ser retirado da água pode apresentar uma coloração esverdeada no dorso.\n" +
                "\n" +
                "A espécie é comum ao longo da beira dos rios, na frente de vilas e cidades, e, por esse motivo, é importante para a pesca de subsistência. É um peixe que briga muito, sendo um peixe bastante apreciado na pesca esportiva. Deve ser colocado no gelo, logo após capturado porque estraga facilmente.",
                "pinirampuspirinampu", "Pimelodidae", "Pinirampus", false, true, 60.0, indeterminado));

        peixesLista.add(new Peixes("Pati", "Luciopimelodus pati", "Seu nome científico é originário de seu nome comum patí , embora possa ser simplesmente referido como pez gato (\"peixe-gato\") em espanhol. Esta espécie é a única espécie reconhecida em seu gênero.\n" +
                "É encontrado principalmente em águas turvas e profundas com corrente moderada. Este peixe pode atingir até 103 centímetros.\n" +
                "Este peixe é visto com moderação como um peixe de aquário.",
                "luciopimeloduspati", "Pimelodidae", "Luciopimelodus", false, true, 65.0, indeterminado));

        peixesLista.add(new Peixes("Jurupoca", "Hemisorubim platyrhynchos", "É um peixe de água doce. É escuro, com manchas amareladas. Mede até 45 centímetros de comprimento. Costuma nadar na superfície da água e emitir um som semelhante ao pio de um pássaro, o que fez surgir a expressão popular \"hoje a jiripoca vai piar\". A carne é de ótima qualidade para o consumo humano.",
                "hemisorubimplatyrhynchos", "Pimelodidae", "Hemisorubim", false, true, 40.0, indeterminado));

        peixesLista.add(new Peixes("Curimbatá, curimba e Papa-terra", "Prochilodus lineatus", "Possui boca protáctil quando se projeta forma, juntos com os grandes lábios, um disco oral com seus pequenos e numerosos dentes enfileirados que servem para a raspagem de perifíton e detritos (géru, 1977; bowen, 1983).\n" +
                "Assim como outros prochilodontídios, o curimatã realiza migrações com fins reprodutivos e essa atividade esta sincronizada com o aumento do nível das águas na época das enchentes. As fêmeas liberam ovócitos e os machos esperma em grandes quantidades, os óvulos serão fecundados e em seguida eclodirão as larvas, essas serão levadas pela correnteza até as planícies alagadas onde encontram-se grande quantidade de algas e invertebrados aquáticos que servirão de alimentos não só pra os prochilodontídios, mas para outras especies de peixes(goulding, 1980; winemmiller e jepsen, 1998).\n" +
                "É um peixe apreciado na pesca esportiva por sua força e capacidade de luta após fisgado. No entanto, não sendo um peixe carnívoro não pode ser pescado com iscas artificiais.",
                "prochiloduslineatus", "Prochilodontidae", "Prochilodus", false, true, 38.0, indeterminado));

        peixesLista.add(new Peixes("Piavuçu e Piauçu", "Megaleporinus macrocephalus", "é uma espécie do género de peixes de água doce Megaleporinus , a família de anostómidos na ordem Caraciforme . Ele vive em águas quentes ou temperadas da América do Sul. comprimento totalcerca de 60 cm.",
                "megaleporinusmacrocephalus", "Anostomidae", "Leporinus", false, true, 38.0, indeterminado));

        peixesLista.add(new Peixes("Jurupensém", "Sorubim lima", "É um peixe de couro de corpo roliço. Apresenta uma lista clara irregular desde a cabeça até a nadadeira caudal. A cabeça é longa e achatada. A boca é arredondada, e o maxilar superior e maior que a mandíbula. Seus olhos se localizam lateralmente. Seu dorso passa do cor marrom escuro na parte dianteira, a amarelado e depois esbranquiçado abaixo da linha lateral. Suas nadadeiras são de avermelhadas a róseas.",
                "sorubimlima", "Pimelodidae", "Sorubim", false, true, 35.0, indeterminado));

        peixesLista.add(new Peixes("Armao, armado e abotoado", "Pterodoras granulosus", "Esse peixe Abotoado é um peixe de couro. Seu corpo é coberto por uma fileira de placas ósseas. Possui coloração cinza escura uniforme, cabeça estreita, focinho longo, boca inferior, olhos grandes e presença de barbilhões curtos. Sua boca inferior e sem dentes, bem como focinho longo servem para conseguir os alimentos.\n" +
                "\n" +
                "O padrão de cores deste peixe-gato varia de acordo com sua idade e com a localização de onde são originários. Estes peixes são geralmente uma cor marrom enlameada com alguns pontos mais escuros sobre o corpo e as barbatanas. À medida que esses peixes amadurecem, a mancha tende a desaparecer. Os espécimes juvenis tendem a não ser tão escuros quanto os peixes adultos.",
                "pterodorasgranulosus", "Doradidae", "Pterodoras", false, true, 35.0, indeterminado));

        peixesLista.add(new Peixes("Cascudo-abacaxi", "Megalancistrus aculeatus", "Apresenta corpo de cor escura com padrão reticulado. Espécies do gênero Pterygoplichthys podem ser identificados a partir dos números de raios da nadadeira dorsal. Acima de 10 raios indica ser um Pterygoplichthys, enquanto um número menor de raios indica espécies de outros gêneros, incluindo espécies do gênero Hypostomus que são mais susceptíveis de serem confundidos com Pterygoplichthys.\n" +
                "Possuem raios duros em suas nadadeiras peitorais e dorsal, servindo como defesa contra predadores. Uma forma albina foi produzida em cativeiro e comumente é vendida como Cascudo Albino ou Cascudo Chocolate.",
                "megalancistrusaculeatus", "Loricariidae", "Megalancistrus", false, true, 30.0, indeterminado));

        peixesLista.add(new Peixes("Cascudo e acari", "Hypostomus boulengeri", "", "hypostomusboulengeri", "Loricariidae", "Hypostomus", false, true, 30.0, indeterminado));

        peixesLista.add(new Peixes("Cascudo e acari", "Hypostomus cochliodon ", "é uma espécie de bagre nativo dasbaciasdo Paraguai e do médio rio Paraná no norte da Argentina, sul do Brasil e Paraguai. Inicialmente não foi encontrado na bacia do alto Paraná, acima das Cataratas do Guaíra , mas estas desapareceram após a construção da hidrelétrica de Itaipu, permitindo que esta espécie (e várias outras) se espalhasse. Ela cresce até um comprimento padrão de 23 cm.\n" +
                "A espécie é às vezes mantida em aquários.\n" +
                "Ao contrário da maioria dos outros Loricariidae, especula-se que este peixe é um especialista em comer madeira, juntamente com o material vegetal usual e algas.",
                "hypostomuscochliodon", "Loricariidae", "Hypostomus", false, true, 30.0, indeterminado));

        peixesLista.add(new Peixes("Cascudo e acari", "Hypostomus latifrons ", "", "hypostomuslatifrons", "Loricariidae", "Hypostomus", false, true, 30.0, indeterminado));

        peixesLista.add(new Peixes("Cascudo e acari", "Hypostomus latirostris", "", "hypostomuslatirostris", "Loricariidae", "Hypostomus", false, true, 30.0, indeterminado));

        peixesLista.add(new Peixes("Cascudo e acari", "Hypostomus piratatu", "", "hypostomuspiratatu", "Loricariidae", "Hypostomus", false, true, 30.0, indeterminado));

        peixesLista.add(new Peixes("Cascudo e acari", "Hypostomus regani", "", "hypostomusregani", "Loricariidae", "Hypostomus", false, true, 30.0, indeterminado));

        peixesLista.add(new Peixes("Corvina", "Plagioscion ternetzi", "", "plagioscionternetzi", "Sciaenidae", "Plagioscion", false, true, 30.0, indeterminado));

        peixesLista.add(new Peixes("Cascudo-preto", "Rhinelepis aspera", "", "rhinelepisaspera", "Loricariidae", "Rhinelepis", false, true, 25.0, indeterminado));

        peixesLista.add(new Peixes("Mandi e mandi-amarelo", "Pimelodus maculatus", "Esse peixe possui características que lhe garantem um bom encaixe em locais com diferentes condições climáticas, devido a sua grande variedade alimentar e comportamento oportunista, o que lhe permite explorar todos os níveis tróficos dos biogeocenoses aquáticos.[1] É bastante adorado na culinária e, por não exigir muitas técnicas, na pesca esportiva. Apresenta uma esperança de vida de 8 anos. Tem como espécie simpátrica a Pimelodus platicirris, que por apresentarem padrões morfológicos parecidos tem sido erroneamente confundidas, se diferenciando pelos padrões de coloração e alguns aspectos morfológicos como altura da nadadeira adiposa, comprimento e altura do corpo.", "pimelodusmaculatus", "Pimelodidae", "Pimelodus", false, true, 25.0, indeterminado));

        peixesLista.add(new Peixes("Piau", "Leporinus elongatus", "", "leporinuselongatus", "Anostomidae", "Leporinus", false, true, 25.0, indeterminado));

        peixesLista.add(new Peixes("Piau e Piau de Lagoa", "Leporinus lacustris", "A família Anostomidae é distribuída em grande parte tropical e subtropical da América do Sul do noroeste da Colômbia para o centro da Argentina. A maioria das espécies são moderadamente alongadgas e um forma tanto arredondada, embora existam algumas exceções. Algumas espécies de Anostomídeos tendem a nadar em um ângulo oblíquo “de cabeça para baixo”, que deu origem a eles a ser referido pelo termo em inglês ‘headstanders’.", "leporinuslacustris", "Anostomidae", "Leporinus", false, true, 25.0, indeterminado));

        peixesLista.add(new Peixes("Piau, Piau Açu, Piauçu e Piauvuçu", "Leporinus macrocephalus", "Espécie distribuída no Pantanal Mato-Grossense, bem como nos Estados de Minas Gerais, Goiás e São Paulo. O Piavuçu é um peixe que habita poços abaixo das corredeiras. Apresenta corpo curto e grosso, boca grande e terminal. Possui coloração cinza escura, principalmente devido à borda lateral escura das escamas. O peixe jovem pode apresentar barras transversais nos flancos. Já o peixe adulto apresenta 3 manchas escuras, alongadas verticalmente, sendo a mais posterior algumas vezes difusa. Se o Piavuçu for muito grande, ele não apresentará barras nem manchas.", "leporinusmacrocephalus", "Anostomidae", "Leporinus", false, true, 25.0, indeterminado));

        peixesLista.add(new Peixes("Piau verdadeiro", "Leporinus obtusidens", "Encontrado em toda Bacia do Prata na região sul e sudeste da América do Sul, no Brasil, Uruguai e Paraguai. Ocorrem em rios médios a grandes e realizam longas migrações para reprodução e alimentação em toda bacia do rio Paraná e do rio Paraguay. ", "leporinusobtusidens", "Anostomidae", "Leporinus", false, true, 25.0, indeterminado));

        peixesLista.add(new Peixes("Piau", "Leporinus octomaculatus", "", "leporinusoctomaculatus", "Anostomidae", "Leporinus", false, true, 25.0, indeterminado));

        peixesLista.add(new Peixes("Piau", "Leporinus striatus", "Encontrado no rio Oriçanga (SP), bacias do Paraná e Paraguai. Reportado no rio Uruguai no Brasil. Adultos são encontrados em águas de fluxo rápido. No Brasil é conhecido por sob uma grande variedade de nomes incluindo Canivete, Ferreirinha, Piau, Piauzinho, Piava, Riscadinho, Tanchina e Taxina.", "leporinusstriatus", "Anostomidae", "Leporinus", false, true, 25.0, indeterminado));

        peixesLista.add(new Peixes("Piau Três Pintas", "Leporinus spilopleura", "", "leporinusspilopleura", "Anostomidae", "Leporinus", false, true, 25.0, indeterminado));

        peixesLista.add(new Peixes("Piau Três Pintas", "Leporinus friderici", "Bastante ativo durante a estação chuvosa, invadem zonas inundadas onde frequentemente são capturados com redes, enquanto na estação seca, estão confinados nas partes mais profundas dos rios, onde são capturados com varas de pesca. Variando a região e populações, poderá apresentar diferenças em sua coloração. No Brasil é conhecido por sob uma grande variedade de nomes como Acaru Paca, Acaru Cabeça Gorda, Araçu, Piabam Piapara, Piau, Piau Cabeça Gorda, Piau Listrado, Piau Três Pintas, Piava e Uaracu.", "leporinusfriderici", "Anostomidae", "Leporinus", false, true, 25.0, indeterminado));

        peixesLista.add(new Peixes("Pacupeva", "Mylossoma paraguayensis", "", "foto", "Serrasalmidae", "Mylossoma", false, true, 20.0, indeterminado));

        peixesLista.add(new Peixes("Palmito", "Ageneiosus inermis", "", "ageneiosusinermis", "Auchenipteridae", "Ageneiosus", false, true, 35.0, indeterminado));

        peixesLista.add(new Peixes("Palmito", "Ageneiosus militaris", "", "ageneiosusmilitaris", "Auchenipteridae", "Ageneiosus", false, true, 35.0, indeterminado));

        peixesLista.add(new Peixes("Palmito", "Ageneiosus ucayalensis", "", "ageneiosusucayalensis", "Auchenipteridae", "Ageneiosus", false, true, 35.0, indeterminado));
    }

        class peixesArrayAdapter extends ArrayAdapter<Peixes> {
            private Context context;
            private List<Peixes> peixesLista;

            public peixesArrayAdapter(Context context, int resource, ArrayList<Peixes> objects) {
                super(context, resource, objects);

                this.context = context;
                this.peixesLista = objects;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                Peixes peixes = peixesLista.get(position);
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                View view = null;
                if (peixes.isExtincao() == false && peixes.isMinMax() == true) {
                    view = inflater.inflate(R.layout.peixes_layout_alt2, null);
                } else if (peixes.isExtincao() == false && peixes.isMinMax() == false) {
                    view = inflater.inflate(R.layout.peixes_layout, null);
                } else if (peixes.isExtincao() == true && peixes.isMinMax() == false) {
                    view = inflater.inflate(R.layout.peixes_layout_alt, null);
                }

                TextView nomePopular = (TextView) view.findViewById(R.id.nome);
                if (peixes.isExtincao() == false && peixes.isMinMax() == true) {
                    nomePopular.setText("Nome : " + peixes.getNomePopular() + ", Tamanho mínimo e máximo exigidos");
                } else if (peixes.isExtincao() == false && peixes.isMinMax() == false) {
                    if (peixes.getNomePopular().equals("")) {
                        nomePopular.setText("" + peixes.getNomePopular());
                    } else {
                        nomePopular.setText("Nome: " + peixes.getNomePopular());
                    }
                } else if (peixes.isExtincao() == true && peixes.isMinMax() == false) {
                    if (peixes.getNomePopular().equals("")) {
                        nomePopular.setText("" + peixes.getNomePopular() + "Proibida a captura");
                    } else {
                        nomePopular.setText("Nome: " + peixes.getNomePopular() + ", Proibida a captura");
                    }
                }

                TextView nomeCientifico = (TextView) view.findViewById(R.id.nomeCientifico);
                nomeCientifico.setText("Nome Científico: " + peixes.getNomeCientifico());
                TextView descricao = (TextView) view.findViewById(R.id.utilizacao);
                descricao.setText("Sobre o peixe: " + peixes.getDescricao());
                TextView familia = (TextView) view.findViewById(R.id.familia);
                familia.setText(peixes.getFamilia());
                TextView genero = (TextView) view.findViewById(R.id.genero);
                genero.setText(peixes.getGenero());
                ImageView image = (ImageView) view.findViewById(R.id.imageEqpt);

                familia.setText("Família: " + peixes.getFamilia());
                genero.setText("Gênero: " + peixes.getGenero());

                int tamanhoUtilizacao = peixes.getDescricao().length();
                if (tamanhoUtilizacao >= 100) {
                    String descriptionTrim = peixes.getDescricao().substring(0, 100) + "...";
                    descricao.setText(descriptionTrim);
                } else {
                    descricao.setText(peixes.getDescricao());
                }

                int imageID = context.getResources().getIdentifier(peixes.getImage(), "drawable", context.getPackageName());
                image.setImageResource(imageID);

                return view;
            }
        }
    }


