package co.edu.udea.compumovil.gr03_20171.lab3.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pkmmte.view.CircularImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import co.edu.udea.compumovil.gr03_20171.lab3.EventsActivity;
import co.edu.udea.compumovil.gr03_20171.lab3.LoginActivity;
import co.edu.udea.compumovil.gr03_20171.lab3.R;
import co.edu.udea.compumovil.gr03_20171.lab3.VolleySingleton;


/**
 * Created by admin on 15/03/2017.
 */

public class ProfileFragment extends Fragment {
    private EditText name;
    private EditText username;
    private EditText age;
    private EditText email;
    private EditText password;
    private Button subirImg;
    private Button actualizar;
    private CircularImageView imgProfile;

    JsonObjectRequest jsonObjectRequest;
    RequestQueue requestQueue;
     private final String URL = "http://192.168.25.89:3000/api/users";
    //private final String URL = "http://192.168.1.51:3000/api/users";
    //private final String URL = "http:// 10.1.45.169:3000/api/users";


    private static final int SELECT_PICTURE = 100;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = (EditText) getActivity().findViewById(R.id.etShowName);
        username = (EditText) getActivity().findViewById(R.id.etShowUsername);
        age = (EditText) getActivity().findViewById(R.id.etShowAge);
        email = (EditText) getActivity().findViewById(R.id.etShowEmail);
        password = (EditText) getActivity().findViewById(R.id.etShowPassword);
        subirImg = (Button)getActivity().findViewById(R.id.btnSubirImagen);
        actualizar = (Button) getActivity().findViewById(R.id.btnActualizar);

        /*String nombre = getActivity().getIntent().getStringExtra("nombre");
        String edad = getActivity().getIntent().getStringExtra("edad");
        String correo = getActivity().getIntent().getStringExtra("email");
        String pass = getActivity().getIntent().getStringExtra("pass");*/
       // nameUser = (TextView)header.findViewById(R.id.tvNameUser);


        String usern = getActivity().getIntent().getStringExtra("usuario");

        getActivity().setTitle("Perfil");
        userInfoGet(usern);

        subirImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = name.getText().toString();
                String usern = username.getText().toString();
                String edad = age.getText().toString();
                edad = edad.substring(0,2);
                Log.i("edad",edad);


                String correo = email.getText().toString();
                String pass = password.getText().toString();

                userInfoPut(usern, edad, correo, pass, nombre);
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.frag_profile,container,false);
    }

    public void userInfoPut(final String user, final String age, final String email, final String pass, final String name) {
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.PUT, URL.concat("/").concat(user),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(response, "onResponseProfile: ");
                        Toast.makeText(getActivity(), "Actualización Correcta", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                }
                Log.d(error.toString(), "onErrorResponseProfile: ");
                Toast.makeText(getActivity(), "Error,no se pudo actualizar la información", Toast.LENGTH_SHORT).show();
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
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(postRequest);
        Log.d(String.valueOf(postRequest), "postRequest: ");
    }

    public void userInfoGet(final String user) {
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        Log.d("Entre a get process", "getProcess: ");

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                URL.concat("/").concat(user), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String pass = response.getString("password");
                            String edad = response.getString("edad");
                            String correo = response.getString("email");
                            String nombre = response.getString("nombre");

                            name.setText(nombre);
                            username.setText(user);
                            age.setText(edad + " años");
                            email.setText(correo);
                            password.setText(pass);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", "onResponse: ");

                        Toast.makeText(getActivity(), "Error al cargar la información", Toast.LENGTH_SHORT).show();
                    }
                });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
    // Choose an image from Gallery
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }
}
