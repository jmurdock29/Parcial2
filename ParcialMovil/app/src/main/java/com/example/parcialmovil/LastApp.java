package com.example.parcialmovil;

import com.example.parcialmovil.Buscar.Filtrar;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastApp {
    @GET("2.0")
    Call<Filtrar> getFiltrar(@Query("method") String method, @Query("track") String track, @Query("api_key") String api_key,
                             @Query("format") String format);
    @GET("2.0/?method=chart.gettoptracks&api_key=b284db959637031077380e7e2c6f2775&format=json")
    Call<EntLast.Lista> getCancion();

}
