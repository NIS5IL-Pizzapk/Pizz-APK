package com.example.pizz_apk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pizz_apk.models.LoginResult;
import com.example.pizz_apk.models.RetroFitRequests;
import com.example.pizz_apk.models.SendMailResult;
import com.example.pizz_apk.services.Utils;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LostPasswordFragment extends Fragment {

    RetroFitRequests requests;

    public LostPasswordFragment() {
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
        return inflater.inflate(R.layout.fragment_lost_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.sendMail(view);
    }

    public void sendMail(View view) {

        Button sendMailButton = view.findViewById(R.id.fragment_reset_btn_valid);
        EditText customerMail = view.findViewById(R.id.fragment_reset_input_mail);
        sendMailButton.setOnClickListener(v -> {

            HashMap<String, String> map = new HashMap<>();
            map.put("email", customerMail.getText().toString());

            requests.executeSendMail(map).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.d("TEST", "mailer répond: " + response);
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Envoyé ! Veuillez vérifier votre boite mail", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(v).navigate(R.id.loginFragment);
                    } else if (response.code() == 400) {
                        Log.d("error", "onResponse: " + response.code());
                        Toast.makeText(getContext(), "Envoi du mail échoué", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("TAG", "onFailure: " + t.getMessage());
                    Toast.makeText(getContext(), "Envoi du mail échoué", Toast.LENGTH_SHORT).show();
                }
            });

        });
        }
}