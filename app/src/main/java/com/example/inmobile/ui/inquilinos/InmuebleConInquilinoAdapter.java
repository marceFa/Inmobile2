package com.example.inmobile.ui.inquilinos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobile.R;
import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.modelo.Inquilino;

import java.util.List;

public class InmuebleConInquilinoAdapter extends RecyclerView.Adapter<InmuebleConInquilinoAdapter.ViewHolder> {

    List<Contrato> contratos;
    Context context;
    LayoutInflater inflater;

    public InmuebleConInquilinoAdapter(Context context, List<Contrato> contratos, LayoutInflater inflater) {
        this.context = context;
        this.contratos = contratos;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public InmuebleConInquilinoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_inquilino_fragment, parent, false);
        return new InmuebleConInquilinoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleConInquilinoAdapter.ViewHolder holder, int position) {
        holder.tvDireccion.setText(contratos.get(position).getInmueble().getDireccion());

        Glide.with(context)
                .load(contratos.get(position).getInmueble().getImagen())
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.inmu3)
                .into(holder.ivImagenInmueble);

    }

    @Override
    public int getItemCount() {
        return contratos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDireccion;
        ImageView ivImagenInmueble;
        Button btInquilino;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagenInmueble = itemView.findViewById(R.id.ivImagenInmueble);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            btInquilino = itemView.findViewById(R.id.btInquilino);
            btInquilino.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Contrato contrato= contratos.get(getAdapterPosition());
                    //bundle.putSerializable("Contrato", contrato);
                    bundle.putInt("id", contrato.getIdContrato());

                    Navigation.findNavController((Activity) context, R.id.nav_host_fragment).navigate(R.id.inquilinoFragment, bundle);
                }
            });
        }
    }

}

