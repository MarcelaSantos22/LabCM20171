package co.edu.udea.compumovil.gr03_20171.lab3.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import co.edu.udea.compumovil.gr03_20171.lab3.EventsAdapter;
import co.edu.udea.compumovil.gr03_20171.lab3.R;
import co.edu.udea.compumovil.gr03_20171.lab3.VolleySingleton;
import data.DatabaseHelper;
import data.Events;

/**
 * Created by admin on 4/04/2017.
 */

public class EventsRecyclerFragment extends Fragment {
    private RecyclerView recyclerView;
    private EventsAdapter eAdapter;
    private DatabaseHelper DBHelper;
    private List<Events> evList;

     private String URL_BASE = "http://192.168.25.89:3000/api/events";
   // private final String URL_BASE = "http://192.168.1.51:3000/api/events";
     //private final String URL_BASE = "http:// 10.1.45.169:3000/api/events";

    private StringRequest stringRequest;
    private RequestQueue requestQueue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events_recycler, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DBHelper = new DatabaseHelper(getActivity());

        getEvents();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle("Lista de eventos");
    }

    public void getEvents() {
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        Log.d("Entre a login process", "loginProcess: ");

        stringRequest = new StringRequest(Request.Method.GET, URL_BASE,
                new Response.Listener<String>() {
                    //Log.d(nombre, "onBindViewHolder: ");
                    @Override
                    public void onResponse(String response) {

                        Log.i("LISTA", response);
                        parseJson(response);
                        // Log.d("ssi me dio", "siiii: ");
                    }

                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(String.valueOf(error), "onErrorResponse: ");
                        Log.d("errrroorrr", "Errorrrrrr: ");

                    }
                });
        VolleySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(stringRequest);

    }

    public void parseJson(String response) {
        JSONArray eventArray;
        evList = new ArrayList<>();
        try {
            eventArray = new JSONArray(new String(response));
            Log.i(String.valueOf(eventArray.length()), "parseJson: ");
            Log.i(String.valueOf(response.length()), "parseJson: ");

            for (int i = 0; i < eventArray.length(); i++) {

                Events event = new Events();
                JSONObject eventObject = eventArray.getJSONObject(i);

                if (contains(eventObject, "id")) {
                    event.id = eventObject.getInt("id");
                } else {
                    event.id = 0;
                }
                if (contains(eventObject, "nombre")) {
                    event.nombre = eventObject.getString("nombre");
                } else {
                    event.nombre = "NA";
                }
                if (contains(eventObject, "descripcion")) {
                    event.descripcion = eventObject.getString("descripcion");
                } else {
                    event.descripcion = "NA";
                }
                if (contains(eventObject, "puntuacion")) {
                    event.puntuacion = (float) eventObject.getDouble("puntuacion");
                } else {
                    event.puntuacion = 0;
                }
                if (contains(eventObject, "ubicacion")) {
                    event.ubicacion = eventObject.getString("ubicacion");
                } else {
                    event.ubicacion = "NA";
                }
                if (contains(eventObject, "fecha")) {
                    event.fecha = eventObject.getString("fecha");
                } else {
                    event.fecha = "NA";
                }
                if (contains(eventObject, "infoGeneral")) {
                    event.infoGeneral = eventObject.getString("infoGeneral");
                } else {
                    event.infoGeneral = "NA";
                }
                if (contains(eventObject, "responsable")) {
                    event.responsable = eventObject.getString("responsable");
                } else {
                    event.responsable = "NA";
                }

                evList.add(event);
            }
            eAdapter = new EventsAdapter(getActivity(), DBHelper,evList);
            recyclerView.setAdapter(eAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public boolean contains(JSONObject jsonObject, String key) {
        return jsonObject != null && jsonObject.has(key) && !jsonObject.isNull(key) ? true : false;
    }
}
