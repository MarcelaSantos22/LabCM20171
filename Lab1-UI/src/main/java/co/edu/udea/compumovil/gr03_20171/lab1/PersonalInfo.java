package co.edu.udea.compumovil.gr03_20171.lab1;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class PersonalInfo extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{



    private int mYear;
    private int mMonth;
    private int mDay;

    private TextView mDateDisplay;;
    private AutoCompleteTextView countryAutoComplete;
    private Spinner escolaridadSpinner;
    private EditText etName;
    private EditText etlastName;
    private RadioButton rbMale;
    private RadioButton rbFemale;
    private EditText etTelephone;
    private EditText etAddress;
    private EditText etEmail;
    private CheckBox cbFavorite;

    public PersonalInfo() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_personal_info);

        mDateDisplay = (TextView) findViewById(R.id.etFecNacimiento);
        etName=(EditText) findViewById(R.id.etNombre);
        etlastName=(EditText)findViewById(R.id.etApellido);
        rbMale=(RadioButton)findViewById(R.id.rbHombre);
        rbFemale=(RadioButton)findViewById(R.id.rbMujer);
        escolaridadSpinner=(Spinner)findViewById(R.id.spnEscolaridad);

    }
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btCambiarFecha:
                DialogFragment datePickerFragment = new DateFragmentPicker();
                datePickerFragment.show(getFragmentManager(), "datePicker");
                break;
            case R.id.btnNext:

              /*  Log.i("PersonalInfo","Mi nombre es: "+etName.getText().toString());
                Log.i("PersonalInfo","Mi apellido es: "+etlastName.getText().toString());
                Log.i("PersonalInfo","Mi fecha de nacimiento es: "+ mDateDisplay.getText().toString());*/
                //Inicializo las variables en el objeto estatico persona que se encuentra en el MainActivity.java
                MainActivity.persona.setNombre(etName.getText().toString());
                MainActivity.persona.setApellido(etlastName.getText().toString());
                MainActivity.persona.setFechaDeNacimiento(mDateDisplay.getText().toString());
                if(rbMale.isChecked()){
                    MainActivity.persona.setSexo(getResources().getString(R.string.boy));
                }else{
                    MainActivity.persona.setSexo(getResources().getString(R.string.girl));
                }
                MainActivity.persona.setEscolaridad(escolaridadSpinner.getSelectedItem().toString());
               //Va a la nueva actividad.
                Intent intent = new Intent(PersonalInfo.this, ContactInfo.class);
                startActivity(intent);
                break;

        }
    }
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mYear = year;
        mMonth = monthOfYear;
        mDay = dayOfMonth;
        updateDisplay();
    }
    private void updateDisplay() {

        mDateDisplay.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(mMonth + 1).append("/").append(mDay).append("/")
                .append(mYear));
    }

}
