package com.example.inmobile;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.inmobile.modelo.Propietario;
import com.example.inmobile.request.ApiClient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Crea un nuevo Builder con un conj de dstinos de nivel superios
        //El boton de retroceso no se mostrara cdo se encuentre en estos destinos
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.mapaInicioFragment,
                R.id.perfilFragment, R.id.inmueblesFragment, R.id.inquilinosFragment,
                R.id.contratosFragment, R.id.pagosFragment, R.id.logoutFragment)
                .setDrawerLayout(drawer)
                .build();
        //Es el objeto que administra la navegacion de la app a traves del nasHost, controla  al navHost
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflar el menú; esto agrega elementos a la barra de acción si está presente.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Se llama a este metodo cdo el usuario quiere navegar hacia arriba dentro de la jerarquia de act
    //de su app desde la barra de accion
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}

