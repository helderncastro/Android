package com.example.helder.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by viniciusdepaula on 24/03/18.
 */

public class SessionUtils extends AppCompatActivity {

    static String TAG = MainActivity.class.getName();

     /*
     * Verifica se a sessão do usuário está iniciada. Em caso positivo direciona o usuário para a activity UserProfileActivity
     * @param context Contexto da classe origem
     * @param sharedPreferences referência ao shared preferences utilizado para o controle da sessão do usuário
     */

    public void checkUserSession(Context context, SharedPreferences sharedPreferences) {

        if(sharedPreferences.contains("LOGIN_SESSION")){

            Log.d(TAG, "O usário já está logado, direcionando ele para a UserProfileActivity");

            Intent intent = new Intent(context, TelaPrincipal.class);
            startActivity(intent);
        }
    }
}
