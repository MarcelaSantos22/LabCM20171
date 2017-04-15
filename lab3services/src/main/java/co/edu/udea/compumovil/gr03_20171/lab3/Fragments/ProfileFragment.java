package co.edu.udea.compumovil.gr03_20171.lab3.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pkmmte.view.CircularImageView;

import co.edu.udea.compumovil.gr03_20171.lab3.R;


/**
 * Created by admin on 15/03/2017.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private TextView name;
    private TextView username;
    private TextView age;
    private TextView email;
    private Button subirImg;
    private CircularImageView imgProfile;

    private static final int SELECT_PICTURE = 100;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = (TextView)getActivity().findViewById(R.id.etShowName);
        username = (TextView)getActivity().findViewById(R.id.etShowUsername);
        age = (TextView)getActivity().findViewById(R.id.etShowAge);
        email = (TextView)getActivity().findViewById(R.id.etShowEmail);
        subirImg = (Button)getActivity().findViewById(R.id.btnSubirImagen);

        String nombre = getActivity().getIntent().getStringExtra("nombre");
        String usern = getActivity().getIntent().getStringExtra("usuario");
        String edad = getActivity().getIntent().getStringExtra("edad");
        String correo = getActivity().getIntent().getStringExtra("email");

        name.setText(nombre);
        username.setText(usern);
        age.setText(edad + " años");
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
