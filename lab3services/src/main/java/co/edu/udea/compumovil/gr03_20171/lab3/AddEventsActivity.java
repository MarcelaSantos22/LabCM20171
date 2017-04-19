package co.edu.udea.compumovil.gr03_20171.lab3;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import co.edu.udea.compumovil.gr03_20171.lab3.Fragments.DateFragmentPicker;
import data.DatabaseHelper;
import data.Events;


/**
 * Created by admin on 20/03/2017.
 */

public class AddEventsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView nombre;
    private TextView descripcion;
    private TextView ubicacion;
    private RatingBar puntuacion;
    private TextView mDateDisplay;
    private TextView infoGeneral;

    private int mYear;
    private int mMonth;
    private int mDay;

    private DatabaseHelper db = new DatabaseHelper(this);
    private RequestQueue queue;
    private final String URL = "http://192.168.25.89:3000/api/events";
    //private final String URL = "http:// 10.1.45.169:3000/api/events";
   // private final String URL = "http://192.168.1.51:3000/api/events";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevents);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Crear Evento");

        nombre = (TextView) findViewById(R.id.etEventName);
        descripcion = (TextView) findViewById(R.id.etEventDescription);
        ubicacion = (TextView) findViewById(R.id.etEventUbicacion);
        puntuacion = (RatingBar) findViewById(R.id.rbEventPuntuacion);
        mDateDisplay = (TextView) findViewById(R.id.etEventDate);
        infoGeneral = (TextView) findViewById(R.id.etEventInfo);

    }

    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mYear = year;
        mMonth = monthOfYear;
        mDay = dayOfMonth;
        updateDisplay();
    }

    private void updateDisplay() {
        mDateDisplay.setText(new StringBuilder().append(mMonth + 1).append("/").append(mDay).append("/").append(mYear));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCrearEvento:
                Intent in = getIntent();
                String responsable = in.getStringExtra("usuario");

                Log.d(responsable, "Responsable: ");

                String nameEvent = nombre.getText().toString();
                String description = descripcion.getText().toString();
                String location = ubicacion.getText().toString();
                String date = mDateDisplay.getText().toString();
                String information = infoGeneral.getText().toString();
                Float punt = puntuacion.getRating();
                Log.d(String.valueOf(punt), "onClick: ");

                if ((nameEvent.equals("") || description.equals("") || punt.equals(0))) {
                    Toast.makeText(AddEventsActivity.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    addEvent(nameEvent,description,punt,location,responsable,date,information);
                    Events event = new Events();

                    event.setNombre(nameEvent);
                    event.setDescripcion(description);
                    event.setUbicacion(location);
                    event.setFecha(date);
                    event.setInfoGeneral(information);
                    event.setPuntuacion(punt);
                    event.setResponsable(responsable);

                    long resp = db.insertEvents(event);
                    Log.d("resp: ", String.valueOf(resp));
                }
                break;
            case R.id.btnFecha:
                DialogFragment datePickerFragment = new DateFragmentPicker();
                datePickerFragment.show(getFragmentManager(), "datePicker");
                break;
        }
    }

    public void addEvent(final String nombre, final String descripcion, final Float puntuacion, final String ubicacion, final String responsable, final String fecha, final String info) {
        queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(response, "onResponse: ");
                        Toast.makeText(AddEventsActivity.this, "Evento creado correctamente", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(AddEventsActivity.this, EventsActivity.class);
                        intent.putExtra("usuario", responsable);
                        intent.putExtra("fragment", "1");
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(AddEventsActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
                Log.d(error.toString(), "onErrorResponse: ");
                Toast.makeText(AddEventsActivity.this, "No se pudo crear el evento", Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("nombre", nombre);
                params.put("descripcion", descripcion);
                params.put("puntuacion", String.valueOf(puntuacion));
                params.put("ubicacion", ubicacion);
                params.put("responsable", responsable);
                params.put("fecha", fecha);
                params.put("infoGeneral", info);

                Log.d(String.valueOf(params), "params: ");

                return params;
            }
            @Override
            public RetryPolicy getRetryPolicy() {
                return new DefaultRetryPolicy(
                        5000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);
        Log.d(String.valueOf(postRequest), "postRequest: ");
    }

}
