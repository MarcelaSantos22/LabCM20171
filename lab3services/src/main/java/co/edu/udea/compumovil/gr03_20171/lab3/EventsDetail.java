package co.edu.udea.compumovil.gr03_20171.lab3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

import data.DatabaseHelper;


public class EventsDetail extends AppCompatActivity {
    private TextView nombre;
    private TextView descripcion;
    private TextView ubicacion;
    private RatingBar puntuacion;
    private TextView fecha;
    private TextView infoGeneral;
    private TextView responsable;
    private TextView foto;
    private String id;
    private String respons;

    private DatabaseHelper db;
    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;
    // private final String URL = "http://192.168.25.120:3000/api/events"; //Cuando se trabaja con Servidor
    private final String URL = "http://192.168.1.51:3000/api/events"; //Cuando se trabaja con Servidor



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_events);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nombre = (TextView) findViewById(R.id.tvNameEvent);
        descripcion = (TextView)findViewById(R.id.tvDescription);
        puntuacion = (RatingBar) findViewById(R.id.erPunt);
        fecha = (TextView) findViewById(R.id.tvDate);
        ubicacion = (TextView)findViewById(R.id.tvLocation);
        infoGeneral = (TextView) findViewById(R.id.tvInfoGeneral);
        responsable = (TextView) findViewById(R.id.tvUser);

        Bundle dato = getIntent().getExtras();
        nombre.setText(dato.getString("nombre"));
        descripcion.setText(dato.getString("descripcion"));
        puntuacion.setRating(dato.getFloat("puntuacion"));
        ubicacion.setText(dato.getString("ubicacion"));
        fecha.setText(dato.getString("fecha"));
        infoGeneral.setText(dato.getString("infoGeneral"));
        responsable.setText(dato.getString("responsable"));

        id = String.valueOf(dato.getInt("id"));
        respons = dato.getString("responsable");
        Log.i(id, "idEvent: ");

        getSupportActionBar().setTitle(dato.getString("nombre"));

    }

    public void borrarEvento(View view){
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Log.d("Entre a delete process", "borrar: ");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE,
                URL.concat("/").concat(id), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        // JSONArray jsonArray  = new JSONArray(response.);
                        //response.toJSONArray(jsonArray);

                        Log.d(String.valueOf(response), "jsonArray: ");

                        Log.d("contraseña", "onResponse: ");
                        Toast.makeText(EventsDetail.this, "Evento Eliminado", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(EventsDetail.this, EventsActivity.class);
                        intent.putExtra("usuario", respons);
                        intent.putExtra("fragment", "1");
                        startActivity(intent);
                    }
                    //Log.d(String.valueOf(user), "user: ");
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", "onResponse: ");
                        Toast.makeText(EventsDetail.this, "No se pudo eliminar el evento", Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

    }
}
