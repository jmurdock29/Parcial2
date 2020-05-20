package com.example.parcialmovil;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    public static List<EntLast.Track> ListTemas = new ArrayList<>();
    public static List<EntLast.Artista> artistList = new ArrayList<>();

    private RecyclerView recyclerTrack;
    private LastAdapter fmAdapter;

    @Override
    protected void onStart() {
        super.onStart();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  GuardarActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  GuardarActivity.class);
                startActivity(intent);
            }
        });
        ConsultaLastFm();
    }


    private void ConsultaLastFm() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ws.audioscrobbler.com/").addConverterFactory(GsonConverterFactory.create()).build();
        LastApp fmApp = retrofit.create(LastApp.class);
        Call<EntLast.Lista> call = fmApp.getCancion();
        call.enqueue(new Callback<EntLast.Lista>() {
            @Override
            public void onResponse(Call<EntLast.Lista> call, Response<EntLast.Lista> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Code: "+response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                EntLast.Lista lista = response.body();EntLast.Tracks temas = lista.getTracks();ListTemas = temas.getTrack();
                for(EntLast.Track m: ListTemas){
                    EntLast.Artista artist = m.getArtist();artistList.add(artist);
                }
                recyclerTrack = (RecyclerView) findViewById(R.id.recyclerTrack);
                LinearLayoutManager m = new LinearLayoutManager(getApplicationContext());
                recyclerTrack.setLayoutManager(m);
                fmAdapter = new LastAdapter(ListTemas,artistList,MainActivity.this);
                recyclerTrack.setAdapter(fmAdapter);            }

            @Override
            public void onFailure(Call<EntLast.Lista> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"6ro7lem: "+ t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}
