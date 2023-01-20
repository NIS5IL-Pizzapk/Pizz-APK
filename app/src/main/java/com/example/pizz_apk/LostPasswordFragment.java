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

            Call<SendMailResult> call = requests.SendEmail(String.valueOf(customerMail));

            call.enqueue(new Callback<SendMailResult>() {
                @Override
                public void onResponse(Call<SendMailResult> call, Response<SendMailResult> response) {
                    Log.d("TEST", "mailer répond: " + response);
                    if (response.isSuccessful()) {
                        SendMailResult result = response.body();
                        if (result != null) {
                            if (result.getSuccess()) {
                                Log.d("TEST", "mailer répond: " + response);
                                Toast.makeText(requireContext(), "Email envoyé", Toast.LENGTH_SHORT).show();
                                Navigation.findNavController(view).navigate(R.id.action_lostPasswordFragment_to_loginFragment);
                            } else {
                                Log.d("TEST", "mailer répond: " + response);
                                Toast.makeText(requireContext(), "Email non envoyé", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<SendMailResult> call, Throwable t) {
                    Log.e("ERROR", t.getMessage());
                }
            });
        });
        }
}