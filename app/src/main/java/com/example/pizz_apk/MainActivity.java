package com.example.pizz_apk;

import static androidx.navigation.Navigation.findNavController;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfiguration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.restaurantChoixFragment,R.id.accueilFragment)
                .setOpenableLayout(drawerLayout)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
       }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.toolbar_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }



    //remove this if you want application to start
    @Override
    public boolean onSupportNavigateUp() {
      NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.nav_panier) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_panier);
        }
        if (item.getItemId() == R.id.nav_parametres) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_parametres);
        }
        if (item.getItemId() == R.id.nav_apropos) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_apropos);
        }
        if (item.getItemId() == R.id.nav_account) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_account);
        }
        if (item.getItemId() == R.id.nav_contact) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_contact);
        }
        if (item.getItemId() == R.id.nav_deconnexion) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_deconnexion);
        }
        return super.onOptionsItemSelected(item);
    }
}