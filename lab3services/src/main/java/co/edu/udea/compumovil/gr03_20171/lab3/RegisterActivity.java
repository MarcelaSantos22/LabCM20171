package co.edu.udea.compumovil.gr03_20171.lab3;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

import static android.Manifest.permission.INTERNET;

public class RegisterActivity extends AppCompatActivity {
    private EditText username;
    private EditText edad;
    private EditText contraseña;
    private EditText confirmPass;
    private EditText correo;
    private EditText nombre;

    private RequestQueue queue;
    private final String URL = "http://192.168.25.89:3000/api/users";
   //private final String URL = "http://192.168.1.51:3000/api/users";
   //private final String URL = "http:// 10.1.45.169:3000/api/users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.etUsernameRegister);
        edad = (EditText) findViewById(R.id.etDateRegister);
        contraseña = (EditText) findViewById(R.id.etPasswordRegister);
        confirmPass = (EditText) findViewById(R.id.etPasswordConfirmer);
        correo = (EditText) findViewById(R.id.etEmail);
        nombre = (EditText) findViewById(R.id.etUserRegister);
    }

    public void onClickRegister(View v) {
        switch (v.getId()) {
            case R.id.btnCrearCuenta:
                String user = username.getText().toString();
                String age = edad.getText().toString();
                String email = correo.getText().toString();
                String pass1 = contraseña.getText().toString();
                String pass2 = confirmPass.getText().toString();
                String name = nombre.getText().toString();

                if (!pass1.equals(pass2)) {
                    Toast pass = Toast.makeText(RegisterActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT);
                    pass.show();
                } else if ((email.length() == 0) || (!validateEmail(email))) {
                    correo.setError("Email required!");
                } else if (verificarPermisoInternet()) {
                    registerUser(user, age, email, pass1, name);
                }

                break;
            case R.id.btnCancelar:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void registerUser(final String user, final String age, final String email, final String pass, final String name) {
        //requestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(response, "onResponse: ");
                        Toast.makeText(RegisterActivity.this, "Cuenta Creada Correctamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
                Log.d(error.toString(), "onErrorResponse: ");
                Toast.makeText(RegisterActivity.this, "Error, el nombre de ususario ya existe " + error.toString(), Toast.LENGTH_SHORT).show();
                username.setText("");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Log.d("entreMAP", "getParams: ");
                Map<String, String> params = new HashMap<String, String>();

                params.put("username", user);
                params.put("password", pass);
                params.put("nombre", name);
                params.put("edad", age);
                params.put("email", email);

                Log.d(String.valueOf(params), "params: ");

                return params;
            }

            @Override
            public RetryPolicy getRetryPolicy() {
                return new DefaultRetryPolicy(
                        5000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            }
        };
        //requestQueue.add(postRequest);
        //VolleySingleton.getInstance(this).addToRequestQueue(postRequest);
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(postRequest);
        Log.d(String.valueOf(postRequest), "postRequest: ");
    }

    public final static boolean validateEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public boolean verificarPermisoInternet() {
        /* Comprobar que la versión del dispositivo si sea la que admite los permisos en tiempo de
        *  de ejecución, es decir, de la versión de Android 6.0 o superior porque para versiones
        *  anteriores basta con colocar el permiso el el archivo manifiesto
        * */
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        /* Aqui es donde comprobamos que los permisos ya hayan sido aceptdos por el username */
        if (checkSelfPermission(INTERNET) == PackageManager.PERMISSION_GRANTED)
            return true;

        return false; //Los permisos no han sido aceptados por el username
    }
}
