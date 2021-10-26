package com.example.inmobile.ui.inquilinos;

import android.app.Application;
import android.content.Context;
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

public class InquilinosViewModel extends ViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Inmueble>> inmueblesMutable;

    public LiveData<ArrayList<Inmueble>> getInmuebles() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }
    //Acá buscamos en la ApiClient y nos trae los inmuebles que tienen un inquilino
    public void cargarInmueblesConInquilino() {


        //ApiClient apiClient= ApiClient.getApi();
        //ArrayList<Inmueble> inmuebles = apiClient.obtenerPropiedadesAlquiladas();
        //this.inmueblesMutable.setValue(inmuebles);
    }


}
