package com.example.pizz_apk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private DrawerLayout drawerLayout;
    private AppBarConfiguration appBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navController = Navigation.findNavController(this, R.id.fragment_container);
        drawerLayout = findViewById(R.id.drawer_layout);

        //remove this if you want application to start
        setSupportActionBar(findViewById(R.id.toolbar));
        NavigationUI.setupWithNavController((NavigationView) findViewById(R.id.navigation_view), navController);
        NavigationUI.setupActionBarWithNavController(this, navController, (DrawerLayout) findViewById(R.id.drawer_layout));

    }

    //remove this if you want application to start
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, (DrawerLayout) findViewById(R.id.drawer_layout));
    }


}