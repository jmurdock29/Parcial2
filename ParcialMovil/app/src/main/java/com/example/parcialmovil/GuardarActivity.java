package com.example.parcialmovil;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import com.example.parcialmovil.Buscar.Filtrar;
import com.example.parcialmovil.Buscar.Results;
import com.example.parcialmovil.Buscar.TrackFiltrar;
import com.example.parcialmovil.Buscar.TrackFiltrar;
import com.example.parcialmovil.Buscar.Trackmatches;
import com.google.gson.JsonSyntaxException;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GuardarActivity extends AppCompatActivity {
    private Button btBuscar;
    private EditText nombre, artista, album, buscar;
    List<TrackFiltrar> lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);
        nombre = (EditText)findViewById(R.id.idTitle);
        artista = (EditText)findViewById(R.id.idArtist);
        album = (EditText)findViewById(R.id.idAlbum);
        buscar = (EditText)findViewById(R.id.editBuscar);
        btBuscar = (Button) findViewById(R.id.buttonBuscar);
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Guardar();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu mmenu) {
        getMenuInflater().inflate(R.menu.menu_guardar, mmenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Guardar_Musica:
                complemnto();
                finish();
                return true;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void Guardar(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ws.audioscrobbler.com/").addConverterFactory(GsonConverterFactory.create()).build();
        LastApp fmApp = retrofit.create(LastApp.class); String aux = buscar.getText().toString();
        Call<Filtrar> call = fmApp.getFiltrar("track.search",aux,"b284db959637031077380e7e2c6f2775","json");
        try{
            call.enqueue(new Callback<Filtrar>() {
                @Override
                public void onResponse(Call<Filtrar> call, Response<Filtrar> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(getApplicationContext(),"Code: "+response.code(),Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Filtrar filtro = response.body(); Results resultados = filtro.getResults(); Trackmatches trackmatches = resultados.getTrackmatches();
                    lista = trackmatches.getTrack(); nombre.setText(lista.get(0).getName());artista.setText(lista.get(0).getArtist());
                    album.setText(lista.get(0).getAlbum());
                }
                @Override
                public void onFailure(Call<Filtrar> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"6ro7lem: "+ t.getMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }catch (IllegalStateException | JsonSyntaxException exception){

        }


    }

    private void complemnto(){
        EntLast.Track entCancion = new EntLast.Track();
        entCancion.name = nombre.getText().toString();
        entCancion.album = album.getText().toString();
        EntLast.Artista entArtista = new EntLast.Artista();
        entCancion.artista = entArtista;
        MainActivity.ListTemas.add(entCancion);
        entArtista.name = artista.getText().toString();
        MainActivity.artistList.add(entArtista);
    }
}
