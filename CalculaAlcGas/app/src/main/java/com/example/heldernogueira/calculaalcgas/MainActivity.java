package com.example.heldernogueira.calculaalcgas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnCalcular);
        btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   EditText edtGasolina;
                   EditText edtAlcool;
                   TextView txtResposta = null;

                   txtResposta = findViewById(R.id.txtResposta);
                   edtAlcool =findViewById(R.id.edtAlcool);
                   edtGasolina = findViewById(R.id.edtGasolina);


                   if (TextUtils.isEmpty(edtGasolina.getText().toString())) {
                       Toast.makeText( getApplicationContext(), "Informe preço da Gasolina", Toast.LENGTH_SHORT).show();
                       return;
                   }

                   if (TextUtils.isEmpty(edtAlcool.getText().toString())) {
                       Toast.makeText( getApplicationContext(), "Informe preço do Alcool", Toast.LENGTH_SHORT).show();
                       return;
                   }

                    Double precoAlcool, precoGasolina, resultado;

                   precoAlcool = Double.valueOf(edtAlcool.getText().toString());
                   precoGasolina = Double.valueOf(edtGasolina.getText().toString());

                   //txtResposta.setText(" alcool " + precoAlcool + "  gasolina " + precoGasolina);


                   resultado = precoAlcool / precoGasolina;

                   // dividir o valor do alcool pela gasolina. Se for menor que 70% vale a pena alcool se for maior que 70% vale gasolina
                    if (resultado > 0.7) {
                        txtResposta.setText(" Resultado: " + String.format("%.2f", resultado) + " Vale a pena Álcool");
                    } else {
                        txtResposta.setText(" Resultado: " + String.format("%.2f", resultado) + " Vale a pena Gasolina");
                    }


               }
           }
        );


    }
}
