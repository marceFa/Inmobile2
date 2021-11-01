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
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.modelo.Inquilino;
import com.example.inmobile.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends ViewModel {

    private MutableLiveData<Inquilino> inquilinoMutable;

    public InquilinoViewModel() {
        super();
    }

    public LiveData<Inquilino> getInquilino() {
        if (inquilinoMutable == null) {
            inquilinoMutable = new MutableLiveData<>();
        }
        return inquilinoMutable;
    }

    ////Acá recibimos un inmueble  y buscamos en la ApiClient el contrato vigente de ese inmueble y su inquilino
    public void cargarInquilino(Bundle bundle) {

        //Inquilino inquilino = (Inquilino) bundle.get("Contrato");
        //ApiClient apiClient= ApiClient.getApi();
        //Inquilino inquilino = apiClient.obtenerInquilino(inmueble);
        //this.inquilinoMutable.setValue(inquilino);



    }


}