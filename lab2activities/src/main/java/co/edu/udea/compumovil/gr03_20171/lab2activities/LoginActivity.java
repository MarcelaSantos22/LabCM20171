package co.edu.udea.compumovil.gr03_20171.lab2activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import data.DatabaseHelper;
import data.Users;

public class LoginActivity extends AppCompatActivity {
    private EditText usuario;
    private EditText contraseña;
    private String USERNAME="username", PASSWORD="password";

    DatabaseHelper helper = new DatabaseHelper(this);
    ArrayList<String> infoUs;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.etUserLogin);
        contraseña = (EditText) findViewById(R.id.etPasswordLogin);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());


    }

    public void onClickLogin(View v) {
        switch (v.getId()) {
            case R.id.btnIniciarSesion:
                String user = usuario.getText().toString();
                String pass = contraseña.getText().toString();
                loginProcess(user,pass);
                break;
            case R.id.tvRegistrar:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void loginProcess(String user, String pass) {


        String password = helper.searchPass(user);

        if (!pass.equals(password)) {
            Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
        } else {
            editor = sharedPref.edit();

            infoUs = helper.searchInfoUser(user);
            editor.putBoolean(getString(R.string.userlogged), true);
            editor.putString(USERNAME, user);
            editor.putString(PASSWORD, pass);
            editor.apply();
            String edad = infoUs.get(0);
            String email = infoUs.get(1);

            Intent intent = new Intent(this, EventsActivity.class);
            intent.putExtra("usuario", user);
            intent.putExtra("edad", edad);
            intent.putExtra("email", email);

            startActivity(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String user,pass;
        Boolean isUserLogged = sharedPref.getBoolean(getString(R.string.userlogged),false);
        user=sharedPref.getString(USERNAME,"");
        pass=sharedPref.getString(PASSWORD,"");

        if(isUserLogged) {
            // Do something for the logged user
        loginProcess(user,pass);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}
