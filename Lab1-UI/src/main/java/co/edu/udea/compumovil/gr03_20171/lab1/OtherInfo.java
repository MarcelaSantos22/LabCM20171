package co.edu.udea.compumovil.gr03_20171.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;


public class OtherInfo extends AppCompatActivity implements View.OnClickListener{

    private Button btnMostrar;
    private CheckBox cbLeer;
    private CheckBox cbTV;
    private CheckBox cbBailar;
    private CheckBox cbCantar;
    private CheckBox cbNadar;

    private RatingBar rbLeer;
    private RatingBar rbTV;
    private RatingBar rbBailar;
    private RatingBar rbCantar;
    private RatingBar rbNadar;
    String ratedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info);

        btnMostrar = (Button) findViewById(R.id.btnMostrar);

        rbLeer = (RatingBar) findViewById(R.id.rbLeer);
        rbTV = (RatingBar) findViewById(R.id.rbTV);
        rbBailar = (RatingBar) findViewById(R.id.rbBailar);
        rbCantar = (RatingBar) findViewById(R.id.rbCantar);
        rbNadar = (RatingBar) findViewById(R.id.rbNadar);

        cbLeer = (CheckBox) findViewById(R.id.cbLeer);
        cbTV = (CheckBox) findViewById(R.id.cbTV);
        cbBailar = (CheckBox) findViewById(R.id.cbBailar);
        cbCantar = (CheckBox) findViewById(R.id.cbCantar);
        cbNadar = (CheckBox) findViewById(R.id.cbNadar);

        cbLeer.setOnClickListener(this);
        cbTV.setOnClickListener(this);
        cbBailar.setOnClickListener(this);
        cbCantar.setOnClickListener(this);
        cbNadar.setOnClickListener(this);


        btnMostrar.setOnClickListener(new View.OnClickListener(){
            String pasatiempo ="";
            public void onClick(View view){
                if(cbLeer.isChecked()){
                    pasatiempo = pasatiempo + cbLeer.getText().toString()+ " " +rbLeer.getRating() + " estrellas \n";
                }
                if(cbTV.isChecked()){
                    pasatiempo = pasatiempo + cbTV.getText().toString() +  " " +rbTV.getRating() + " estrellas \n";
                }
                if(cbBailar.isChecked()){
                    pasatiempo = pasatiempo + cbBailar.getText().toString()+  " " +rbBailar.getRating() + " estrellas \n";
                }
                if(cbCantar.isChecked()){
                    pasatiempo = pasatiempo + cbCantar.getText().toString()+  " " +rbCantar.getRating() + " estrellas \n";
                }
                if(cbNadar.isChecked()){
                    pasatiempo = pasatiempo + cbNadar.getText().toString()+  " " +rbNadar.getRating() + " estrellas \n";
                }

                String datos = getIntent().getExtras().getString("DATOS");
                datos = datos + "PASATIEMPOS: \n"+ pasatiempo + "\n";

                Intent intent = new Intent(OtherInfo.this, MostrarInfo.class);
                intent.putExtra("DATOS",datos);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.cbLeer:
                if(cbLeer.isChecked()){
                    rbLeer.setIsIndicator(false);
                }else {
                    rbLeer.setIsIndicator(true);
                    rbLeer.setRating(0);
                }
                break;
            case R.id.cbTV:
                if(cbTV.isChecked()){
                    rbTV.setIsIndicator(false);
                }else {
                    rbTV.setIsIndicator(true);
                    rbTV.setRating(0);
                }
                break;
            case R.id.cbBailar:
                if(cbBailar.isChecked()){
                    rbBailar.setIsIndicator(false);
                }else {
                    rbBailar.setIsIndicator(true);
                    rbBailar.setRating(0);
                }
                break;
            case R.id.cbCantar:
                if(cbCantar.isChecked()){
                    rbCantar.setIsIndicator(false);
                }else {
                    rbCantar.setIsIndicator(true);
                    rbCantar.setRating(0);
                }
                break;
            case R.id.cbNadar:
                if(cbNadar.isChecked()){
                    rbNadar.setIsIndicator(false);
                }else {
                    rbNadar.setIsIndicator(true);
                    rbNadar.setRating(0);
                }
                break;
        }

    }
}

