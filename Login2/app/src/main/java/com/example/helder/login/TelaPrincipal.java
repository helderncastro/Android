package com.example.helder.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TelaPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_principal);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String nome = bundle.getString("nomeCapturado");
        //undle bundle = intent.getExtras();
        TextView txtnomeResultado = findViewById(R.id.txtLogin);

        txtnomeResultado.setText("Bem Vindo-Via API " + " " + nome);
    }
}
