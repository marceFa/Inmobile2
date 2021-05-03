package com.example.inmobile.ui.perfil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.request.ApiClient;

public class PerfilViewModel extends ViewModel {

    private MutableLiveData<Propietario> propietarioMutable;
    private MutableLiveData<String> mensajeMutable;
    ApiClient apiClient= ApiClient.getApi();

    public PerfilViewModel() {
        super();
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

    //este metodo nos trae al usuario Logueado
    public void obtenerDatos(){

        ApiClient apiClient= ApiClient.getApi();
        Propietario p = apiClient.obtenerUsuarioActual();
        // paso el usuario al mutable
        propietarioMutable.setValue(p);
    }

    public void editar(int id,Long dni ,String nombre, String apellido, String email,  String password, String tel){

        Propietario propietario = new Propietario(id, dni, nombre, apellido, email, password, tel);


        if( (dni != null) && (nombre.length()>0) && (nombre != null) && (apellido != null) && (apellido.length()>0) &&
                ( email != null) && ( email.length() >0 ) && (password != null) &&
                (password.length() > 0) && (tel != null ) && (tel.length()>0))
        {
            apiClient.actualizarPerfil(propietario);
            propietarioMutable.setValue(propietario);
            mensajeMutable.setValue("Sus datos fueron editados exitosamente!!!");

        }
        else
        {
            mensajeMutable.setValue("Verifique que ningún campo este vacio!!");
        }
    }

}
