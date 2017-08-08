package br.com.fiap.notas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;

import br.com.fiap.notas.entity.CloudantResponse;
import br.com.fiap.notas.entity.Row;
import br.com.fiap.notas.util.CloudantRequestInterface;
import br.com.fiap.notas.util.DataAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotasCardActivity extends AppCompatActivity {

    private ArrayList<Row> rows;
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notas_card);
        loadJson();
    }

    private void loadJson() {

        //cria uma url base para acesso a minha API no Cloudant
        //configura um conversor Gson para minha API,
        //constroi o objeto
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://matheuspedro1509.cloudant.com/fiap-noas/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Crio uma api com base na interface que retornara um CloudantResponse
        CloudantRequestInterface api = retrofit.create(CloudantRequestInterface.class);

        //enqueue retorna de forma assincrona um ArrayList<CloudantRow>
        //em onResponse quando sucesso e caso falha onFailure
        api.getAllJson().enqueue(new Callback<CloudantResponse>() {
            @Override
            public void onResponse(Call<CloudantResponse> call, Response<CloudantResponse> response) {

                //O JSON retornado será atribuido a classe CloudantResponse
                CloudantResponse cloudantResponse = response.body();

                rows= new ArrayList<>(Arrays.asList(cloudantResponse.getRows()));

                for(Row item: rows){
                    Log.i("Notas: ", item.getDoc().toString());
                }

                setaAdapter();
            }

            @Override
            public void onFailure(Call<CloudantResponse> call, Throwable t) {

                //log
                Log.e("Erro ", t.getMessage());
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        iniciarViews();
    }


    private void iniciarViews(){
        //Referencia a RecycleView que desenvolvemos na Activity notas_card.xml
        recyclerView= (RecyclerView) findViewById(R.id.card_recycler_notas);

        //Aumenta a perfomace mantendo um tamanho fixo
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadJson();
    }

    private void setaAdapter(){
        //Instancio um Adapter e passo com parametro o dataset retornado pelo Cloudant
        dataAdapter= new DataAdapter(rows);

        //Ligamos o recycle view com o adaptador que criamos
        recyclerView.setAdapter(dataAdapter);
    }

    public void voltar(View v){
        finish();
    }
}
