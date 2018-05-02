package com.example.helder.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

public class TelaPrincipal extends AppCompatActivity {

    JSONObject response, profilePicData, profilePicUrl;
    static String TAG = TelaPrincipal.class.getName();

    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    TextView tvUserName, tvUserEmail;
    ImageView ivUserPicture;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String userName, userEmail, userPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);


        tvUserName = findViewById(R.id.UserName);
        ivUserPicture = findViewById(R.id.profilePic);
        tvUserEmail = findViewById(R.id.email);


        getUserData();



    }



    private void getUserData() {

        /*
         * Inicialmente recuperamos os dados do usuário que foram enviados via Intent
         */

        try {


            /*
             * Recuperamos o objeto USER_DATA, caso ele exista o objeto sharedPreferences será setado
             */

            sharedPreferences = getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);

            /*
             * Se a sessão já tiver sido ininicida, usamos os objetos do sharedPreferences
             */

            if(sharedPreferences.contains("LOGIN_SESSION")){

                userEmail = sharedPreferences.getString("FB_USER_EMAIL", "");
                userName = sharedPreferences.getString("FB_USER_NAME", "");
                userPicture = sharedPreferences.getString("FB_USER_PIC", "");

            } else {

                Intent intent = getIntent();

                Bundle bundle = intent.getExtras();
                String nome = bundle.getString("nomeUsuario");

                if ( nome == null) {

                    String jsondata = intent.getStringExtra("userProfile");

                    Log.d(TAG, "JSON: " + jsondata);

                    response = new JSONObject(jsondata);

                    /*
                     * Recuperamos os respectivos campos retornados no JSON e os setamos nos componentes de tela
                     */

                    profilePicData = new JSONObject(response.get("picture").toString());
                    profilePicUrl = new JSONObject(profilePicData.getString("data"));

                    userEmail = response.get("email").toString();
                    userName = response.get("name").toString();
                    userPicture = profilePicUrl.getString("url");

                    /*
                     * Habilitamos o modo de edição do sharedPreferences
                     */

                    editor = sharedPreferences.edit();

                    /*
                     * Adicionamos KEYs que representam os dados do usuário ao objeto USER_DATA
                     */

                    editor.putString("FB_USER_NAME", userName);
                    editor.putString("FB_USER_EMAIL", userEmail);
                    editor.putString("FB_USER_PIC", userPicture);
                    editor.putBoolean("LOGIN_SESSION", true);

                    /*
                     * Salvamos as keys criadas
                     */

                    editor.commit();

                    tvUserEmail.setText(userEmail);
                    tvUserName.setText(userName);
                    Picasso.with(this).load(userPicture).into(ivUserPicture);

                } else
                {
                    tvUserName.setText("Seja Bem Vindo: " + nome);
                   // tvUserEmail.setText(userEmail);
                }


            }

            /*
             * Setamos os dados dos usuário nos componentes de tela
             */





        } catch(Exception e){

            e.printStackTrace();
        }
    }



    public void logout(View view) {

        Log.d(TAG, "Finalizando sessão do usuário");

        /*
         * Método que encerra a sessão do usuário no Facebook
         */

        LoginManager.getInstance().logOut();

        /*
         * Removemos os dados do usuário que estão no sharedPreferences
         */

        //sharedPreferences.edit().remove("USER_DATA").commit();
        sharedPreferences.edit().clear().commit();

        Intent intent = new Intent(TelaPrincipal.this, MainActivity.class);
        startActivity(intent);
    }

}
