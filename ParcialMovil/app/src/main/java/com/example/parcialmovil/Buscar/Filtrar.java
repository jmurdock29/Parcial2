package com.example.parcialmovil.Buscar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Filtrar {
    @SerializedName("results")
    @Expose
    private Results results;

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
