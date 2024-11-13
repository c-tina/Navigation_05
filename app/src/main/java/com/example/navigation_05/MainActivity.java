package com.example.navigation_05;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.navigation_05.databinding.ActivityMainBinding;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((binding = ActivityMainBinding.inflate(getLayoutInflater())).getRoot());

        setSupportActionBar(binding.toolbar);

        setupNavControllers();
    }

    private void setupNavControllers() {
        // Configurar NavController para el Bottom Navigation
        navController = ((NavHostFragment) Objects.requireNonNull(
                getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment)
        )).getNavController();

        // Definir AppBarConfiguration con los top-level destinations
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.options1Fragment2, R.id.options2Fragment2, // Options Menu
                R.id.drawer1Fragment2, R.id.drawer2Fragment2,     // Drawer Menu
                R.id.bottom1Fragment2, R.id.bottom2Fragment2, R.id.bottom3Fragment2 // Bottom Menu
        )
                .setOpenableLayout(binding.drawerLayout) // DrawerLayout para el drawer menu
                .build();

        // Configurar Toolbar y Bottom Navigation con el NavController principal
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.bottomNavView, navController);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    // Establece los ítems del options_menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    // Usa el navController para navegar al destino cuando se pulsa un ítem
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return NavigationUI.onNavDestinationSelected(item, navController)
                || super.onOptionsItemSelected(item);
    }
}

