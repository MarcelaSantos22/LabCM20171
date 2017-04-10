package co.edu.udea.compumovil.gr03_20171.lab1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MostrarInfo extends AppCompatActivity {


    private TextView tvInformacion;
    private TextView tvSex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_info);

        tvInformacion=(TextView)findViewById(R.id.tvInfoCompleta);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle!=null){
            String datos= bundle.getString("DATOS");
            tvInformacion.setText(datos);
        }
    }
}
