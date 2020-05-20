package com.example.parcialmovil;




        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import java.util.List;


public class LastAdapter extends RecyclerView.Adapter<LastAdapter.MusicViewHolder> {
    public List<EntLast.Track> lisTracks;
    public List<EntLast.Artista> listArtista;
    private LayoutInflater layoutInflater;

    public LastAdapter(List<EntLast.Track> lisTracks, List<EntLast.Artista> listArtista, Context context) {
        this.lisTracks = lisTracks;
        this.listArtista = listArtista;
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.activity_catalog,null);
        return new MusicViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        EntLast.Track track = lisTracks.get(position);
        EntLast.Artista artista = listArtista.get(position);
        holder.nombre.setText(track.name);
        holder.artist.setText(artista.name);
    }

    @Override
    public int getItemCount() {
        return lisTracks.size();

    }

    public class MusicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView nombre;
        private TextView artist;
        private LastAdapter cancionAdapter;
        public MusicViewHolder(@NonNull View itemView, LastAdapter cancionAdapter) {
            super(itemView);
            nombre = itemView.findViewById(R.id.idNombre);
            artist = itemView.findViewById(R.id.idArtista);
            this.cancionAdapter = cancionAdapter;
        }

        @Override
        public void onClick(View v) {

        }
    }

}

