package com.example.inmobile;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.inmobile.modelo.LoginView;
import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.example.inmobile.request.ApiClient.getMyApiClient;

public class LoginViewModel extends AndroidViewModel {
    Context context;
    private MutableLiveData<String> error;
    private MutableLiveData<Boolean> loginMutable;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();

    }

    public LiveData<String> getError() {
        if (error == null) {
            error = new MutableLiveData<>();
        }
        return error;
    }
    public LiveData<Boolean> getLoginMutable(){
        if(loginMutable == null){
            loginMutable = new MutableLiveData<>();
        }
        return  loginMutable;
    }

    public void autenticar(String email, String clave) {

        if (email != null && clave != null && email.length() > 0 && clave.length() > 0) {

            LoginView lv= new LoginView (email, clave);
            Call<String> respuestaToken = ApiClient.getMyApiClient().login(lv);
            respuestaToken.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        //Log.d("Token",response.body());
                        SharedPreferences sharedPreferences = context.getSharedPreferences("token.dat",0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token","Bearer " + response.body());
                        editor.commit();
                        loginMutable.postValue(true);
                    }
                    else{
                        error.postValue("Datos del usuario incorrectos");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("Token","Error de salida de token"+t.getMessage());
                    error.postValue(t.getMessage());
                }
            });


        /*ApiClient apiClient= ApiClient.getApi();
        Propietario propietario = apiClient.login(mail, password);

        if (propietario != null) {
            Intent intent = new Intent(context, MainActivity.class);
            //para llamar una activity desde el viewmodel
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } else {
            error.setValue("Usuario y/o password incorrecto/s !!!!");
        }*/
        } else {
            error.setValue("Email y/o Clave incorrectos");
        }

    }
}

