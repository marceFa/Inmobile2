package com.example.inmobile.ui.contratos;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobile.R;
import com.example.inmobile.modelo.Contrato;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ContratoFragment extends Fragment {

    private ContratoViewModel contratoViewModel;
    private TextView tvCodigoContrato;
    private TextView tvFechaInicio;
    private TextView tvFechaFin;
    private TextView tvMontoAlquiler;
    private TextView tvInquilino;
    private TextView tvInmueble;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.contrato_fragment, container, false);
        context = root.getContext();
        inicializar(root);

        return root;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void inicializar(View view) {
        tvCodigoContrato = view.findViewById(R.id.tvCodigoContrato);
        tvFechaInicio = view.findViewById(R.id.tvFechaInicio);
        tvFechaFin = view.findViewById(R.id.tvFechaFin);
        tvMontoAlquiler = view.findViewById(R.id.tvMontoAqluiler);
        tvInquilino = view.findViewById(R.id.tvInquilino);
        tvInmueble = view.findViewById(R.id.tvInmueble);

        contratoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(ContratoViewModel.class);
        contratoViewModel.getContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {


            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onChanged(Contrato contrato) {

                tvCodigoContrato.setText(contrato.getIdContrato() + " ");
                //SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

                //DateTimeFormatter dt= DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                LocalDateTime ld=LocalDateTime.parse(contrato.getFechaInicio());
                LocalDate fff = ld.toLocalDate();
                tvFechaInicio.setText(fff.toString());



                LocalDateTime ld2=LocalDateTime.parse(contrato.getFechaFin());
                LocalDate fff2 = ld2.toLocalDate();
                tvFechaFin.setText(fff2.toString());

                tvMontoAlquiler.setText("$" + contrato.getMontoAlquiler());
                //tvInquilino.setText(contrato.getInquilino().getNombre() + " " + contrato.getInquilino().getApellido());
                tvInmueble.setText("Domicilio:  " + contrato.getInmueble().getDireccion());



            }

    });
        contratoViewModel.cargarContrato(getArguments());





}



}
