package co.edu.udea.compumovil.gr03_20171.lab3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import co.edu.udea.compumovil.gr03_20171.lab3.R;


/**
 * Created by Michael Garcia on 19/03/2017.
 */

public class EventsDetail extends AppCompatActivity {
    private TextView nombre;
    private TextView descripcion;
    private TextView ubicacion;
    private RatingBar puntuacion;
    private TextView fecha;
    private TextView infoGeneral;
    private TextView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_events);

        nombre = (TextView) findViewById(R.id.tvNameEvent);
        descripcion = (TextView)findViewById(R.id.tvDescription);
        puntuacion = (RatingBar) findViewById(R.id.erPunt);
        fecha = (TextView) findViewById(R.id.tvDate);
        ubicacion = (TextView)findViewById(R.id.tvLocation);
        infoGeneral = (TextView) findViewById(R.id.tvInfoGeneral);

        Bundle dato = getIntent().getExtras();
        nombre.setText(dato.getString("nombre"));
        descripcion.setText(dato.getString("descripcion"));
        puntuacion.setRating(dato.getFloat("puntuacion"));
    }

    public void borrarEvento(View view){

    }


}
