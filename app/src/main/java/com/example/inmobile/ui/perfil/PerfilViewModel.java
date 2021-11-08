package com.example.inmobile.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> mensajeMutable;
    private Context context;


    public PerfilViewModel(@NonNull Application application)
    {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getPropietarioMutable() {
        if (propietarioMutable == null) {
            propietarioMutable = new MutableLiveData<>();
        }
        return propietarioMutable;
    }

    public LiveData<String> getMensajeMutable() {
        if (mensajeMutable == null) {
            mensajeMutable = new MutableLiveData<>();
        }
        return mensajeMutable;
    }

    /*//este metodo nos trae al usuario Logueado
    public void obtenerDatos(){

        ApiClient apiClient= ApiClient.getApi();
        Propietario p = apiClient.obtenerUsuarioActual();
        // paso el usuario al mutable
        propietarioMutable.setValue(p);
    }


    public void editar(int id,Long dni ,String nombre, String apellido, String email,  String clave, String tel){

        Propietario propietario = new Propietario(id, dni, nombre, apellido, email, clave, tel);


        if( (dni != null) && (nombre.length()>0) && (nombre != null) && (apellido != null) && (apellido.length()>0) &&
                ( email != null) && ( email.length() >0 ) && (clave != null) &&
                (clave.length() > 0) && (tel != null ) && (tel.length()>0))
        {

            /*apiClient.actualizarPerfil(propietario);
            propietarioMutable.setValue(propietario);
            mensajeMutable.setValue("Sus datos fueron editados exitosamente!!!");

        }
        else
        {
            mensajeMutable.setValue("Verifique que ningún campo este vacio!!");
        }

             */

    public void cargarPerfil(){
        Call<Propietario> respuestaToken = ApiClient.getMyApiClient().propietarioActual(ApiClient.obtenerToken(context));
        respuestaToken.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietarioMutable.postValue(response.body());
                }
                else{
                    Log.d("error","error en else cargar");
                    Toast.makeText(context,"No se ha encontrado el usuario",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("error","error en failure");
                Toast.makeText(context,"Ha ocurrido un error",Toast.LENGTH_LONG).show();
            }
        });


    }

    public void actualizarPerfil(Propietario propietario){

        Call<Propietario> respuestaToken = ApiClient.getMyApiClient().actualizarPropietario(ApiClient.obtenerToken(context), propietario);
        respuestaToken.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    propietarioMutable.postValue(response.body());

                    Toast.makeText(context, "Datos editados con exito !!",Toast.LENGTH_LONG).show();
                }else{
                    Log.d("error","error en else");
                    Toast.makeText(context,"ERROR No se pudo editar.",Toast.LENGTH_LONG);
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("error","error en failure");
                Toast.makeText(context,"Hay ERROR." + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}



