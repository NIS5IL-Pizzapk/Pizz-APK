package com.example.pizz_apk;

import static androidx.core.content.ContentProviderCompat.requireContext;
import static androidx.navigation.Navigation.findNavController;

import static com.google.android.material.tabs.TabLayout.GRAVITY_START;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.services.Utils;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navControllertest;
    private DrawerLayout drawerLayout;
    //get selected fragment
    private Fragment selectedFragment;
    private AppBarConfiguration appBarConfiguration;
    RetroFitRequests requests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);

        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.accueilFragment)
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

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        SharedPreferences sharedPreferences = getSharedPreferences("user", 0);
        String token = sharedPreferences.getString("token", null);

        //hide disconnect button if the user is not connected into the application
        if (token == null) {
            menu.findItem(R.id.nav_deconnexion).setVisible(false);
        } else {
            menu.findItem(R.id.nav_deconnexion).setVisible(true);
        }

        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        this.navControllertest = Navigation.findNavController(this, R.id.nav_host_fragment);
           //     if (navControllertest.getCurrentDestination().getId() == R.id.nav_reservation || navControllertest.getCurrentDestination().getId() == R.id.nav_contact || navControllertest.getCurrentDestination().getId() == R.id.nav_parametres || navControllertest.getCurrentDestination().getId() == R.id.nav_apropos || navControllertest.getCurrentDestination().getId() == R.id.nav_commandesencours || navControllertest.getCurrentDestination().getId() == R.id.nav_account) {
             //       Navigation.findNavController(this,R.id.nav_host_fragment).navigate(R.id.accueilFragment);
              //
    return NavigationUI.navigateUp(navControllertest, appBarConfiguration)
                || super.onSupportNavigateUp();
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


            if (item.getItemId() == R.id.nav_reservation) {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_reservation);
            }
            if (item.getItemId() == R.id.nav_commandesencours) {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_commandesencours);
            }
            if (item.getItemId() == R.id.nav_account) {
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_account);
            }
            if (item.getItemId() == R.id.nav_deconnexion) {
                SharedPreferences SharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.edit().clear().apply();
                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_deconnexion);
            }
        if (item.getItemId() == R.id.nav_panier) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_panier);
        }
        if (item.getItemId() == R.id.nav_parametres) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_parametres);
        }
        if (item.getItemId() == R.id.nav_apropos) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_apropos);
        }

        if (item.getItemId() == R.id.nav_contact) {
            Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.nav_contact);
        }

        return NavigationUI.onNavDestinationSelected(item,findNavController(this, R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item);
    }
}