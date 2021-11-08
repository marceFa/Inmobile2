package com.example.inmobile.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobile.modelo.Contrato;
import com.example.inmobile.modelo.Inmueble;
import com.example.inmobile.modelo.Pago;
import com.example.inmobile.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel  extends AndroidViewModel{

    private MutableLiveData<List<Contrato>> inmueblesMutable;
    private Context context;


    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<List<Contrato>> getInmuebles() {
        if (inmueblesMutable == null) {
            inmueblesMutable = new MutableLiveData<>();
        }
        return inmueblesMutable;
    }

    //Acá hacemos una consulta a la ApiClient para traer los inmuebles con un contrato y sus pagos

    public void cargarInmueblesConPagos() {
        Call<List<Contrato>> pagosxcontrato = ApiClient.getMyApiClient().contratosVigentes(ApiClient.obtenerToken(context));
        pagosxcontrato.enqueue(new Callback<List<Contrato>>() {
            @Override
            public void onResponse(Call<List<Contrato>> call, Response<List<Contrato>> response) {
                if(response.isSuccessful()){
                    ArrayList<Inmueble> inmuebles = new ArrayList<>();
                    Log.d("salida",response.body().get(0).getIdContrato()+"");
                    for(Contrato c: response.body()){
                        inmuebles.add(c.getInmueble());

                    }
                    inmueblesMutable.postValue(response.body());
                }else{
                    Toast.makeText(context, "No hay Pagos realizados", Toast.LENGTH_LONG).show();
                    Log.d("else","no paso");
                }
            }
            @Override
            public void onFailure(Call<List<Contrato>> call, Throwable t) {
                Log.d("Error ",t.getMessage());

                Toast.makeText(context, "Ha ocurrido un error!!! ", Toast.LENGTH_SHORT).show();
            }
        });



      /*  ApiClient apiClient= ApiClient.getApi();
        ArrayList<Inmueble> inmuebles= apiClient.obtenerPropiedadesAlquiladas();
        this.inmueblesMutable.setValue(inmuebles);
*/

    }

}