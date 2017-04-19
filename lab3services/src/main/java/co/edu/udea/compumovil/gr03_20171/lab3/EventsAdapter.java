package co.edu.udea.compumovil.gr03_20171.lab3;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.List;

import data.DatabaseHelper;
import data.Events;

/**
 * Created by admin on 4/04/2017.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    private List<Events> eventos;
    Intent intent;
    private Cursor items;
    private Context contexto;
    private static ClickListener clickListener;
    private DatabaseHelper db;
    //private String URL_BASE = "http://192.168.25.120:3000/api/events";
    //private static final String TAG = "EventAdapter";
    //private JsonObjectRequest jsonObjectRequest;
   // private RequestQueue requestQueue;
    Events event;


    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("HOLI", "pase por onCreateViewHolder");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_events, parent, false);
        return new EventsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventsViewHolder holder, int position) {
        event = eventos.get(position);
        Log.i(String.valueOf(position), "onBindViewHolder: ");
       // items.moveToPosition(position);

        //retrieved date may be null
        //Date movieReleaseDate = currentMovie.getReleaseDateTheater();
        Log.d("Entre a login process", "loginProcess: ");

//        String nombre = items.getString(items.getColumnIndex("evento"));
        Log.d(event.nombre, "onBindViewHolder: ");
        //holder.evento.setText(nombre);
        holder.evento.setText(event.nombre);

      //  String descripcion = items.getString(items.getColumnIndex("descripcion"));
        Log.d(event.descripcion, "onBindViewHolder: ");
        //holder.descripcion.setText(descripcion);
        holder.descripcion.setText(event.descripcion);

      //  Float punt = items.getFloat(items.getColumnIndex("puntuacion"));
        Log.d(String.valueOf(event.puntuacion), "onBindViewHolder: ");
        //holder.punt.setRating(punt);
        holder.punt.setRating(event.puntuacion);
    }

    @Override
    public int getItemCount() {
        items = db.getAllEvents();
        /*if (items != null) {
            return items.getCount();
        }*/
         if(eventos != null) {
            Log.i(String.valueOf(eventos.size()), "ENTRE AL GETITEMCOUNT");
            return eventos.size();
        }
        return 0;
    }

    public EventsAdapter(Context context, DatabaseHelper db, List<Events> eventos) {
        super();
        this.contexto = context;
        this.db = db;
        this.eventos = eventos;
        // Gestionar petición del archivo JSON
        // this.context = context;
    }


    private void mostraDetalle(Events ev, View view) {

       Log.i("ENTROO Descripcion ", "ENTROOOOOOOO ");
       // Log.i("ENTROO Descripcion ",aux.getString(items.getColumnIndex("descripcion")) );

        Intent intent = new Intent(view.getContext(), EventsDetail.class);
        Bundle dato = new Bundle();
       dato.putInt("id",ev.id);
        Log.d(String.valueOf(ev.id), "iddddd: ");

        dato.putString("nombre", ev.nombre );
        dato.putString("descripcion", ev.descripcion);
        dato.putFloat("puntuacion", ev.puntuacion);
        dato.putString("responsable", ev.responsable);
        dato.putString("fecha", ev.fecha);
        dato.putString("ubicacion", ev.ubicacion);
        dato.putString("infoGeneral",ev.infoGeneral);
        //dato.putString("foto", aux.getString(aux.getColumnIndex("foto")));
        intent.putExtras(dato);
        contexto.startActivity(intent);

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
        public RatingBar punt;
        public CardView cardView;

        public EventsViewHolder(View itemView) {
            super(itemView);
            Log.i("HOLI", "pase por el constructor de EventsViewHolder");

            evento = (TextView) itemView.findViewById(R.id.event_name);
            descripcion = (TextView) itemView.findViewById(R.id.event_description);
            punt = (RatingBar) itemView.findViewById(R.id.event_rating);
            // cardView = (CardView) itemView.findViewById(R.id.)

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //items.moveToPosition(getAdapterPosition());

            mostraDetalle(event, v);
//            clickListener.onItemClick(getAdapterPosition(), v);
            Log.i("ENTROO ", "onclick");

            /*event.get(getAdapterPosition());
            mostraDetalle(eventos, v);
            clickListener.onItemClick(getAdapterPosition(), v);
            Log.i("ENTROO ", "onclick");*/
        }

        @Override
        public boolean onLongClick(View v) {
            Log.i("ENTROO ", "ONLONGCLICK  ENTROOOO");
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }
}
