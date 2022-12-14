package com.example.pizz_apk.services;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.Toast;

import com.example.pizz_apk.R;
import com.example.pizz_apk.models.RetroFitRequests;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    public static String getConfigValue(Context context, String name) {
        Resources resources = context.getResources();
        try {
            InputStream rawResource = resources.openRawResource(R.raw.config);
            Properties properties = new Properties();
            properties.load(rawResource);
            return properties.getProperty(name);
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Unable to find the config file: " + e.getMessage());
        } catch (IOException e) {
            Log.e(TAG, "Failed to open config file.");
        }
        return null;
    }

    public static RetroFitRequests getRetrofitCon(Context ctx) {
        String apiUrl = Utils.getConfigValue(ctx, "api_url");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetroFitRequests.class);
    }

    public static String getToken (Context ctx) {
        return ctx.getSharedPreferences("user", Context.MODE_PRIVATE).getString("token", null);
    }

    public static void requestNotSuccessfulToast(Context ctx, Response response) {
        String message = "";
        try {
            message = response.errorBody().string().split("\"message\":\"")[1].split("\"")[0];
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(ctx, "Erreur "+response.code() + " : " + message, Toast.LENGTH_SHORT).show();
    }
}
