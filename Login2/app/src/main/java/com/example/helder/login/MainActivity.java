package com.example.helder.login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    ProgressDialog progressDialog;
    static String TAG = MainActivity.class.getName();
    private TextView info;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkUserSession();

        info = findViewById(R.id.info);
        loginButton = findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();

        loginButton.setReadPermissions("email");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {

                getUserDetails(loginResult);
                Log.d(TAG, "Login realizado com sucesso");
            }

            @Override
            public void onCancel() {

                info.setText("Login Cancelado.");
            }

            @Override
            public void onError(FacebookException e) {

                info.setText("Login Falhou.");
            }
        });


            // Botão de Login local ==========================================
        final Button btnEntrar = findViewById(R.id.btnEntrar);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                makePostCall();
            }
        });



    }

    /*
     * Verifica se a sessão do usuário está iniciada. Em caso positivo direciona o usuário para a activity UserProfileActivity
     */

    private void checkUserSession() {

        SharedPreferences sharedPreferences = getSharedPreferences("USER_DATA", MODE_PRIVATE);

        if(sharedPreferences.contains("LOGIN_SESSION")){

            Log.d(TAG, "O usário já está logado, direcionando ele para a UserProfileActivity");

            Intent intent = new Intent(MainActivity.this, TelaPrincipal.class);
            startActivity(intent);
        }
    }




    private void makePostCall() {

       // Toast.makeText(MainActivity.this, "MakePostCall", Toast.LENGTH_SHORT).show();

        String url = "http://ec2-34-230-46-185.compute-1.amazonaws.com:8080/v1/users/authenticate";
        RequestQueue queue = Volley.newRequestQueue(this);

        /**
         JsonObjectRequest espera 5 parâmetros
         Request Type - Tipo da requisição: GET,POST
         URL          - URL da API
         JSONObject   - Objeto JSON da requisição (parameters.null se a requisição for do tipo GET)
         Listener     - Implementação de um Response.Listener() com um callback de sucesso e de erro
         **/

        JSONObject postRequest = new JSONObject();

        EditText email = findViewById(R.id.edtEmail);
        EditText senha = findViewById(R.id.edtSenha);
        //Toast.makeText(getBaseContext(),email + " " + senha,Toast.LENGTH_LONG).show(); desta forma manda um objeto como se fosse sem um toString
        //Toast.makeText(getBaseContext(),"ENVIANDO" + " " + email.getText() + " " + senha.getText(),Toast.LENGTH_LONG).show();

        try {

            postRequest.put("email", email.getText().toString());
            postRequest.put("pass", senha.getText().toString());
            //postRequest.put("appid","4fa74572c6b3268a6ae5bd1150d7a748");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postRequest,
                new Response.Listener<JSONObject>() {

                    /* Callback chamado em caso de sucesso */

                    @Override
                    public void onResponse(JSONObject response) {

                        progressDialog.dismiss();
                        Log.d(TAG, "API Response: " + response);
                        String message = response.optString("message");
                        showDialog("Informação", message);

                        String sucesso = response.optString("sucess");
                        //Toast.makeText(getBaseContext(),sucesso,Toast.LENGTH_SHORT).show();

                        if (sucesso.equals("true")) {
                            String nomeUsuario = response.optString("userName");
                            String emailCadastrado = response.optString("email");


                            Intent intent = new Intent(MainActivity.this, TelaPrincipal.class);

                            intent.putExtra("nomeUsuario",nomeUsuario);
                            intent.putExtra("emailUsuario",emailCadastrado);

                            startActivity(intent);

                        }


                    }
                },

                /* Callback chamado em caso de erro */

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e(TAG, "Ocorreu um erro ao chamar a API " + error);
                        progressDialog.dismiss();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                //add params <key,value>
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                Map<String, String> headers = new HashMap<String, String>();
                String auth = "Basic MzgxNTc5ZmEtZDI0MC00Mzg3LTkyNTMtZWY2YjgwYTdhMWEwOmM4NDM4M2Y0LTJiMDgtNGJiYy04MjQwLWI0YjQ5YTFlYWQzZQ==";
                headers.put("Authorization", auth);
                return headers;
            }
        };

        queue.add(jsonObjReq);
        showProgressDialog();
    }

    private void showDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Por favor, aguarde");
        progressDialog.show();
    }


    public void chamaCadastro(View view) {
        Intent intent = new Intent(MainActivity.this,Conta.class);
        startActivity(intent);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    /*
     * Recupera os dados do usuário no Facebook e os envia para uma nova Activity via Intent
     * @param loginResult objeto contendo os dados retornados pelo login
     */
    protected void getUserDetails(LoginResult loginResult) {

        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        Intent intent = new Intent(MainActivity.this, TelaPrincipal.class);
                        intent.putExtra("userProfile", json_object.toString());
                        startActivity(intent);
                    }

                });

        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,name,email,picture.width(240).height(240)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();
    }

}
