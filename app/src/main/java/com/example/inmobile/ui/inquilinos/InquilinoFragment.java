package com.example.inmobile.ui.inquilinos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.inmobile.R;
import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inquilino;

public class InquilinoFragment extends Fragment {

    private InquilinoViewModel inquilinoViewModel;
    private TextView tvCodigo;
    private TextView tvNombre;
    private TextView tvApellido;
    private TextView tvDNI;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvGarante;
    private TextView tvTelefonoGarante;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        //int id =getArguments().getInt("id");

        View root = inflater.inflate(R.layout.inquilino_fragment, container, false);
        inicializar(root);
        return root;
}

    public void inicializar(View view) {
        tvCodigo = view.findViewById(R.id.tvCodigo);
        tvNombre = view.findViewById(R.id.tvNombre);
        tvApellido = view.findViewById(R.id.tvApellido);
        tvDNI = view.findViewById(R.id.tvDNI);
        tvTelefono = view.findViewById(R.id.tvTelefono);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvGarante = view.findViewById(R.id.tvGarante);
        tvTelefonoGarante = view.findViewById(R.id.tvTelefonoGarante);
        int id =getArguments().getInt("id");

        inquilinoViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(InquilinoViewModel.class);
        inquilinoViewModel.getInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino inquilino) {

                tvCodigo.setText(inquilino.getIdInquilino() + "");
                tvNombre.setText(inquilino.getNombre());
                tvApellido.setText(inquilino.getApellido());
                tvDNI.setText(inquilino.getdni() + "");
                tvTelefono.setText(inquilino.getTelefono() + "");
                tvEmail.setText(inquilino.getLugarDeTrabajo());
                tvGarante.setText(inquilino.getNombreGarante());
                tvTelefonoGarante.setText(inquilino.getTelefonoGarante() + "");

        }
    });
        inquilinoViewModel.cargarInquilino(id);



    }

}
