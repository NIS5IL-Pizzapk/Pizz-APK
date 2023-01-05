package com.example.pizz_apk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.models.RetroFitResponse;
import com.example.pizz_apk.models.User;
import com.example.pizz_apk.services.Utils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MonCompteFragment extends Fragment {
    private Retrofit retrofit;
    RetroFitRequests requests;
    public HashMap<String, String> userID;
    public String valueUsername;
    public String valuePassword;
    public String valueEmail;
    public String valueAdresse;
    public String valueTelephone;


    public MonCompteFragment() {
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
        return inflater.inflate(R.layout.fragment_mon_compte, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

      HashMap<String , String> map = new HashMap<>();
        map.put("id", String.valueOf(userID));
        requests.getUserById(map).enqueue(new Callback<RetroFitResponse<User>>() {
            @Override
            public void onResponse(Call<RetroFitResponse<User>> call, Response<RetroFitResponse<User>> response) {
                if (response.isSuccessful()) {
                    RetroFitResponse<User> body = response.body();
                    if (body != null) {
                        if (body.getSuccess()) {
                            User user = body.getResult();
                            valueUsername = user.getUsername();
                            valuePassword = user.getPassword();
                            valueEmail = user.getEmail();
                            valueAdresse = user.getAdresseLivraison();
                            valueTelephone = user.getTelephone();
                        } else {
                            Toast.makeText(requireContext(), body.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RetroFitResponse<User>> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        //change hint from edittext
        EditText emailguest = view.findViewById(R.id.input_email_account);
        emailguest.setHint("current email : " + valueEmail);
        EditText passwordguest = view.findViewById(R.id.input_password_account);
        passwordguest.setHint("current password : " + valuePassword);
        EditText adressguest = view.findViewById(R.id.input_adresse_account);
        adressguest.setHint("current adresse : " + valueAdresse);
        EditText phoneguest = view.findViewById(R.id.input_telephone_account);
        phoneguest.setHint("current phone : " + valueTelephone);
        EditText usernameguest = view.findViewById(R.id.input_username_account);
        usernameguest.setHint("current username : " + valueUsername);
        Button editButton = view.findViewById(R.id.btn_modif_account);

        editButton.setOnClickListener(v -> {
            HandleUpdateUser(view);
            Log.d("HandleUpdateUser", "HandleUpdateUser called");
            Toast.makeText(requireContext(), "Votre compte a été modifié", Toast.LENGTH_SHORT).show();
        });
    }

    public void HandleUpdateUser(View view) {

        EditText username = view.findViewById(R.id.input_username_account);
        EditText email = view.findViewById(R.id.input_email_account);
        EditText password = view.findViewById(R.id.input_password_account);
        EditText adress = view.findViewById(R.id.input_adresse_account);
        EditText telephone = view.findViewById(R.id.input_telephone_account);

        HashMap<String, String> map = new HashMap<>();
        map.put("username", username.getText().toString());
        map.put("email", email.getText().toString());
        HashMap<String, String> mapadrss = new HashMap<>();
        mapadrss.put("adresse", adress.getText().toString());
        map.put("password", password.getText().toString());
        map.put("telephone", telephone.getText().toString());

        //get current login user current id
        requests.getUserById(userID).enqueue(new Callback<RetroFitResponse<User>>() {
            @Override
            public void onResponse(Call<RetroFitResponse<User>> call, Response<RetroFitResponse<User>> response) {
                if (response.isSuccessful()) {
                    RetroFitResponse<User> retroFitResponse = response.body();
                    if (retroFitResponse != null) {
                        User user = retroFitResponse.getResult();
                        if (user != null) {
                            userID = user.getId();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RetroFitResponse<User>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });

        requests.executeUpdate(userID, username.getText().toString(), password.getText().toString(), email.getText().toString(), adress.getText().toString(), telephone.getText().toString()).enqueue(new Callback<RetroFitResponse<User>>() {
            @Override
            public void onResponse(Call<RetroFitResponse<User>> call, Response<RetroFitResponse<User>> response) {
                if (response.isSuccessful()) {
                    RetroFitResponse<User> retroFitResponse = response.body();
                    if (retroFitResponse != null) {
                        User user = retroFitResponse.getResult();
                        if (user != null) {
                            userID = user.getId();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<RetroFitResponse<User>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }
}