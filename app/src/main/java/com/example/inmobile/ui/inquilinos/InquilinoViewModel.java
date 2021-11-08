package com.example.inmobile.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inquilino;
import com.example.inmobile.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Inquilino> inquilinoMutable;

    public InquilinoViewModel(@NonNull Application application)
    {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Inquilino> getInquilino() {
        if (inquilinoMutable == null) {
            inquilinoMutable = new MutableLiveData<Inquilino>();
        }
        return inquilinoMutable;
    }

    ////Acá recibimos un inmueble  y buscamos en la ApiClient el contrato vigente de ese inmueble y su inquilino
    public void cargarInquilino(int id) {
        Log.d("id","el id"+id);
        Call<Contrato> respuestaToken = ApiClient.getMyApiClient().inquilinoPorContrato(id, ApiClient.obtenerToken(context));
        respuestaToken.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                if(response.isSuccessful()){
                    inquilinoMutable.postValue(response.body().getInquilino());
                }
                else{
                    Log.d("response","error: "+response.message());
                    Toast.makeText(context, "Inquilino no encontrado", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.d("inquilino","error: "+t.getMessage());
                Toast.makeText(context, "Ocurrio un error", Toast.LENGTH_SHORT).show();
            }
        });



        //ApiClient apiClient= ApiClient.getApi();
        //Inquilino inquilino = apiClient.obtenerInquilino(inmueble);
        //this.inquilinoMutable.setValue(inquilino);



    }


}