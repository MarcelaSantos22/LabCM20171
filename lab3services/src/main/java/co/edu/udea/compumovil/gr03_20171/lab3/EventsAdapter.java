package co.edu.udea.compumovil.gr03_20171.lab3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import data.DatabaseHelper;
import data.Events;

/**
 * Created by admin on 4/04/2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    private final Context context;
    private List<Events> eventos;
    Intent intent;
    // private Cursor items;
    private static ClickListener clickListener;
    private DatabaseHelper db;
    private String URL_BASE = "http://localhost:3000/explorer/#/event";
    private static final String TAG = "EventAdapter";

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("HOLI", "pase por onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_events, parent, false);
        return new EventsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        Events event = eventos.get(position);
        //one or more fields of the Movie object may be null since they are fetched from the web
        //holder.movieTitle.setText(currentMovie.getTitle());

        //retrieved date may be null
        //Date movieReleaseDate = currentMovie.getReleaseDateTheater();

        Log.i("HOLI", "pase por bind view holder");
        String nombre = event.getNombre();
        holder.evento.setText(nombre);

        String descripcion = event.getDescripcion();
        holder.descripcion.setText(descripcion);

        float punt = event.getPuntuacion();
        holder.puntuacion.setRating(punt);
    }

    @Override
    public int getItemCount() {

        // items = db.getAllEvents();
        Log.i("HOLI", "pase por el GETITEMCOUNT");
       /* if (items != null) {
            Log.i("HOLI", "ENTRE AL GETITEMCOUNT");
            return items.getCount();
        }*/
        if (eventos != null) {
            Log.i("HOLI", "ENTRE AL GETITEMCOUNT");
            return eventos.size();
        }
        return 0;
    }

    public EventsAdapter(Context context) {
        super();
        this.context = context;
        Log.i("HOLI", "pase por el constructor");
        // Gestionar petición del archivo JSON
    }


    private void mostraDetalle(List aux, View view) {
/*
        Log.i("ENTROO Descripcion ", "ENTROOOOOOOO ");
        Log.i("ENTROO Descripcion ",aux.getString(items.getColumnIndex("descripcion")) );

        Intent intent = new Intent(view.getContext(), EventsDetail.class);
        Bundle dato = new Bundle();
        dato.putString("nombre", aux.getString(aux.getColumnIndex("evento")));
        dato.putString("descripcion", aux.getString(aux.getColumnIndex("descripcion")));
        dato.putString("puntuacion", aux.getString(aux.getColumnIndex(EventsContract.EventsEntry.PUNTUACION)));
        dato.putString("responsable", aux.getString(aux.getColumnIndex(EventsContract.EventsEntry.RESPONSABLE)));
        dato.putString("fecha", aux.getString(aux.getColumnIndex(EventsContract.EventsEntry.FECHA)));
        dato.putString("ubicacion", aux.getString(aux.getColumnIndex(EventsContract.EventsEntry.UBICACION)));
        dato.putString("infoGeneral", aux.getString(aux.getColumnIndex(EventsContract.EventsEntry.INFOGENERAL)));
        dato.putString("foto", aux.getString(aux.getColumnIndex(EventsContract.EventsEntry.FOTO)));
        intent.putExtras(dato);
        contexto.startActivity(intent);
*/
    }

    public interface ClickListener {
        void onItemClick(int position, View v);

        void onItemLongClick(int position, View v);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        Log.i("ENTROO ", "entro a setitemclick en EventsAdapter1");
        EventsAdapter.clickListener = clickListener;
    }

    public class EventsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView evento;
        public TextView descripcion;
        public RatingBar puntuacion;

        public EventsViewHolder(View itemView) {
            super(itemView);
            Log.i("HOLI", "pase por el constructor de EventsViewHolder");

            evento = (TextView) itemView.findViewById(R.id.event_name);
            descripcion = (TextView) itemView.findViewById(R.id.event_description);
            puntuacion = (RatingBar) itemView.findViewById(R.id.rbEventPuntuacion);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //items.moveToPosition(getAdapterPosition());
            eventos.get(getAdapterPosition());
            mostraDetalle(eventos, v);
            clickListener.onItemClick(getAdapterPosition(), v);
            Log.i("ENTROO ", "onclick");
        }

        @Override
        public boolean onLongClick(View v) {
            Log.i("ENTROO ", "ONLONGCLICK  ENTROOOO");
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }
}
