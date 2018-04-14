package com.example.heldernogueira.prestacaopaga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnCalcular);
        btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String mensagem;
                   Integer meses;
                   Double juros;
                   Double valor;

                   EditText edtMeses = findViewById(R.id.edtMeses);
                   EditText edtJuros = findViewById(R.id.edtJuros);
                   EditText edtValor = findViewById(R.id.edtValor);
                   TextView txtResposta = findViewById(R.id.txtResposta);

                   if (TextUtils.isEmpty(edtMeses.getText().toString())) {
                       Toast.makeText( getApplicationContext(), "Informe os meses do financiamento",Toast.LENGTH_LONG).show();
                       return;
                   }

                   if (TextUtils.isEmpty(edtJuros.getText().toString())){
                       Toast.makeText( getApplicationContext(),"Informe o juro do financimento", Toast.LENGTH_LONG).show();
                       return;
                   }

                   if (TextUtils.isEmpty(edtValor.getText().toString())) {
                       Toast.makeText( getApplicationContext(), "Informe o Valor do financiamento", Toast.LENGTH_LONG).show();
                       return;
                   }

                   meses = Integer.valueOf( edtMeses.getText().toString() );
                   juros = Double.valueOf( edtJuros.getText().toString() );
                   valor = Double.valueOf(edtValor.getText().toString() );
                   mensagem = calcularValor(meses,juros,valor);

                   txtResposta.setText(mensagem);

               }
           }
        );

    }


    public String calcularValor(Integer meses, Double juros, double valor) {

        String msg = null;
        double jurosPeriodo, valorFinal;
        double valorParcela;

        // calculo do juros = J = Valor Principal * Juros a.m (/100) * parcelas
        jurosPeriodo = valor * (juros / 100) * meses;
        valorFinal = jurosPeriodo + valor;12    
        valorParcela = valorFinal / meses;


        msg = "Valor Montante: " +  String.format("%.2f", valorFinal) + " O valor da Parcela é : " + String.format("%.2f",valorParcela) ;

        return msg;
    }


}
