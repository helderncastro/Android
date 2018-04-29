package com.example.heldernogueira.convertermoeda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btnConverter);

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


            RadioButton rbOpcao1 = findViewById(R.id.rbOpcao1);
            RadioButton rbOpcao2 = findViewById(R.id.rbOpcao2);
            RadioButton rbOpcao3 = findViewById(R.id.rbOpcao3);

            EditText edtValor1 = findViewById(R.id.edtValor1);
            EditText edtValor2 = findViewById(R.id.edtValor2);

            /* Cotação usada.
                Dóllar R$ 3,30
                Euro   R$ 4,20
            */

            // Real x Dóllar
            if (rbOpcao1.isChecked()) {
                Double dollar;
                Double real;
                if (edtValor1.getText().toString().isEmpty()) {
                    dollar = Double.parseDouble(edtValor2.getText().toString());
                    real = dollar * 3.30;
                    edtValor1.setText(real.toString());
                } else {
                    real = Double.parseDouble(edtValor1.getText().toString());
                    dollar = real / 3.30;
                    edtValor2.setText(dollar.toString());
                }
            }

            // Real x Euro
            if (rbOpcao2.isChecked()) {
                Double euro;
                Double real;
                if (edtValor1.getText().toString().isEmpty()) {
                    euro = Double.parseDouble(edtValor2.getText().toString());
                    real = euro * 4.20;
                    edtValor1.setText(real.toString());
                } else {
                    real = Double.parseDouble(edtValor1.getText().toString());
                    euro = real / 4.20;
                    edtValor2.setText(euro.toString());
                }
            }

            //Euro x Dóllar
            if (rbOpcao3.isChecked()) {
                Double euro;
                Double dollar;
                if (edtValor1.getText().toString().isEmpty()) {
                    dollar = Double.parseDouble(edtValor2.getText().toString());
                    euro = dollar * (3.30 / 4.20); // dollar * (cot dolar / cot euro)
                    edtValor1.setText(euro.toString());
                } else {
                    euro = Double.parseDouble(edtValor1.getText().toString());
                    dollar = euro * (4.20 / 3.30); // euro * (cot euro / cot dollar)
                    edtValor2.setText(dollar.toString());
                }


            }


            }
        });

        EditText edtValor1 = findViewById(R.id.edtValor1);
        EditText edtValor2 = findViewById(R.id.edtValor2);

        edtValor1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText edtValor2 = findViewById(R.id.edtValor2);
                edtValor2.setText("");
            }
        });

        edtValor2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText edtValor1 = findViewById(R.id.edtValor1);
                edtValor1.setText("");
            }
        });


    }

    public void onRadioButtonClicked(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        View rdView;

        rdView = findViewById(R.id.rdOpcao);

        RadioGroup rg = findViewById(R.id.rdOpcao);
        TextView txtEntrada = findViewById(R.id.txtEntrada);
        TextView txtResultado = findViewById(R.id.txtResultado);

        String radioValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();

        switch(view.getId()) {
            // Real x Dollar
            case R.id.rbOpcao1:
                if (checked) {
                    txtEntrada.setText("Real");
                    txtResultado.setText("Dóllar");
                }

                break;
            //Real x Euro
            case R.id.rbOpcao2:
                if (checked){
                    txtEntrada.setText("Real");
                    txtResultado.setText("Euro");
                }

                break;
            // Euro x Dollar
            case R.id.rbOpcao3:
                if (checked){
                    txtEntrada.setText("Euro");
                    txtResultado.setText("Dóllar");
                }

                break;
        }
    }





}
