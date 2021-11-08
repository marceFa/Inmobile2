package com.example.inmobile.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.modelo.Pago;
import com.example.inmobile.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Pago>> pagosMutable;
    private Context context;

    public PagoViewModel(@NonNull Application application) {
        super(application);
        context= application.getApplicationContext();
    }

    public LiveData<List<Pago>> getPagos () {
        if (pagosMutable == null) {
            pagosMutable = new MutableLiveData<>();
        }
        return pagosMutable;
    }

    //Aca traemos un inmueble de la Api que tenga un contrato vigente y sus pagos
    public void cargarPagos(int id) {

        Call<List<Pago>> pagosRecibidos = ApiClient.getMyApiClient().pagosPorcontrato(id,ApiClient.obtenerToken(context));
        pagosRecibidos.enqueue(new Callback<List<Pago>>() {
            @Override
            public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                if(response.isSuccessful()){
                    pagosMutable.postValue(response.body());
                    Log.d("Pago", response.body().get(1).getNumero()+" ");
                }else{
                    Toast.makeText(context, "Pagos no encontrados", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pago>>call, Throwable t) {
                Toast.makeText(context, "Ha ocurrido un error!!! :( ", Toast.LENGTH_LONG).show();

            }
        });

        /*Inmueble inmueble = (Inmueble)  bundle.getSerializable("inmueble");
        ApiClient apiClient= ApiClient.getApi();
        Contrato contratoVer =apiClient.obtenerContratoVigente(inmueble);
        ArrayList<Pago> pagos = apiClient.obtenerPagos(contratoVer);
        this.pagosMutable.setValue(pagos);*/

    }

}