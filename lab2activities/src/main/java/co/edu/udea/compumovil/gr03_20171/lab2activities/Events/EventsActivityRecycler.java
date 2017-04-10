package co.edu.udea.compumovil.gr03_20171.lab2activities.Events;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toolbar;

import co.edu.udea.compumovil.gr03_20171.lab2activities.AddEventsActivity;
import co.edu.udea.compumovil.gr03_20171.lab2activities.R;
import data.Events;


/**
 * Created by Michael Garcia on 18/03/2017.
 */

public class EventsActivityRecycler extends AppCompatActivity {
/*    static final int RESULT_OK = 1;  // The request code
    public static final int REQUEST_UPDATE_DELETE_LAWYER = 2;
    private static final String TAG = "MENSAJE";

    private RecyclerView mRecyclerView;
    private EventsAdapter1 mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private EventsDatabaseHelper eventsDBHelper;
    private EventsAdapter1 eventsAdapter;
    private Toolbar toolbar;
    private SearchView searchView;
    private TextView nameUser, email;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fab_events);

        mRecyclerView = (RecyclerView) findViewById(R.id.reciclador);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        eventsDBHelper = new EventsDatabaseHelper(this);

        mAdapter = new EventsAdapter1(this, eventsDBHelper);
        mRecyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new EventsAdapter1.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                    Log.i(TAG, "onItemClick position: " + position);
            }

            @Override
            public void onItemLongClick(int position, View v) {
                Log.i(TAG, "onItemLongClick pos = " + position);
            }
        });


        //BOTON FLOTANTE
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Snackbar.make(view, "Hello Snackbar", Snackbar.LENGTH_LONG).show();
                Intent in = new Intent(view.getContext(), AddEventsActivity.class);


                startActivityForResult(in, RESULT_OK);
            }

        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == RESULT_OK) {
            Events evento;
            Log.i("HOLI", "RESULT_OK");
            evento = new Events(data.getExtras().getString("nombre"), data.getExtras().getString("descripcion"),
                    data.getExtras().getString("puntuacion"), data.getExtras().getString("responsable"),
                    data.getExtras().getString("fecha"), data.getExtras().getString("ubicacion"),
                    data.getExtras().getString("infoGeneral"), data.getExtras().getString("foto"));
            Log.i("VALUES RETURN ", data.getExtras().getString("nombre"));

            Long prueba = eventsDBHelper.insertEvents(evento);
            mAdapter.notifyItemChanged(prueba.intValue());
            mAdapter.notifyDataSetChanged();
            Log.i("LONG DE PRUEBA", prueba.toString());
        }
    }*/


}