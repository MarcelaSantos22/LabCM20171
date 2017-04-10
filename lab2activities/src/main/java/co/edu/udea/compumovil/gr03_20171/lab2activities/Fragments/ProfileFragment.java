package co.edu.udea.compumovil.gr03_20171.lab2activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import co.edu.udea.compumovil.gr03_20171.lab2activities.R;


/**
 * Created by admin on 15/03/2017.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private EditText name;
    private EditText age;
    private EditText email;
    private Button subirImg;

    private static final int SELECT_PICTURE = 100;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = (EditText)getActivity().findViewById(R.id.etShowName);
        age = (EditText)getActivity().findViewById(R.id.etShowAge);
        email = (EditText)getActivity().findViewById(R.id.etShowEmail);
        subirImg = (Button)getActivity().findViewById(R.id.btnSubirImagen);

        String nombre = getActivity().getIntent().getStringExtra("usuario");
        String edad = getActivity().getIntent().getStringExtra("edad");
        String correo = getActivity().getIntent().getStringExtra("email");

        name.setText(nombre);
        age.setText(edad);
        email.setText(correo);

        subirImg.setOnClickListener(this);

        getActivity().setTitle("Perfil");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.frag_profile,container,false);
    }

    @Override
    public void onClick(View v) {
        openImageChooser();
    }
    // Choose an image from Gallery
    void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }
}
