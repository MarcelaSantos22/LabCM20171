package co.edu.udea.compumovil.gr03_20171.lab1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class ContactInfo extends AppCompatActivity {
    //Inicializo variables
    AutoCompleteTextView autoCompleteTextView;
    //Inicializo variable de paises para el Autocomplete
    String[] paises={"Colombia ","Argentina","Mexico","Peru","Venezuela","Nicaragua"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        //Le cambio el titulo a la actividad
        this.setTitle("Información del contacto");
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.ac_country);

        //Creo el adaptador con los paises para el autocomplete
        ArrayAdapter adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,paises);
        //Adapto el adaptador lol
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setThreshold(2);

    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.contactInfoNextButton:
                Log.i("ContactInfo","Viajar a la ultima vista");
                break;
        }
    }
}
