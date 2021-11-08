package com.example.inmobile.ui.contratos;

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

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.inmobile.R;
import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;

import java.util.List;

public class InmuebleConContratoAdapter extends RecyclerView.Adapter<InmuebleConContratoAdapter.ViewHolder> {

    List<Contrato> contratos;
    Context context;
    LayoutInflater inflater;


    public InmuebleConContratoAdapter (Context context, List<Contrato> contratos, LayoutInflater inflater) {
        this.context = context;
        this.contratos = contratos;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public InmuebleConContratoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_inmueble_con_contrato_fragment, parent, false);
        return new InmuebleConContratoAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvDireccion.setText(contratos.get(position).getInmueble().getDireccion());
        Log.d("salida ",  contratos.get(position).getInmueble().getDireccion());


        // String url= "http://192.168.0.7:45455";

        Glide.with(context)
                .load(contratos.get(position).getInmueble().getImagen())
                .placeholder(R.drawable.inmu2)
                //.diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivImagenInmueble);

    }

    @Override
    public int getItemCount() {return contratos.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDireccion;
        ImageView ivImagenInmueble;
        Button btContrato;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImagenInmueble = itemView.findViewById(R.id.ivImagenInmueble);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            btContrato = itemView.findViewById(R.id.btContrato);
            btContrato.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    Contrato contrato = contratos.get(getAdapterPosition());
                    //bundle.putInt("id", contrato.getIdContrato());
                    bundle.putSerializable("Contrato", contrato);
                    //bundle.putSerializable("id", contrato);
                    Navigation.findNavController((Activity) context, R.id.nav_host_fragment).navigate(R.id.contratoFragment, bundle);
                }
            });
        }
    }
}


