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

public class InquilinosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<List<Contrato>> inmueblesMutable;

    public InquilinosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Contrato>> getInmuebles() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }
    //Acá buscamos en la ApiClient y nos trae los inmuebles que tienen un inquilino
    public void cargarInmueblesConInquilino() {

        Call<List<Contrato>> inquilinosxinmu = ApiClient.getMyApiClient().contratosVigentes(ApiClient.obtenerToken(context));
        inquilinosxinmu.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if(response.isSuccessful()){
                    inmueblesMutable.postValue(response.body());
                }else{
                    Log.d("else","ERROR en el else");
                    Toast.makeText(context, "No hay inquilinos.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                Log.d("failure","ERROR en el failure");
                Toast.makeText(context, "Se ha producido un error ", Toast.LENGTH_SHORT).show();

            }
        });



        //ApiClient apiClient= ApiClient.getApi();
        //ArrayList<Inmueble> inmuebles = apiClient.obtenerPropiedadesAlquiladas();
        //this.inmueblesMutable.setValue(inmuebles);
    }


}
