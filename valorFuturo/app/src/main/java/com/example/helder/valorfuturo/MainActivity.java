package com.example.helder.valorfuturo;

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
                EditText edtValorPresente, edtTaxaJuros, edtMeses = null;
                TextView txtResultado;

                txtResultado = findViewById(R.id.txtResultado);

                edtValorPresente = findViewById(R.id.edtValorPresente);
                edtTaxaJuros = findViewById(R.id.edtTaxaJuros);
                edtMeses = findViewById(R.id.edtMeses);


                if (TextUtils.isEmpty(edtValorPresente.getText().toString())) {
                    //txtRes.setText("Gentileza inserir a altura");
                    Toast.makeText(
                            getApplicationContext(),
                            "Informe o Valor Presente!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtTaxaJuros.getText().toString())) {
                    //txtRes.setText("Gentileza inserir a altura");
                    Toast.makeText(
                            getApplicationContext(),
                            "Informe a Taxa de Juros!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtMeses.getText().toString())) {
                    //txtRes.setText("Gentileza inserir a altura");
                    Toast.makeText(
                            getApplicationContext(),
                            "Informe a Quantidade de Meses!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }


                Double valorPresente =  Double.valueOf(edtValorPresente.getText().toString());
                Double taxaJuros = Double.valueOf(edtTaxaJuros.getText().toString());
                Double meses = Double.valueOf(edtMeses.getText().toString());

                Double resultado =  valorPresente + ( (valorPresente * taxaJuros /100 ) * meses);

                String resposta =  String.valueOf(resultado);

                txtResultado.setText(resposta);

            }
        }

        );


    }




}
