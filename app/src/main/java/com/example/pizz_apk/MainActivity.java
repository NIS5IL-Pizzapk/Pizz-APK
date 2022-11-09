package com.example.pizz_apk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RestaurantChoixFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_view);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_cart, menu);
        return true;
    }

Fragment fragment = null;

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
           case R.id.nav_apropos:
              getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AProposFragment()).commit();
              break;
            case R.id.nav_parametres:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ParametresFragment()).commit();
                break;
            case R.id.nav_contact:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactFragment()).commit();
                break;
            case R.id.nav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MonCompteFragment()).commit();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (fragment instanceof AProposFragment) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RestaurantChoixFragment()).commit();
            }
            if (fragment instanceof ContactFragment) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RestaurantChoixFragment()).commit();
            }
            if (fragment instanceof ParametresFragment) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RestaurantChoixFragment()).commit();
            }
            if (fragment instanceof MonCompteFragment) {
                super.onBackPressed();
            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new RestaurantChoixFragment()).commit();
            }
        }
    }

}