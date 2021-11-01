package com.example.inmobile.ui.inquilinos;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.inmobile.R;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;

import java.util.ArrayList;
import java.util.List;

public class InquilinosFragment extends Fragment {
    private InquilinosViewModel inquilinosViewModel;
    private RecyclerView rvInquilinos;
    private InmuebleConInquilinoAdapter adapter;
    Context context;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.inquilinos_fragment, container, false);
        context = root.getContext();
        inicializar(root);
        return root;
    }



    private void inicializar(View view) {
        inquilinosViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getActivity().getApplication()).create(InquilinosViewModel.class);
        rvInquilinos = view.findViewById(R.id.rvInquilinos);
        inquilinosViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                adapter = new InmuebleConInquilinoAdapter(context, contratos, getLayoutInflater());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, RecyclerView.VERTICAL, false);
                rvInquilinos.setAdapter(adapter);
                rvInquilinos.setLayoutManager(gridLayoutManager);

            }
        });
        inquilinosViewModel.cargarInmueblesConInquilino();
    }


}
