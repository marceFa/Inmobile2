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

import java.util.ArrayList;
import java.util.List;

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
    public void cargarContrato(Bundle arguments) {
        Contrato contrato= (Contrato) arguments.getSerializable("Contrato");
        this.contratoMutable.setValue(contrato);



        //Inmueble inmueble = (Inmueble) bundle.get("inmueble");
        //ApiClient apiClient= ApiClient.getApi();
        //Contrato contrato= apiClient.obtenerContratoVigente(inmueble);
        //this.contratoMutable.setValue(contrato);

    }

}
