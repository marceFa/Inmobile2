package com.example.inmobile.ui.pagos;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.inmobile.R;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.modelo.Pago;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PagoAdapter  extends ArrayAdapter<Pago> {

    private List<Pago> pagos;
    private Context context;
    private LayoutInflater inflater;
    private TextView tvCodigo, tvNumero,tvCodigoContrato,tvImporte,tvFecha;


    public PagoAdapter(@NonNull Context context, int resource, @NonNull List<Pago> objects, LayoutInflater inflater) {
        super(context, resource, objects);
        this.context = context;
        this.pagos = objects;
        this.inflater = inflater;

    }

    @RequiresApi(api = VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View viewPago = convertView;
        if (viewPago == null) {
            viewPago = inflater.inflate(R.layout.item_pago_fragment, parent, false);
        }
        TextView tvCodigo = viewPago.findViewById(R.id.tvCodigo);
        TextView tvNumero = viewPago.findViewById(R.id.tvNumero);
        TextView tvCodigoContrato = viewPago.findViewById(R.id.tvCodigoContrato);
        TextView tvImporte = viewPago.findViewById(R.id.tvImporte);
        TextView tvFecha = viewPago.findViewById(R.id.tvFecha);

        tvCodigo.setText(pagos.get(position).getIdPago() + "");
        tvNumero.setText(pagos.get(position).getNumero() + "");
        //tvCodigoContrato.setText(pagos.get(position).getContrato().getIdContrato() + "");
        tvImporte.setText("$" + pagos.get(position).getImporte());

        DateTimeFormatter dt= DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDate fechaPago=LocalDate.parse(pagos.get(position).getFechaDePago(), dt);


        tvFecha.setText(fechaPago.toString());


        return viewPago;
    }
}
