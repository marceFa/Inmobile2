package com.example.inmobile.ui.pagos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobile.R;
import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.modelo.Inquilino;
import com.example.inmobile.modelo.Pago;

import java.util.ArrayList;
import java.util.List;

public class InmuebleConPagosAdapter extends RecyclerView.Adapter<InmuebleConPagosAdapter.ViewHolder>{
    private Context context;
    private List<Contrato> contratos;
    private LayoutInflater inflater;



    public InmuebleConPagosAdapter(Context context, List<Contrato> contratos, LayoutInflater inflater) {
        this.context = context;
        this.contratos = contratos;
        this.inflater = inflater;
    }
    @NonNull
    @Override
    public InmuebleConPagosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_inmueble_con_pagos_fragment, parent, false);
        return new InmuebleConPagosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InmuebleConPagosAdapter.ViewHolder holder, int position) {
        holder.tvDireccion.setText(contratos.get(position).getInmueble().getDireccion());

        Glide.with(context)
                .load(contratos.get(position).getInmueble().getImagen())
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.inmu4)
                .into(holder.ivImagenInmueble);
    }

    @Override
    public int getItemCount() {
        return contratos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDireccion;
        ImageView ivImagenInmueble;
        Button btPagos;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagenInmueble = itemView.findViewById(R.id.ivImagenInmueble);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            btPagos = itemView.findViewById(R.id.btPagos);
            btPagos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Contrato contrato = contratos.get(getAdapterPosition());
                    //bundle.putSerializable("Contrato", contrato);
                    bundle.putInt("id", contrato.getIdContrato());
                    Navigation.findNavController((Activity) context, R.id.nav_host_fragment).navigate(R.id.pagoFragment, bundle);
                }
            });
        }
    }
}