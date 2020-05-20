package com.example.parcialmovil;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EntLast {

    public class Lista {
        @SerializedName("tracks")
        @Expose
        private Tracks tracks;
        public Tracks getTracks() {
            return tracks;
        }
        public void setTracks(Tracks tracks) {
            this.tracks = tracks;
        }
    }

    public static class Artista {

        @SerializedName("name")
        @Expose
        public String name;



        public Artista(){}
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }

    public static class Track {

        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("album")
        @Expose
        public String album;
        @SerializedName("artist")
        @Expose
        public Artista artista;

        public Track(){}

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAlbum() {
            return album;
        }

        public void setAlbum(String album) {
            this.album = album;
        }

        public Artista getArtist() {
            return artista;
        }

        public void setArtist(Artista artist) {
            this.artista = artist;
        }


    }

    public class Tracks {

        @SerializedName("track")
        @Expose
        private List<Track> track = null;

        public List<Track> getTrack() {
            return track;
        }

        public void setTrack(List<Track> track) {
            this.track = track;
        }


    }

}
