package com.example.pizz_apk;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizz_apk.models.LoginResult;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.services.Utils;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginFragment extends Fragment {

    private Retrofit retrofit;
    RetroFitRequests requests;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.requests = Utils.getRetrofitCon(requireContext());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button loginButton = view.findViewById(R.id.fragment_lgin_btn_valid);
        EditText email = (EditText) view.findViewById(R.id.fragment_lgin_input_mail);
        EditText password = (EditText) view.findViewById(R.id.input_password);

        loginButton.setOnClickListener(v -> {
                HashMap<String, String> map = new HashMap<>();

                map.put("password", password.getText().toString());
            Log.d("password", password.getText().toString());
                map.put("email", email.getText().toString());
            Log.d("email", email.getText().toString());
                //map.put("email", email.getText().toString());

                Call<LoginResult> call = requests.executeLogin(map);

                call.enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                        if (response.isSuccessful()) {
                            SharedPreferences preferences = requireContext().getSharedPreferences("user", 0);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("token", response.body().getToken());
                            //store the id of the user connected
                            editor.putInt("userId", response.body().getId());
                            editor.putString("userUsername", response.body().getUsername());
                            editor.putString("userEmail", response.body().getMail());
                            editor.putString("userPassword", response.body().getPassword());
                            editor.putString("userAdresse", response.body().getAdresseLivraison());
                            editor.putString("userTelephone", response.body().getTelephone());
                            editor.putBoolean("isConnected", true);
                            editor.commit();
                            Menu menu = ((NavigationView) requireActivity().findViewById(R.id.navigation_view)).getMenu();
                            menu.findItem(R.id.nav_logout).setVisible(true);
                            menu.findItem(R.id.nav_account).setVisible(true);
                            menu.findItem(R.id.nav_commandesencours).setVisible(true);
                            menu.findItem(R.id.nav_reservation).setVisible(true);
                             System.out.println("connexion réussie ! " + response);
                            Toast.makeText(requireContext(), "connexion réussie ! " + response, Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(v).navigate(R.id.accueilFragment);

                        }
                    }
                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        System.out.println("Erreur de connexion" + t);
                        Toast.makeText(getContext(), "Erreur de connexion" + t, Toast.LENGTH_SHORT).show();
                    }
                });
        });

    }
}
