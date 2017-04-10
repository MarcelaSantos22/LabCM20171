package co.edu.udea.compumovil.gr03_20171.lab1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;


public class ContactInfo extends AppCompatActivity {
    //Inicializo variables

    private EditText input_tel;
    private EditText input_email;
    private AutoCompleteTextView ac_country;
    private AutoCompleteTextView ac_city;
    private EditText input_address;


    //Inicializo variable de paises y ciudades para el Autocomplete
    String[] paises={"Colombia","Argentina","Mexico","Perú","Venezuela","Nicaragua","Bolivia","Brasil","Chile","Costa Rica","Cuba","Ecuador",
                        "El Salvador","Guayana Francesa","Granada","Guatemala","Guayana","Haití","Honduras","Jamaica","México","Paraguay","Panamá",
                        "Puerto Rico","República Dominicana","Surinam","Uruguay"};

    String[] ciudades={"Bogotá","Cali","Medellín","Barranquilla","Cartagena de Indias","Soledad","Cúcuta","Ibagué","Soacha","Bucaramanga","Santa Marta"
            ,"Villavicencio","Bello","Valledupar","Pereira","Buenaventura","Pasto","Manizales","Montería","Neiva"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        input_tel = (EditText)findViewById((R.id.input_tel));
        input_email = (EditText)findViewById(R.id.input_email);
        ac_country =(AutoCompleteTextView)findViewById(R.id.ac_country);
        ac_city = (AutoCompleteTextView)findViewById(R.id.ac_city);
        input_address = (EditText)findViewById(R.id.input_address);


        //Creo el adaptador con los paises y ciudades para el autocomplete
        ArrayAdapter adapPaises = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,paises);
        ArrayAdapter adapCiudades = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ciudades);

        //Adapto el adaptador lol
        ac_country.setAdapter(adapPaises);
        ac_country.setThreshold(3);

        ac_city.setAdapter(adapCiudades);
        ac_city.setThreshold(3);

    }
    public void clickNext(View v) {
        String datos = getIntent().getExtras().getString("DATOS");
        String tel = input_tel.getText().toString();
        String email = input_email.getText().toString();
        String direccion = input_address.getText().toString();
        String pais = ac_country.getText().toString();
        String ciudad = ac_city.getText().toString();

        datos = datos + "TELEFONO: "+ tel + "\n\n" + "DIRECCIÓN: " + direccion +"\n\n" + "CORREO: " + email +"\n\n"+
                            "PAÍS: "+ pais + "\n\n"+ "CIUDAD: "+ ciudad + "\n\n";

        Intent intent = new Intent(this, OtherInfo.class);
        intent.putExtra("DATOS",datos);
        startActivity(intent);
    }
}
