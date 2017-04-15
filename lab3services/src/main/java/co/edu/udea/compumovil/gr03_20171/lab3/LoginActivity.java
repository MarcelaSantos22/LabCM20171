package co.edu.udea.compumovil.gr03_20171.lab3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private EditText usuario;
    private EditText contraseña;

    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;
    private final String URL = "http://192.168.1.51:3000/api/users"; //Cuando se trabaja con Servidor

    private String USERNAME = "username", PASSWORD = "password";

    //DatabaseHelper helper = new DatabaseHelper(this);
   // ArrayList<String> infoUs;
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
                loginProcess(user, pass);
                break;
            case R.id.tvRegistrar:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void loginProcess(final String user, final String pass) {
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL.concat("/").concat(user), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String password = response.getString("password");

                            if (!pass.equals(password)) {
                                Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                            } else {
                                editor = sharedPref.edit();
                                editor.putBoolean(getString(R.string.userlogged), true);
                                editor.putString(USERNAME, user);
                                editor.putString(PASSWORD, pass);
                                editor.apply();
                                String edad = response.getString("edad");
                                String email = response.getString("email");
                                String nombre = response.getString("nombre");

                                Log.d(String.valueOf(edad), "response: ");
                                Log.d(String.valueOf(email), "response: ");
                                Log.d(String.valueOf(nombre), "response: ");


                                Intent intent = new Intent(LoginActivity.this, EventsActivity.class);
                                intent.putExtra("usuario", user);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("edad", edad);
                                intent.putExtra("email", email);

                                startActivity(intent);
                            }
                            //Log.d(String.valueOf(user), "user: ");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String user, pass;
        Boolean isUserLogged = sharedPref.getBoolean(getString(R.string.userlogged), false);
        user = sharedPref.getString(USERNAME, "");
        pass = sharedPref.getString(PASSWORD, "");

        if (isUserLogged) {
            // Do something for the logged user
            loginProcess(user, pass);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}
