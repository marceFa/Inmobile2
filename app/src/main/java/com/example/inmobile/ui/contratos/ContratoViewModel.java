package com.example.inmobile.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {

    private MutableLiveData<Contrato> contratoMutable;
    private Context context;

    public ContratoViewModel(@NonNull Application application) {

        super(application);
        context= application.getApplicationContext();
    }

    public LiveData<Contrato> getContrato() {
        if (contratoMutable == null) {
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;

    }

    //aca recibimos un inmueble y se busca el contrato vigente
    public void cargarContrato(int id) {
        Log.d("idContrato","id:"+id);
        Call<Contrato> contratoSeleccionado = ApiClient.getMyApiClient().obtenerContratoPorId(id, ApiClient.obtenerToken(context));
        contratoSeleccionado.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful()) {
                    contratoMutable.postValue(response.body());
                }
                else
                    Toast.makeText(context, "El Contrato no se ha encontrado!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("contrato","ERROR"+t.getMessage());
                Toast.makeText(context, "Inesperadamente  a ocurrido un ERROR ", Toast.LENGTH_SHORT).show();
            }
        });
        //Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        //ApiClient apiClient= ApiClient.getApi();
        //Contrato contrato= apiClient.obtenerContratoVigente(inmueble);
        //this.contratoMutable.setValue(contrato);

    }

}
