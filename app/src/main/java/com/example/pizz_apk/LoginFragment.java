package com.example.pizz_apk;

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
import com.example.pizz_apk.models.RetroFitResponse;
import com.example.pizz_apk.models.User;
import com.example.pizz_apk.services.Utils;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;

public class LoginFragment extends Fragment {

    private Retrofit retrofit;
    RetroFitRequests requests;
    SharedPrefManager sharedPrefManager;

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

        sharedPrefManager = new SharedPrefManager(requireContext());

        loginButton.setOnClickListener(v -> {
                HashMap<String, String> map = new HashMap<>();

//                map.put("password", password.getText().toString());
//            Log.d("password", password.getText().toString());
//                map.put("email", email.getText().toString());
//            Log.d("email", email.getText().toString());
                //map.put("email", email.getText().toString());

            String userEmail = email.getText().toString();
            String userPassword = password.getText().toString();

                Call<LoginResult> call = requests.Login(userEmail, userPassword);

                call.enqueue(new Callback<LoginResult>() {
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                    Log.d("response", "WHY THE TOKEN IS NOT GIVEN  "+response.body().getToken());
                        if (response.isSuccessful()) {
                            Log.d("testing" , "onResponse: " + sharedPrefManager.getUser().getUsername());

                            Log.d("token", "i have the token: " + response.body().getToken());
                            Log.d("userid test" , "onResponse: " + response.body().getId());
                            Menu menu = ((NavigationView) requireActivity().findViewById(R.id.navigation_view)).getMenu();
                            menu.findItem(R.id.nav_logout).setVisible(true);
                            menu.findItem(R.id.nav_account).setVisible(true);
                            menu.findItem(R.id.nav_commandesencours).setVisible(true);
                            menu.findItem(R.id.nav_reservation).setVisible(true);
                            Toast.makeText(requireContext(), "Login successful Mr" + sharedPrefManager.getUser().getUsername(), Toast.LENGTH_SHORT).show();
                            Log.d("token", "Welcome back ! : " + sharedPrefManager.getUser().getUsername());
                            System.out.println("connexion réussie ! " + response);
                            Toast.makeText(requireContext(), "connexion réussie ! " + response, Toast.LENGTH_SHORT).show();
                            Navigation.findNavController(v).navigate(R.id.accueilFragment);

//                            SharedPreferences preferences = requireContext().getSharedPreferences("user", 0);
//                            SharedPreferences.Editor editor = preferences.edit();
//                            editor.putString("token", response.body().getToken());
//                            //get the user id of the user request
//                            Call<RetroFitResponse<User>> calling = requests.getUserById(response.body().getId());;
//                            Call<LoginResult> calltest = requests.getUserByIdLogin(response.body().getId(), "Bearer " + response.body().getToken());
////                            //store the id of the user connected
//                            Log.d("calltest2", calltest.toString());
//                            editor.putInt("id", response.body().getId());
//                            editor.putString("userUsername", response.body().getUsername());
//                            editor.putString("userEmail", response.body().getMail());
//                            editor.putString("userPassword", response.body().getPassword());
//                            editor.putString("userAdresse", response.body().getAdresseLivraison());
//                            editor.putString("userTelephone", response.body().getTelephone());
//                            editor.putBoolean("isConnected", true);
//                            editor.commit();
//                            Log.d("token USER", " token of the current user "+response.body().getToken());
//                            Log.d("id USER", "userid of the current user "+ requests.getUserById(response.body().getId()));
//                            Log.d("username USER", "username of the current user"+response.body().getUsername());
//                            Log.d("email USER", "email of the current user " +response.body().getMail());
//                            Log.d("connexion réussie ! ", response + " " + response.body().getToken());
                        } else if (response.code() == 401) {
                            Log.d("connexion échouée ! ", response.toString());
                            System.out.println("connexion échouée ! " + response);
                            Toast.makeText(requireContext(), "connexion échouée ! " + response, Toast.LENGTH_SHORT).show();
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
