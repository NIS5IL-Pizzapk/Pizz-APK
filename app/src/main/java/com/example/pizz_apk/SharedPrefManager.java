package com.example.pizz_apk;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pizz_apk.models.User;

public class SharedPrefManager {
    private static String SHARED_PREF_NAME = "example";
    private SharedPreferences sharedPreferences;
    Context context;
    private  SharedPreferences.Editor editor;

    public SharedPrefManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    void saveCurrentUser (User user){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        //token
        editor.putInt("id",user.getId());
        editor.putString("username",user.getUsername());
        editor.putString("password",user.getPassword());
        editor.putString("email",user.getEmail());
        editor.putString("adresseLivraison",user.getAdresseLivraison());
        editor.putString("telephone",user.getTelephone());
        editor.putBoolean("isLoggedIn",true);
        editor.apply();
    }

    public boolean isLoggedIn(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("isLoggedIn",false);
    }


   public User getUser() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt("id", -1),
                sharedPreferences.getString("username", null),
                sharedPreferences.getString("password", null),
                sharedPreferences.getString("email", null),
                sharedPreferences.getString("adresseLivraison", null),
                sharedPreferences.getString("telephone", null)
        );
    }

    public void logout(){
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


    public void saveCurrentUser(String token, int id, String email, String password, String username, String adresseLivraison, String telephone) {
        sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        //token
        editor.putInt("id", getUser().getId());
        editor.putString("username", getUser().getUsername());
        editor.putString("password", getUser().getPassword())   ;
        editor.putString("email",getUser().getEmail());
        editor.putString("adresseLivraison", getUser().getAdresseLivraison());
        editor.putString("telephone", getUser().getTelephone());
        editor.putBoolean("isLoggedIn",true);
        editor.apply();
    }

    public void saveCurrentUser2(String token, int id, String username, String email, String adresseLivraison, String telephone) {
    sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
    editor=sharedPreferences.edit();
    //token
    editor.putInt("id", Integer.parseInt(token));
    editor.putString("username", username);
    editor.putString("email", email);
    editor.putString("adresseLivraison", adresseLivraison);
    editor.putString("telephone", telephone);
    editor.putBoolean("isLoggedIn",true);
    editor.apply();

    }
}
