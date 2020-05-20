package com.example.parcialmovil.Buscar;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trackmatches {
    @SerializedName("track")
    @Expose
    private List<TrackFiltrar> track = null;

    public List<TrackFiltrar> getTrack() {
        return track;
    }

    public void setTrack(List<TrackFiltrar> track) {
        this.track = track;
    }
}
