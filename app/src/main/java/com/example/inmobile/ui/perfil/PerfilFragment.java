package com.example.inmobile.ui.perfil;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.R;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;
    private EditText etId,etDNI,etNombre,etApellido,etEmail,etClave,etTelefono;
    private Button btEditar,btGuardar;
    Propietario propietario;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel = ViewModelProviders.of(this).get(PerfilViewModel.class);

        View vistaPerfil= inflater.inflate(R.layout.perfil_fragment, container, false);
        inicializar(vistaPerfil);



        //observer del mutable Propietario
        perfilViewModel.getPropietarioMutable().observe(getViewLifecycleOwner(), new Observer<Propietario>() {



            @Override
            public void onChanged(Propietario propietario) {

                etId.setText(propietario.getId()+"");
                etDNI.setText(propietario.getDni());
                etNombre.setText(propietario.getNombre());
                etApellido.setText(propietario.getApellido());
                etEmail.setText(propietario.getEmail());
                etTelefono.setText(propietario.getTelefono());
                etClave.setText(propietario.getClave());

            }
        });
       perfilViewModel.cargarPerfil();
       return vistaPerfil;
    }

    private void habilitarEditex(){
        etDNI.setEnabled(true);
        etNombre.setEnabled(true);
        etApellido.setEnabled(true);
        etEmail.setEnabled(true);
        etClave.setEnabled(true);
        etTelefono.setEnabled(true);

    }
    private void deshabilitarEditex() {
        etDNI.setEnabled(false);
        etNombre.setEnabled(false);
        etApellido.setEnabled(false);
        etEmail.setEnabled(false);
        etClave.setEnabled(false);
        etTelefono.setEnabled(false);
    }

    private void inicializar(View vistaPerfil) {
        etId = vistaPerfil.findViewById(R.id.etId);
        etDNI = vistaPerfil.findViewById(R.id.tvDNI);
        etNombre = vistaPerfil.findViewById(R.id.tvNombre);
        etApellido = vistaPerfil.findViewById(R.id.tvApellido);
        etEmail = vistaPerfil.findViewById(R.id.tvEmail);
        etClave = vistaPerfil.findViewById(R.id.etClave);
        etTelefono = vistaPerfil.findViewById(R.id.tvTelefono);
        btEditar = vistaPerfil.findViewById(R.id.btEditar);
        btGuardar= vistaPerfil.findViewById(R.id.btGuardar);

        //editar habilita la edicion de editex
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btEditar.setVisibility(View.INVISIBLE);
                btGuardar.setVisibility(View.VISIBLE);
                habilitarEditex();

            }
        });

        //guardar deshabilita la edicion de editex
        btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btGuardar.setVisibility(View.INVISIBLE);
                btEditar.setVisibility(View.VISIBLE);
                Propietario propietario = new Propietario();
                propietario.setId(Integer.parseInt(etId.getText().toString()));
                Log.d("idperfil","id="+propietario.getId());
                propietario.setDni(etDNI.getText().toString());
                propietario.setNombre(etNombre.getText().toString());
                propietario.setApellido(etApellido.getText().toString());
                propietario.setEmail(etEmail.getText().toString());
                propietario.setClve(etClave.getText().toString());
                propietario.setTelefono(etTelefono.getText().toString());
                perfilViewModel.actualizarPerfil(propietario);
                deshabilitarEditex();
            }
        });
    }
}






















