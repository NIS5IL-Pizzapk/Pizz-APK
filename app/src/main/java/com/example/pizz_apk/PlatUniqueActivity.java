package com.example.pizz_apk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.pizz_apk.databinding.ActivityPlatUniqueBinding;
import com.example.pizz_apk.models.PlatPropose;

public class PlatUniqueActivity extends AppCompatActivity {

    ActivityPlatUniqueBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plat_unique);
        binding = ActivityPlatUniqueBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        PlatPropose plat = (PlatPropose) getIntent().getSerializableExtra("plat");
        binding.tvPlatUniqueNom.setText(plat.getNom());
        binding.tvPlatUniquePrix.setText(String.valueOf(plat.getPrix()));
        binding.tvPlatUniqueIngredients.setText(plat.getDescription());
    }
}