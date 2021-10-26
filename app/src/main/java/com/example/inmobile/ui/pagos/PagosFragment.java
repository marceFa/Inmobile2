package com.example.inmobile.ui.pagos;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
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
import com.example.inmobile.modelo.Pago;

import java.util.ArrayList;
import java.util.List;

public class PagosFragment extends Fragment {

    private PagosViewModel pagosViewModel;
    private RecyclerView rvInmuebles;
    private Context context;
    private InmuebleConPagosAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.pagos_fragment, container, false);
        context = root.getContext();
        inicializar(root);
        return root;
    }

    private void inicializar(View view) {
        pagosViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(PagosViewModel.class);
        rvInmuebles = view.findViewById(R.id.rvInmuebles);
        pagosViewModel.getInmuebles().observe(getViewLifecycleOwner(), new Observer<List<Contrato>>() {
            @Override
            public void onChanged(List<Contrato> contratos) {
                adapter = new InmuebleConPagosAdapter(context, contratos, getLayoutInflater());
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, RecyclerView.VERTICAL, false);

                rvInmuebles.setLayoutManager(gridLayoutManager);
                rvInmuebles.setAdapter(adapter);
            }
        });
        pagosViewModel.cargarInmueblesConPagos();
    }
}