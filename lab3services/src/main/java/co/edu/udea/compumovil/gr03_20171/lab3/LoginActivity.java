package co.edu.udea.compumovil.gr03_20171.lab3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {
    private EditText usuario;
    private EditText contraseña;
    TextView textView;

    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;
    private final String URL = "http://192.168.25.89:3000/api/users";
   // private final String URL = "http://192.168.1.51:3000/api/users";
   //private final String URL = "http:// 10.1.45.169:3000/api/users";



    private String USERNAME = "username", PASSWORD = "password";

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.etUserLogin);
        contraseña = (EditText) findViewById(R.id.etPasswordLogin);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        textView = (TextView) findViewById(R.id.android_device_ip_address);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ipAddress = Formatter.formatIpAddress(wifiManager.getConnectionInfo().getIpAddress());
        textView.setText("Your Device IP Address: " + ipAddress);

    }

    public void onClickLogin(View v) {
        switch (v.getId()) {
            case R.id.btnIniciarSesion:
                Log.d("entre a login", "onClickLogin: ");
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
        Log.d("Entre a login process", "loginProcess: ");

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

                                String email = response.getString("email");

                                /*String edad = response.getString("edad");

                                String nombre = response.getString("nombre");*/

                                Intent intent = new Intent(LoginActivity.this, EventsActivity.class);
                                intent.putExtra("usuario", user);
                                intent.putExtra("email", email);
                               /* intent.putExtra("nombre", nombre);
                                intent.putExtra("edad", edad);

                                intent.putExtra("pass",password);*/

                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", "onResponse: ");

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
           loginProcess(user, pass);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

}
