package co.edu.udea.compumovil.gr03_20171.lab1;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class PersonalInfo extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{



    private int mYear;
    private int mMonth;
    private int mDay;

    public RadioGroup rgSexo;
    public TextView mDateDisplay;
    public Spinner escolaridadSpinner;
    public EditText etName;
    public EditText etlastName;
    public RadioButton rbMale;
    public RadioButton rbFemale;
    public String datos;
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
        rgSexo = (RadioGroup) findViewById(R.id.rgSexo);
        datos = new String();
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
            case R.id.btCambiarFecha:
                DialogFragment datePickerFragment = new DateFragmentPicker();
                datePickerFragment.show(getFragmentManager(), "datePicker");
                break;
            case R.id.btnPersonalInfoNext:
                String nombre = etName.getText().toString();
                String apellido = etlastName.getText().toString();
                String fecha = mDateDisplay.getText().toString();
                String escolaridad = escolaridadSpinner.getSelectedItem().toString();
                String selectedType;
                if (rbMale.isChecked()) {
                    selectedType = rbMale.getText().toString();
                } else {
                    selectedType = rbFemale.getText().toString();
                }

                datos = datos + "NOMBRE COMPLETO: " + nombre + " " + apellido + "\n\n" + "FECHA DE NACIMIENTO: "+ fecha+ "\n\n"
                                    +"SEXO: "+ selectedType + "\n\n" + "ESCOLARIDAD: "+ escolaridad + "\n\n";

                Intent intent = new Intent(this, ContactInfo.class);
                intent.putExtra("DATOS", datos);
                startActivity(intent);
                break;

        }
    }

}
