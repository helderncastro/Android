package com.example.helder.corpreferida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onRadioButtonClicked(View view) {

        /*
         * Usamos o método isChecked para verificar se o botão radio foi selecionado
         */
        boolean checked = ((RadioButton) view).isChecked();

        View rdView;

        rdView = findViewById(R.id.rdGroup);


        RadioGroup rg = findViewById(R.id.rdGroup);

        /*
         * O método getCheckedRadioButtonId retorna o identificador de um radio button selecionado em um determinado grupo
         */
        String radioValue = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();

        switch(view.getId()) {
            case R.id.rdBranco:
                if (checked)

                    rdView.setBackgroundColor(getResources().getColor(android.R.color.white));
                    Toast.makeText(
                            getApplicationContext(),
                            "Opção selecionada: " + radioValue,
                            Toast.LENGTH_SHORT).show();
                break;
            case R.id.rdAmarelo:
                if (checked)
                    rdView.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
                    Toast.makeText(
                            getApplicationContext(),
                            "Opção selecionada: " + radioValue,
                            Toast.LENGTH_SHORT).show();
                break;

            case R.id.rdRosa:
                if (checked)
                    rdView.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
                    Toast.makeText(
                            getApplicationContext(),
                            "Opção selecionada: " + radioValue,
                            Toast.LENGTH_SHORT).show();
                break;
            case R.id.rdAzul:
                if (checked)
                    rdView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                    Toast.makeText(
                            getApplicationContext(),
                            "Opção selecionada: " + radioValue,
                            Toast.LENGTH_SHORT).show();
                break;
        }
    }





}
