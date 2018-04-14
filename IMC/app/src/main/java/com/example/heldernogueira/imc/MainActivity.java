package com.example.heldernogueira.imc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnCalcular);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edtAltura, edtPeso;
                TextView txtRes, txtMsg = null;


                // SEMPRE USAR FINDVIEWBYID para referenciar ao layout
                txtRes = findViewById(R.id.txtResultado);
                edtAltura = findViewById(R.id.edtAltura);
                edtPeso = findViewById(R.id.edtPeso);
                txtMsg = findViewById(R.id.txtMensagem);

                //if(TextUtils.isEmpty(phoneNumber.getText().toString())) {
                if (TextUtils.isEmpty(edtAltura.getText().toString())) {
                    //txtRes.setText("Gentileza inserir a altura");
                    Toast.makeText(
                             getApplicationContext(),
                            "Informe a Altura!",
                             Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(edtPeso.getText().toString())) {
                    Toast.makeText(
                            getApplicationContext(),
                            "Informe o Peso",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                Double altura =  Double.valueOf(edtAltura.getText().toString());
                Double peso = Double.valueOf(edtPeso.getText().toString());

                double imc = calcularIMC(peso, altura);
                String respImc = respostaIMC(imc);

                txtRes.setText(" IMC = " +  imc);
                txtMsg.setText(respImc);


            }
        });
    }

    public String respostaIMC (double resultado) {
        String respIMC = null;

        if (resultado < 18.5 ) {
            respIMC = "Você está abaixo do seu peso ideal";
        } else if ( resultado >= 18.5 && resultado < 25.0) {
            respIMC = "Parabéns! você está no seu peso ideal";
        } else if ( resultado >= 25.0 && resultado < 30.0 ) {
            respIMC = "Você está acima do Peso (Soprepeso)";
        } else if (resultado >= 30.0 && resultado < 35) {
            respIMC = "Obesidade Grau I";
        } else if (resultado >= 35 && resultado < 39.9) {
            respIMC = "Obesidade Grau II";
        } else if (resultado >= 39.9) {
            respIMC = "Manoel Urias";
        }
                /*

                abaixo de 18,5 - você está abaixo de peso ideal
                Entre 18,5 - 24,9 - Parabéns! Você está no seu peso ideal
                Entre 25,0 - 29,9 - Você está acima do Peso (Sobrepeso)
                Entre 30,0 - 34,9 - Obesidade Grau I
                Entre 35,0 e 39,9 - Obesidade Grau II

                 */

        return respIMC;
    }

    public double calcularIMC(double peso, double altura) {
        Double resultado = null;
        // peso / altura * altura
        resultado = peso / pow( altura, altura );

        return resultado;


    }

}
