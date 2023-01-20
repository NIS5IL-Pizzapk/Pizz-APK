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

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

    public static void handleJWTToken (Context ctx, Response<ResponseBody> response) {
        String token = response.headers().get("Authorization");
        ctx.getSharedPreferences("token", Context.MODE_PRIVATE)
                .edit()
                .putString("token", token)
                .apply();
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("Authorization", "Bearer " + token)
                                .method(original.method(), original.body())
                                .build();

                        return chain.proceed(request);
                    }
                })
                .build();
    }

    public static void setToken(Context ctx, String token) {
        ctx.getSharedPreferences("token", Context.MODE_PRIVATE)
                .edit()
                .putString("token", token)
                .apply();
    }

    //get current connected userid retrofit
    public  static int getUserId (Context ctx) {
        return ctx.getSharedPreferences("user", Context.MODE_PRIVATE).getInt("userId", 0);
    }

    public static void requestNotSuccessfulToast(Context ctx, Response response) {
        String message = "";
        Log.d("TAG", "requestNotSuccessfulToast: " + response.code());
        try {
            Log.d("response", response.errorBody().string());
            message = response.errorBody().string().split("\"message\":\"")[1].split("\"")[0];
        } catch (IOException e) {
            Log.d("response", "error" + e);
            e.printStackTrace();
        }
        Log.d("response", message);
        Toast.makeText(ctx, "Erreur "+response.code() + " : " + message, Toast.LENGTH_SHORT).show();
    }
}
