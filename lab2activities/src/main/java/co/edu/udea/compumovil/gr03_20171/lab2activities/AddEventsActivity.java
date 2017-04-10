package co.edu.udea.compumovil.gr03_20171.lab2activities;

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

import co.edu.udea.compumovil.gr03_20171.lab2activities.Fragments.DateFragmentPicker;
import data.DatabaseHelper;
import data.Events;


/**
 * Created by admin on 20/03/2017.
 */

public class AddEventsActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

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

    public void onClickAddEvent(View v) {
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

                if ((nameEvent.equals("") || description.equals("") || punt.equals(0))) {
                    Toast.makeText(AddEventsActivity.this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
                } else {
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

                    if (resp != -1) {
                        Toast.makeText(AddEventsActivity.this, "Evento creado correctamente", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(this, EventsActivity.class);
                        intent.putExtra("usuario",responsable);
                        intent.putExtra("fragment","1");
                        startActivity(intent);
                    } else {
                        Toast.makeText(AddEventsActivity.this, "No se pudo crear el evento", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnFecha:
                DialogFragment datePickerFragment = new DateFragmentPicker();
                datePickerFragment.show(getFragmentManager(), "datePicker");
                break;
        }
    }

}
